package controller;
/**
 * Add Player Controller for Add Player Dialog
 * 
 * @author nidhi chawla
 * */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.observable.EventObservable;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerDialog;

public class AddPlayerActionListener implements ActionListener{

	private GameEngine model; 
	private AddPlayerDialog addPlayerView;
	private EventObservable eventObservable;
	
	/**
	 * Constructor 
	 * @param addPlayer : instance of the Add Player Dialog View
	 * @param model : instance of the model
	 * @param eventObservable : instance of the Event Observable which will be used to inform the respective observers about the event. 
	 * */
	public AddPlayerActionListener(AddPlayerDialog addPlayer, GameEngine model, EventObservable eventObservable) {
		this.model = model;
		addPlayerView = addPlayer;
		this.eventObservable = eventObservable;
	}

	/**
	 * Action Performed Event Handler
	 * 
	 * 	- validates the data in fields
	 * 		- calls method on view if data is not there in any field.
	 * 	- creates new player and calls add player on model to add the newly created player
	 * 	- calls method on event observable to update the attached observers.
	 * 	- calls method on view to update the success message
	 * 
	 * @param e: instance of the event
	 * 
	 * **/
	@Override
	public void actionPerformed(ActionEvent e) {
		String playerId = addPlayerView.getPlayerId();
		String playerName = addPlayerView.getPlayerName();
		String initialPoints = addPlayerView.getInitialPoints();
		if(playerId.isEmpty() || playerName.isEmpty() || initialPoints.isEmpty()){
			addPlayerView.errorInAddingPlayer();
		}
		else {
			Player newPlayer = new SimplePlayer(addPlayerView.getPlayerId(), addPlayerView.getPlayerName(), Integer.parseInt(addPlayerView.getInitialPoints()));
			model.addPlayer(newPlayer);
			eventObservable.updateAddPlayerObserver(newPlayer);
			addPlayerView.clearViewFields();
			addPlayerView.successInAddingPlayer();
		}
	}
}
