package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.observable.EventObservable;
import controller.observable.IPlayerEventsObserver;
import controller.observable.IStartGameObserver;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 *Panel where toolbar, board panel are displayed.
 */
@SuppressWarnings("serial")
public class BlackJackBoard extends JPanel implements IPlayerEventsObserver, IStartGameObserver {
	private GameEngine model;
	private EventObservable modelObservable;
	private JPanel playerAreaPanel, houseAreaPanel;
	private CardLayout cardlayout;
	private JPanel boardPanel;
	public BlackJackBoard(GameEngine model, EventObservable modelObservable) {
		this.model = model;
		this.modelObservable = modelObservable;
		populateUI();
		modelObservable.addSelectedPlayerObserver(this);
		modelObservable.addToStartGameObserverList(this);
	}

	private void populateUI() {
		setLayout(new BorderLayout());
		Toolbar toolbarView = new Toolbar(model, modelObservable);
		toolbarView.setVisible(true);
		add(toolbarView, BorderLayout.NORTH);
		boardPanel = new JPanel();
		add(boardPanel, BorderLayout.CENTER);
		revalidate();
		setVisible(true);
	}

	/***create switchable per player panel 
	 * 
	 * @param listOfPlayerPlayingGame : list of players who will be dealt the cards.
	 */
	private void createSwitchableSectionsForPlayers(List<Player> listOfPlayerPlayingGame) {
		for (Player player : listOfPlayerPlayingGame) {
			playerAreaPanel.add(player.getPlayerId(), new PlayerPanel(modelObservable, player));
		}
	}
	/****
	 * show the panel of the player which has been selected from the combobox 
	 * @param player - instance of the player who has been selected and event name.
	 * */
	@Override
	public void updateView(Player player, String eventName) {
		cardlayout.show(playerAreaPanel, player.getPlayerId());
	}
	/**
	 * remove the content from board panel and reset board for the players playing game
	 * */

	@Override
	public void gameStartedObserved(List<Player> listOfPlayerPlayingGame) {
		remove(boardPanel);
		resetBoard();
		createSwitchableSectionsForPlayers(listOfPlayerPlayingGame);
		revalidate();
	}

	/**
	 * reset board for new game
	 * **/
	private void resetBoard() {
		boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.X_AXIS));
		boardPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		playerAreaPanel = new JPanel();
		
		cardlayout = new CardLayout();
		cardlayout.setHgap(10);
		cardlayout.setVgap(10);
		playerAreaPanel.setLayout(cardlayout);
		boardPanel.add(playerAreaPanel, BorderLayout.CENTER);
		houseAreaPanel = new HousePanel(modelObservable);
		boardPanel.add(houseAreaPanel, BorderLayout.EAST);
		add(boardPanel, BorderLayout.CENTER);
	}
}
