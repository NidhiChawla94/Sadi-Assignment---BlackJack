package view;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.observable.EventObservable;
import controller.observable.IGameResultsObserver;
import controller.observable.IPlayerEventsObserver;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 */
@SuppressWarnings("serial")
public class PlayersSummary extends JPanel implements IPlayerEventsObserver, IGameResultsObserver {
	private DefaultTableModel playerTableModel ;
	private JScrollPane playerListScrollPane;
	private JTable playerSummaryTable;
	private GameEngine model;
	/**
	 * @param model
	 * @param modelObservable
	 */
	public PlayersSummary(GameEngine model, EventObservable modelObservable) {
		this.model = model;
		modelObservable.addToAddPlayerObserverList(this);
		modelObservable.addToPlaceBetObserverList(this);
		modelObservable.addToHouseResultObserver(this);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JSeparator separator = new JSeparator();
		add(separator);
		JLabel lblNewLabel = new JLabel("Player Summary: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblNewLabel);
		generatePlayersTable();
	}
	/**
	 * @see controller.observable.IPlayerEventsObserver#updateView(model.interfaces.Player, java.lang.String)
	 */
	@Override
	public void updateView(Player player, String eventName) {
		updatePlayer();
		
	}
	
	private void updatePlayer() {
		remove(playerListScrollPane);
		generatePlayersTable();
	}
	/**
	 * generate table for the players summary panel in summary panel
	 */
	private void generatePlayersTable() {
		playerTableModel = new DefaultTableModel();
		 playerSummaryTable = new JTable(playerTableModel);
		 playerTableModel.addColumn("ID");
		 playerTableModel.addColumn("NAME");
		 playerTableModel.addColumn("BALANCE POINTS");
		 playerTableModel.addColumn("BET");
		 for(Player player : model.getAllPlayers()) {
			 playerTableModel.addRow(new Object[]{player.getPlayerId(), player.getPlayerName(), player.getPoints(), player.getBet()});
		 }
		playerListScrollPane = new JScrollPane(playerSummaryTable);
		add(playerListScrollPane);
		revalidate();
	}
	@Override
	public void updatePlayerResult(Player player, int result) {
		updatePlayer();
	}
	/**
	 * @see controller.observable.IGameResultsObserver#updateHouseResult(int)
	 */
	@Override
	public void updateHouseResult(int result) {
		updatePlayer();
	}
	
	
	
}
