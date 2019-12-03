package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 */
public class StartGameButtonListener implements ActionListener {

	private GameEngine model;
	private EventObservable eventObservable;
	/**
	 * Constructor
	 * @param model: instance of Game Engine
	 * @param eventObservable: instance of EventObservable
	 * 
	 * **/
	public StartGameButtonListener(GameEngine model, EventObservable eventObservable) {
		this.model = model;
		this.eventObservable = eventObservable;
	}

	/**
	 * - Validates if bet is not placed for any player
	 * 		- if the bet is not placed for any player displays error message dialog.
	 * 		- if all players have placed bets then updates the event observable that the game is about to start and sends the list of player playing game.
	 * 		- deals all players
	 * 		- deals house 
	 * 
	 * ***/
	@Override
	public void actionPerformed(ActionEvent e) {

		List<Player> listOfPlayersWithIncompleteInfo = validatePlayers();
		if(listOfPlayersWithIncompleteInfo.size()>0) {
			JOptionPane.showMessageDialog(null, "Please place bet for all the players: ", "Incomplete Player Info", JOptionPane.ERROR_MESSAGE);
		}
		else {
			List<Player> playersPlayingGame = getPlayersWhoWillPlayGame();
			if(playersPlayingGame.size() == 0) {
				JOptionPane.showMessageDialog(null, "There are no players that can be dealt. ", "No Players", JOptionPane.ERROR_MESSAGE);
			} 
			else {
				eventObservable.updateStartGameObserver(playersPlayingGame);
				new Thread() {
					@Override
					public void run() {
						for (Player player : playersPlayingGame) {
								model.dealPlayer(player, 1000);
						}
						model.dealHouse(2000);
					}
				}.start();
			}
			
		}
	}
	
	private List<Player> getPlayersWhoWillPlayGame() {
		List<Player> playersPlayingGame = new ArrayList<>();
		for (Player player : model.getAllPlayers()) {
			if (player.getPoints() > 0) {
				playersPlayingGame.add(player);
			}
		}
		return playersPlayingGame;
	}

	private List<Player> validatePlayers() {
		List<Player> playersList = new ArrayList<>();
		for(Player player : model.getAllPlayers()) {
			if(player.getBet() == 0 && player.getPoints()>0) {
				playersList.add(player);
			}
		}
		return playersList;
	}

}
