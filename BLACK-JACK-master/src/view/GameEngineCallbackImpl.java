package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback
{
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl()
	{
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)
	{
		logger.log(Level.FINE, String.format("Card Dealt to "+player.getPlayerName()+ " .. Suit: "+ card.getSuit()+ ", Value: "+ card.getValue()+ ", Score: "+ card.getScore() ));
		
	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		logger.log(Level.INFO, player.getPlayerName()+ ", final result="+ result);
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		
		logger.log(Level.FINE, player.getPlayerName()+ " .. Suit: "+ card.getSuit()+ ", Value: "+ card.getValue()+ ", Score: "+ card.getScore()+ " ... YOU BUSTED!" );
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, "Card Dealt to House .. Suit: "+ card.getSuit()+ ", Value: "+ card.getValue()+ ", Score: "+ card.getScore() );
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, "Card Dealt to House .. Suit: "+ card.getSuit()+ ", Value: "+ card.getValue()+ ", Score: "+ card.getScore()+ " ... HOUSE BUSTED!" );
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		StringBuffer strBuffer = new StringBuffer("Final Results:\n");
		/*private List<String> singleTableRowValues = new ArrayList<String>();*/
		logger.log(Level.INFO, "House, final result="+ result);
		for(Player player: engine.getAllPlayers()){
			strBuffer.append("Player: id="+player.getPlayerId()+", name="+player.getPlayerName()+", points="+player.getPoints()).append('\n');
		}
		
		logger.log(Level.INFO, String.format(strBuffer.toString()));
	}
}
