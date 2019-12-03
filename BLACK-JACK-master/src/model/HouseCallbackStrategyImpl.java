
package model;

import model.interfaces.GameEngine;
import model.interfaces.IGameEngineCallbackStratergy;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * @author nidhi chawla
 *
 */
public class HouseCallbackStrategyImpl implements IGameEngineCallbackStratergy {

	
	@Override
	public void nextCard(GameEngineCallback callback, PlayingCard card, GameEngine engine) {
		callback.nextHouseCard(card, engine);
	}

	@Override
	public void bustCard(GameEngineCallback callback, PlayingCard card, GameEngine engine) {
		callback.houseBustCard(card, engine);
	}

	

}
