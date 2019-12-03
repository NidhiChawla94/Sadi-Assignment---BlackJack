package view;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.observable.EventObservable;
import controller.observable.IGameResultsObserver;
import controller.observable.IStartGameObserver;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *	A pert of Summary panel. This panel is reponsible for showing the  results of the game being played.
 */
@SuppressWarnings("serial")
public class GameSummary extends JPanel implements IGameResultsObserver, IStartGameObserver {

	private DefaultTableModel playerTableModel;
	private JScrollPane playerListScrollPane;
	private JTable gameSummaryTable;
	private JSeparator separator;
	private JLabel gameOverLabel;

	public GameSummary(EventObservable modelObservable) {
		modelObservable.addToHouseResultObserver(this);
		modelObservable.addToPlayerResultObserver(this);
		modelObservable.addToStartGameObserverList(this);
		gameOverLabel = new JLabel();
		gameOverLabel.setText("GAME OVER");
		gameOverLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		gameOverLabel.setVisible(false);
		gameOverLabel.setForeground(Color.RED);
		add(gameOverLabel);
		generateResultsTable();
		
	}

	/** generate table for showing the results.**/
	private void generateResultsTable() {
		playerTableModel = new DefaultTableModel();
		gameSummaryTable = new JTable(playerTableModel);
		playerTableModel.addColumn("NAME");
		playerTableModel.addColumn("SCORE");

		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		separator = new JSeparator();
		add(separator);

		JLabel lblNewLabel = new JLabel("Game Summary:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		add(lblNewLabel);
		playerListScrollPane = new JScrollPane(gameSummaryTable);

		add(playerListScrollPane);
		revalidate();
	}

	/** update the table when result of the house has come. **/
	@Override
	public void updateHouseResult(int result) {
		playerTableModel.addRow(new Object[]{"House", String.valueOf(result)});
		gameOverLabel.setVisible(true);
	}

	/** update the table when result of the player has come.**/
	@Override
	public void updatePlayerResult(Player player, int result) {
		playerTableModel.addRow(new Object[]{player.getPlayerName(), String.valueOf(result)});
	}

	/** clear table when new game has started.**/
	@Override
	public void gameStartedObserved(List<Player> listOfPlayersPlayingGame) {
		gameOverLabel.setVisible(false);
		int totalRows = playerTableModel.getRowCount();
		for(int rowNo=totalRows-1; rowNo>=0 ; rowNo--) {
			playerTableModel.removeRow(rowNo);
		}
		
	}
	

}
