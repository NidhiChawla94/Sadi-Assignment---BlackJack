/**
 * 
 */
package model;

import model.interfaces.GameEngine;
import model.interfaces.IGameEngineCallbackStratergy;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * @author nidhi chawla
 *
 */
public class PlayerCallbackStrategyImpl implements IGameEngineCallbackStratergy {

	Player player;
	public PlayerCallbackStrategyImpl(Player player) {
		super();
		this.player = player;
	}

	@Override
	public void nextCard(GameEngineCallback callback, PlayingCard card, GameEngine engine) {
		callback.nextCard(player, card, engine);
		
	}

	@Override
	public void bustCard(GameEngineCallback callback, PlayingCard card, GameEngine engine) {
		callback.bustCard(player, card, engine);
	}

	

}
