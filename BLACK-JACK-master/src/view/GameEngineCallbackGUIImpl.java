package view;

import javax.swing.SwingUtilities;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * @author nidhi chawla
 *
 *Implementation of GameEngineCallback to update GUI.
 *@see view.interfaces.GameEngineCallback
 *
 */
public class GameEngineCallbackGUIImpl implements GameEngineCallback{

	EventObservable eventObservable;
	
	public GameEngineCallbackGUIImpl() {
		super();
	}

	public GameEngineCallbackGUIImpl(EventObservable eventObservable) {
		this.eventObservable = eventObservable;
	}
	
	
	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateNextCard(player, card);
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateBustCard(player, card);
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateResult(player, result);
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateNextHouseCard(card);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateBustHouseCard(card);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventObservable.updateHouseResult(result);
			}
		});
	}
	

}
