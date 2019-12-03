package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.PlaceBetDialog;

/**
 * Controller for Place Bet Dialog.
 * @author nidhi chawla
 *
 */
public class PlaceBetActionListener implements ActionListener {

	private PlaceBetDialog placeBetView;
	private GameEngine model; 
	private EventObservable eventObservable;
	
	public PlaceBetActionListener(PlaceBetDialog placeBetView, GameEngine model, EventObservable eventObservable) {
		this.placeBetView = placeBetView;
		this.model = model;
		this.eventObservable = eventObservable;
	}
	
	/**
	 * Called upon clicking the place bet button in the Place Bet Dialog
	 * 	- validates bet placed
	 * 		- if valid bet
	 * 			- places bet 
	 * 			- calls method on event observable to update observers.
	 * 			- calls method on view to update success message.
	 * 		- if invalid bet
	 * 			- calls method on view to update for invalid bet.
	 * 
	 * ***/
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Player selectedPlayer = placeBetView.getSelectedPlayer();
		
		int betToBePlaced = Integer.parseInt(placeBetView.getBet());
		if(selectedPlayer.getPoints()>= betToBePlaced) {
			model.placeBet(selectedPlayer, betToBePlaced);
			eventObservable.updatePlaceBetObserver(selectedPlayer);
			placeBetView.setBetPlacedStatus();
		}
		else {
			placeBetView.setInvalidBetStatus();
		}
	}
	

}
