package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import view.PlaceBetDialog;

/**
 * Listener of Place Bet button in toolbar.
 * @author nidhi chawla
 *
 */
public class PlaceBetToolbarListener implements ActionListener {

	private GameEngine model;
	private EventObservable eventObservable;
	/**
	 * Constructor
	 * @param model: instance of GameEngine
	 * @param eventObservable : instance of the EventObservable
	 * ***/
	public PlaceBetToolbarListener(GameEngine model, EventObservable eventObservable) {
		this.model = model;
		this.eventObservable = eventObservable;
	}
	
	/**
	 * Opens Place bet dialog.
	 * **/

	@Override
	public void actionPerformed(ActionEvent e) {
		new PlaceBetDialog(model, eventObservable);
	}

}
