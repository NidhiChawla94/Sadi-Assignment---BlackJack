package controller.observable;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 * @see controller.observable.EventObservable
 *	Concrete implementation of the Event Observable Interface
 */
public class EventObservableImpl implements EventObservable {

	/**
	 * List of different Observers.
	 * **/
	private List<IPlayerEventsObserver> addPlayerObserverList = new ArrayList<>();
	private List<IPlayerEventsObserver> selectedPlayerObserverList = new ArrayList<>();
	private List<IPlayerEventsObserver> placeBetObserverList = new ArrayList<>();
	private List<IStartGameObserver> startGameObserverList = new ArrayList<>();
	private List<IPlayerCardObserver> playerCardObserverList = new ArrayList<>();
	private List<IHouseCardObserver> houseCardObserverList = new ArrayList<>();
	private List<IGameResultsObserver> playerResultObserverList = new ArrayList<>();
	private List<IGameResultsObserver> houseResultObserverList = new ArrayList<>();

	@Override
	public void addToAddPlayerObserverList(IPlayerEventsObserver observer) {
		addPlayerObserverList.add(observer);
	}
	@Override
	public void updateAddPlayerObserver(Player player) {
		for(IPlayerEventsObserver viewObserver : addPlayerObserverList) {
			viewObserver.updateView(player, Constants.EVENT_ADD_PLAYER);
		}
	}
	@Override
	public void addSelectedPlayerObserver(IPlayerEventsObserver observer) {
		selectedPlayerObserverList.add(observer);
	}
	@Override
	public void updateSelectedPlayerObserver(Player selectedPlayer) {
		for(IPlayerEventsObserver viewObserver : selectedPlayerObserverList) {
			viewObserver.updateView(selectedPlayer, Constants.EVENT_PLAYER_SELECTED);
		}
	}
	@Override
	public void addToPlaceBetObserverList(IPlayerEventsObserver observer) {
		placeBetObserverList.add(observer);
	}
	@Override
	public void updatePlaceBetObserver(Player player) {
		for(IPlayerEventsObserver viewObserver : placeBetObserverList) {
			viewObserver.updateView(player, Constants.EVENT_BET_PLACED);
		}
	}
	
	@Override
	public void addToStartGameObserverList(IStartGameObserver observer) {
		startGameObserverList.add(observer);
	}
	@Override
	public void updateStartGameObserver(List<Player> playersPlayingGame) {
		for(IStartGameObserver viewObserver : startGameObserverList) {
			viewObserver.gameStartedObserved(playersPlayingGame);
		}
	}
	
	@Override
	public void addToPlayerCardObserverList(IPlayerCardObserver observer) {
		playerCardObserverList.add(observer);
	}
	
	@Override
	public void updateNextCard(Player player, PlayingCard playingCard) {
		for(IPlayerCardObserver observer : playerCardObserverList) {
			observer.updateNextPlayerCard(player, playingCard);
		}
	}
	
	
	
	@Override
	public void updateBustCard(Player player, PlayingCard playingCard) {
		for(IPlayerCardObserver observer : playerCardObserverList) {
			observer.updateBustPlayerCard(player, playingCard);
		}
	}
	@Override
	public void updateResult(Player player, int result) {
		for(IGameResultsObserver observer: playerResultObserverList) {
			observer.updatePlayerResult(player, result);
		}
	}
	
	@Override
	public void addToHouseCardsObserverList(IHouseCardObserver observer) {
		houseCardObserverList.add(observer);	
	}
	
	@Override
	public void updateNextHouseCard(PlayingCard playingCard) {
		for(IHouseCardObserver observer : houseCardObserverList) {
			observer.updateNextHouseCard(playingCard);
		}
	}
	@Override
	public void updateBustHouseCard(PlayingCard playingCard) {
		for(IHouseCardObserver observer : houseCardObserverList) {
			observer.updateBustHouseCard(playingCard);
		}
	}
	@Override
	public void updateHouseResult(int result) {
		for(IGameResultsObserver observer: houseResultObserverList) {
			observer.updateHouseResult(result);
		}
	}
	@Override
	public void addToPlayerResultObserver(IGameResultsObserver observer) {
		playerResultObserverList.add(observer);
	}
	@Override
	public void addToHouseResultObserver(IGameResultsObserver observer) {
		houseResultObserverList.add(observer);
		
	}
	
	
}
