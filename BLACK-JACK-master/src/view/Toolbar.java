package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.PlaceBetToolbarListener;
import controller.SelectedPlayerChangeListener;
import controller.StartGameButtonListener;
import controller.observable.EventObservable;
import controller.observable.IPlayerEventsObserver;
import controller.observable.IStartGameObserver;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 */
@SuppressWarnings("serial")
public class Toolbar extends JToolBar implements IPlayerEventsObserver, IStartGameObserver {
	private GameEngine model;
	private EventObservable modelObservable;
	private Collection<Player> players = new ArrayList<Player>();
	private JComboBox<Player> playerIdcomboBox;
	private JButton btnPlaceBet;
	private JButton startGame;
	private SelectedPlayerChangeListener selectedPlayerListener;
	/**
	 * @param model - instance of the Game engine
	 * @param eventObservable - instance of the event observable.
	 */
	public Toolbar(GameEngine model, EventObservable eventObservable) {
		this.model = model;
		this.modelObservable = eventObservable;
		eventObservable.addToAddPlayerObserverList(this);
		eventObservable.addToStartGameObserverList(this);
		populateUI();

	}

	private void populateUI() {
		btnPlaceBet = new JButton("Place Bet");
		add(btnPlaceBet);
		btnPlaceBet.addActionListener(new PlaceBetToolbarListener(model, modelObservable));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		playerIdcomboBox = new JComboBox<Player>();
		selectedPlayerListener = new SelectedPlayerChangeListener(playerIdcomboBox, modelObservable);
		add(playerIdcomboBox);

		startGame = new JButton("Start Game ");
		startGame.addActionListener(new StartGameButtonListener(model, modelObservable));
		add(startGame);

		playerIdcomboBox.setEnabled(false);
		btnPlaceBet.setEnabled(false);
		startGame.setEnabled(false);
	}

	/**
	 * @param listOfPlayersPlayingGame - list of players playing game.
	 * set players playing the game in combobox
	 */
	private void setPlayersInComboBox(List<Player> listOfPlayersPlayingGame) {
		players = listOfPlayersPlayingGame;
		for (Player player : players) {
			playerIdcomboBox.addItem(player);
		}
		playerIdcomboBox.setRenderer(new PlayerListCellRenderer());

	}

	@Override
	public void updateView(Player player, String eventName) {
		btnPlaceBet.setEnabled(true);
		startGame.setEnabled(true);
	}

	@Override
	public void gameStartedObserved(List<Player> listOfPlayersPlayingGame) {
		playerIdcomboBox.removeItemListener(selectedPlayerListener);
		playerIdcomboBox.removeAllItems();
		setPlayersInComboBox(listOfPlayersPlayingGame);
		playerIdcomboBox.addItemListener(selectedPlayerListener);
		playerIdcomboBox.setEnabled(true);

	}

}
