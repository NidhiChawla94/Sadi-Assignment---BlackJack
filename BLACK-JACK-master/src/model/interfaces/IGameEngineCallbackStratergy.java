package model.interfaces;

import view.interfaces.GameEngineCallback;

/**
 * @author nidhi chawla
 *
 */
public interface IGameEngineCallbackStratergy {
	/**
	 * called for each card to be dealt to hosue/player , use this strategy to call the implementation of GameEngineCallback
	 * @param callback
	 * 					reference of GameEngineCallback
	 * @param card
	 * 				reference of PlayingCard, card being dealt to house
	 * @param engine
	 * 			reference to the engine so the receiver can call
	 *            methods if necessary
	 *   
	 * */
	public abstract void nextCard(GameEngineCallback callback, PlayingCard card, GameEngine engine);
	/**
	 * called when the card causes the player/house to bust , use this strategy to call the implementation of GameEngineCallback
	 * @param callback
	 * 					reference of GameEngineCallback
	 * @param card
	 * 				reference of PlayingCard, card being dealt to house
	 * @param engine
	 * 			reference to the engine so the receiver can call
	 *            methods if necessary
	 *   
	 * */
	public abstract void bustCard(GameEngineCallback callback, PlayingCard card, GameEngine engine);
	
}
