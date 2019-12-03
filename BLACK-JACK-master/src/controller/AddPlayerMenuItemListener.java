package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import view.AddPlayerDialog;

/**
 * Add Player in Menu Listener
 * @author nidhi chawla
 *
 */
public class AddPlayerMenuItemListener implements ActionListener {

	private GameEngine model;
	private EventObservable viewModel;
	public AddPlayerMenuItemListener(GameEngine model, EventObservable eventObservable) {
		this.model = model; 
		this.viewModel = eventObservable;
	}

	/**
	 * Opens add player dialog 
	 * **/
	@Override
	public void actionPerformed(ActionEvent e) {
		new AddPlayerDialog(model, viewModel);
	}
}
