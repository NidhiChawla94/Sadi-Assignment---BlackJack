package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.interfaces.GameEngine;
import model.interfaces.IGameEngineCallbackStratergy;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private Deque<PlayingCard> shuffledCards = new LinkedList<>();
	Map<String, Player> players = new HashMap<String, Player>();
	List<GameEngineCallback> gameEngineCallback = new ArrayList<GameEngineCallback>();


	public GameEngineImpl() {
		super();
		createShuffledDeck();
	}

	/**
	 * Shuffles the deck of card returned by getCardDeck 
	 * */

	private void createShuffledDeck() {
		ArrayList<PlayingCard> cards = getCardDeck();
		Random random = new Random();
		int randomCardIndex = 0;
		for(int i = 0; i< PlayingCard.DECK_SIZE;i++){
			randomCardIndex = random.nextInt(cards.size());
			shuffledCards.add(cards.get(randomCardIndex));
			cards.remove(randomCardIndex);
		}
	}
	/**
	 * Creates a card deck of 52 cards
	 * @return cardDeck : a list of cards
	 * */

	private ArrayList<PlayingCard> getCardDeck(){
		ArrayList<PlayingCard> cardDeck = new ArrayList<>();
		for(Suit cardSuit : Suit.values()){
			for(Value cardValue : Value.values()){
				cardDeck.add(new PlayingCardImpl(cardSuit,cardValue));
			}
		}
		return cardDeck;
	}
	/**
	 * Pops a card from a deck of shuffled cards 
	 * if the deck gets Empty then calls createShuffledCards
	 * @return a card to be dealt to user
	 * */
	private PlayingCard getCardToBeDealt(){
		PlayingCard cardToBeDealt = shuffledCards.pop();
		if(shuffledCards.size()==0){
			createShuffledDeck();
		}
		return cardToBeDealt;
	}

	@Override
	public void dealPlayer(Player player, int delay) {
		int points = deal(delay, new PlayerCallbackStrategyImpl(player));
		for(GameEngineCallback callback: gameEngineCallback){
			callback.result(player, points, this);
		}
		player.setResult(points);

	}

	/**
	 * Deals card until bust
	 * @param delay : delay between dealing cards
	 * @param strategyImplObj : an object of concrete strategy class
	 * @return points : Total points of player/house before bust
	 * */
	private int deal(int delay, IGameEngineCallbackStratergy strategyImplObj){
		int points =0;
		try {
			boolean continueToDealCards = true;
			while (continueToDealCards){
				Thread.sleep(delay);
				PlayingCard cardToBeDealt = getCardToBeDealt();
				if(points + cardToBeDealt.getScore()<= GameEngine.BUST_LEVEL) {
					for(GameEngineCallback callback: gameEngineCallback){
						strategyImplObj.nextCard(callback, cardToBeDealt, this);
					}
					points+=cardToBeDealt.getScore();
				} else{
					for(GameEngineCallback callback: gameEngineCallback){
						strategyImplObj.bustCard(callback, cardToBeDealt, this);	
					}
					continueToDealCards = false;
				}	
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return points;
	}
	@Override
	public void dealHouse(int delay) {
		int points = deal(delay, new HouseCallbackStrategyImpl());
		for(Player player: players.values()){
			if(player.getResult()< points){
				player.setPoints(player.getPoints()- player.getBet());
			} else if (player.getResult()> points){
				player.setPoints(player.getPoints()+ player.getBet());
			}
			player.resetBet();
		}
		for(GameEngineCallback callback: gameEngineCallback){
			callback.houseResult(points, this);
		}
		createShuffledDeck();
	}
	@Override
	public void addPlayer(Player player) {
		players.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		Player playerWithGivenId = null;
		if(players.containsKey(id)){
			playerWithGivenId = players.get(id);
		}
		return playerWithGivenId;
	}

	@Override
	public boolean removePlayer(Player player) {
		if(players.containsKey(player.getPlayerId())){
			players.remove(player.getPlayerId());
			return true;
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallback.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		if(this.gameEngineCallback.contains(gameEngineCallback)){
			this.gameEngineCallback.remove(gameEngineCallback);
			return true;
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(players.values());
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		return player.placeBet(bet);
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		return shuffledCards;
	}
}
