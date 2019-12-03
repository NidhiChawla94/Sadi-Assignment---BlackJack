package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import controller.AddPlayerActionListener;
import controller.observable.EventObservable;
import model.interfaces.GameEngine;

/**
 * @author nidhi chawla
 * Add Player Dialog View
 *
 */
@SuppressWarnings("serial")
public class AddPlayerDialog  extends JDialog{
	private JTextField playerNameTextField;
	private JTextField initialPointsTextField;
	private JTextField playerIdTextField;
	private GameEngine model;
	private EventObservable modelObservable;
	private JLabel addPlayerStatusLabel;
	public AddPlayerDialog(GameEngine gameEngine, EventObservable modelObservable) {
		model = gameEngine;
		this.modelObservable = modelObservable;
		setBounds(700, 150, 500, 300);
		setVisible(true);
		setTitle("Add Player");
		populateUI();
	}
	private void populateUI() {
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	
		//panel for add player fields
		JPanel addPlayerPanel = new JPanel();
		addPlayerPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Player Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		addPlayerPanel.setBounds(12, 13, 473, 250);
		getContentPane().add(addPlayerPanel);
		addPlayerPanel.setLayout(null);
		
		//label for player id 
		JLabel lblPlayerId = new JLabel("Player id : ");
		lblPlayerId.setBounds(34, 46, 95, 16);
		addPlayerPanel.add(lblPlayerId);
		
		// player id text field
		playerIdTextField = new JTextField();
		playerIdTextField.setBounds(130, 39, 157, 22);
		addPlayerPanel.add(playerIdTextField);
		playerIdTextField.setColumns(10);
		
		//label player name
		JLabel lblPlayerName = new JLabel("Player Name : ");
		lblPlayerName.setBounds(34, 77, 95, 16);
		addPlayerPanel.add(lblPlayerName);
		
		//text field for player name
		playerNameTextField = new JTextField();
		playerNameTextField.setBounds(131, 74, 156, 22);
		addPlayerPanel.add(playerNameTextField);
		playerNameTextField.setColumns(10);
		
		// initial points label
		JLabel lblInitialPoints = new JLabel("Initial Points : ");
		lblInitialPoints.setBounds(34, 112, 85, 16);
		addPlayerPanel.add(lblInitialPoints);
		
		//initial points text field
		initialPointsTextField = new JTextField();
		initialPointsTextField.setBounds(131, 109, 156, 22);
		addPlayerPanel.add(initialPointsTextField);
		initialPointsTextField.setColumns(10);
		
		
		//add player button
		JButton btnAddPlayer = new JButton("Add");
		btnAddPlayer.addActionListener(new AddPlayerActionListener(this, model, modelObservable));
		btnAddPlayer.setBounds(154, 185, 114, 37);
		addPlayerPanel.add(btnAddPlayer);
		
		addPlayerStatusLabel = new JLabel();
		addPlayerStatusLabel.setBounds(96, 144, 191, 16);
		addPlayerPanel.add(addPlayerStatusLabel);
		addPlayerStatusLabel.setVisible(false);
		
		JLabel closeMessage = new JLabel("Click the cross icon to close this dialog");
		closeMessage.setFont(new Font("Tahoma", Font.ITALIC, 12));
		getContentPane().add(closeMessage);
	}
	
	//get player name
	public String getPlayerName() {
		return playerNameTextField.getText();
	}
	
	//clear text on fields
	public void clearViewFields() {
		playerNameTextField.setText("");
		playerIdTextField.setText("");
	}
	
	//get player points from text fields 
	public String getInitialPoints() {
		return initialPointsTextField.getText();
	}
	
	//get player id from text field
	public String getPlayerId() {
		return playerIdTextField.getText();
	}
	
	// set error label visible.
	public void errorInAddingPlayer(){
		addPlayerStatusLabel.setText("Please fill all the fields");
		addPlayerStatusLabel.setVisible(true);
		addPlayerStatusLabel.setForeground(Color.RED);
	}
	public void successInAddingPlayer() {
		addPlayerStatusLabel.setText("Player successfully added");
		addPlayerStatusLabel.setVisible(true);
		addPlayerStatusLabel.setForeground(Color.GREEN);
	}
}
