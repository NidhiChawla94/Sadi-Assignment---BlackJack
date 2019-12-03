package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controller.PlaceBetActionListener;
import controller.observable.EventObservable;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * @author nidhi chawla
 *	Place Bet View
 */
@SuppressWarnings("serial")
public class PlaceBetDialog extends JDialog {
	
	private Collection<Player> players = new ArrayList<Player>();
	private JComboBox<Player> playerIdcomboBox;
	private JTextField betTextField;
	private JLabel statusLabel;
	private GameEngine model;
	private EventObservable modelObservable;
	public PlaceBetDialog(GameEngine model, EventObservable modelObservable) {
		this.model = model;
		this.modelObservable = modelObservable;
		setBounds(700, 150, 500, 300);
		setTitle("Place Bet");
		setVisible(true);
		populateUI();
	}

	private void populateUI() {
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
				
		JPanel betDetailsPanel = new JPanel();
		betDetailsPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bet Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(betDetailsPanel);
		betDetailsPanel.setLayout(null);

		JLabel lblBet = new JLabel("Bet");
		lblBet.setBounds(30, 81, 95, 16);
		betDetailsPanel.add(lblBet);

		betTextField = new JTextField();
		betTextField.setBounds(131, 78, 156, 22);
		betDetailsPanel.add(betTextField);
		betTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Player Id:");
		lblNewLabel.setBounds(30, 52, 56, 16);
		betDetailsPanel.add(lblNewLabel);
		
		playerIdcomboBox = new JComboBox<Player>();
		playerIdcomboBox.setBounds(131, 49, 156, 22);
		players = getPlayersWhoCanPlaceBet();
		for(Player player: players) {
			playerIdcomboBox.addItem(player);	
		}
		if(players.size()>0) {
			playerIdcomboBox.setRenderer(new PlayerListCellRenderer());
		}
		betDetailsPanel.add(playerIdcomboBox);
		
		JButton placeBet = new JButton("Place Bets");
		placeBet.addActionListener(new PlaceBetActionListener(this, model, modelObservable));
		placeBet.setBounds(156, 130, 114, 37);
		betDetailsPanel.add(placeBet);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(40, 104, 347, 27);
		betDetailsPanel.add(statusLabel);
		
		JLabel closeMessage = new JLabel("Click the cross icon to close this dialog");
		closeMessage.setFont(new Font("Tahoma", Font.ITALIC, 12));
		add(closeMessage);

	}
	/** 
	 * @return: String: value of combobox
	 * **/
	public Player getSelectedPlayer() {
		return playerIdcomboBox.getItemAt(playerIdcomboBox.getSelectedIndex());
	}
	/**
	 * @return String: bet value from text field 
	 * 
	 * **/
	public String getBet() {
		return betTextField.getText();
	}
	/**set error in case of invalid bet**/
	public void setInvalidBetStatus() {
		statusLabel.setText("Invalid Bet. Bet should be less than or equal to points.");
		statusLabel.setForeground(Color.RED);
	}
	public void setBetPlacedStatus() {
		statusLabel.setText("Successfully Placed Bet.");
		statusLabel.setForeground(Color.GREEN);
	}
	/**
	 * @return Collection<Player>: players who can place bet i.e. players whose points are greater than 0.
	 * */
	private Collection<Player> getPlayersWhoCanPlaceBet() {
		Collection<Player> playerList = new ArrayList<>();
		for(Player player : model.getAllPlayers()) {
			if(player.getPoints()>0) {
				playerList.add(player);
			}
		}
		return playerList;
	}
}
