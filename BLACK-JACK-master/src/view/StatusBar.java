package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import controller.observable.Constants;
import controller.observable.EventObservable;
import controller.observable.IHouseCardObserver;
import controller.observable.IPlayerCardObserver;
import controller.observable.IPlayerEventsObserver;
import controller.observable.IStartGameObserver;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Value;

/**
 * @author nidhi chawla
 *
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel implements IPlayerEventsObserver, IStartGameObserver, IPlayerCardObserver, IHouseCardObserver {
		private JLabel currentPlayerNameLabel;
		private JLabel lblCurrentPlayer ;
		private JLabel statusMessage;
		public StatusBar(GameEngine model, EventObservable modelObservable) {
			modelObservable.addToAddPlayerObserverList(this);
			modelObservable.addToPlaceBetObserverList(this);
			modelObservable.addToHouseCardsObserverList(this);
			modelObservable.addToPlayerCardObserverList(this);
			modelObservable.addToStartGameObserverList(this);
			populateUI();
		}
		/**
		 * Create components to be displayed on GUI
		 */
		private void populateUI() {
			setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			setLayout(new GridLayout(1, 0, 0, 0));
			
			JPanel currentPlayerPanel = new JPanel();
			add(currentPlayerPanel);
			
			lblCurrentPlayer = new JLabel("Currently dealing cards to: ");
			currentPlayerPanel.add(lblCurrentPlayer);
			
			currentPlayerNameLabel = new JLabel("");
			currentPlayerPanel.add(currentPlayerNameLabel);
			
			JPanel statusMessagePanel = new JPanel();
			add(statusMessagePanel);
			
			JLabel statusMessageLabel = new JLabel("Status: ");
			statusMessagePanel.add(statusMessageLabel);
			statusMessage = new JLabel("");
			statusMessage.setForeground(new Color(0, 128, 128));
			statusMessagePanel.add(statusMessage);
			
		}

		/** Update status bar when player added, and bet placed
		 * @see controller.observable.IPlayerEventsObserver
		 */
		@Override
		public void updateView(Player player, String eventName) {
			if(eventName.equals(Constants.EVENT_ADD_PLAYER)) {
				statusMessage.setText(Constants.MSG_PLAYER_ADDED + " : "+ player.getPlayerName());
			}else if(eventName.equals(Constants.EVENT_BET_PLACED)) {
				statusMessage.setText(Constants.MSG_BET_PLACED + " : "+ player.getPlayerName());
			}
		}
		/** Update GUI when game started
		 * @see controller.observable.IStartGameObserver
		 */
		@Override
		public void gameStartedObserved(List<Player> playersPlayingGame) {
			
			statusMessage.setText(Constants.MSG_GAME_STARTED);
		}
		/** Update GUI about the player being dealt.
		 * @see controller.observable.IPlayerCardObserver
		 */
		@Override
		public void updateNextPlayerCard(Player player, PlayingCard playingCard) {
			setPersonBeingDealt(player.getPlayerName());
						
		}
		/** Update GUI about the player busted and the card it busted at
		 * @see controller.observable.IPlayerCardObserver#updateBustPlayerCard
		 */
		@Override
		public void updateBustPlayerCard(Player player, PlayingCard playingCard) {
			setBustCardDetails(player.getPlayerName(), playingCard.getValue());
		}
		/** Update GUI about House being dealt
		 * @see controller.observable.IHouseCardObserver
		 */
		@Override
		public void updateNextHouseCard(PlayingCard playingCard) {
			setPersonBeingDealt("House");
		}
		/** update GUI about the house bust card
		 * @see controller.observable.IHouseCardObserver
		 */
		@Override
		public void updateBustHouseCard(PlayingCard playingCard) {
			setBustCardDetails("House", playingCard.getValue());
		}
		
		private void setPersonBeingDealt(String playerName) {
			currentPlayerNameLabel.setText(playerName);

		}
		private void setBustCardDetails(String playerName, Value cardValue) {
			statusMessage.setText(playerName+ " busted at : "+ cardValue);
		}
}
