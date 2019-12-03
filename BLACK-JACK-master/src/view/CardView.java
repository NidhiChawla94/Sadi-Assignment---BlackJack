package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 * layout of the playing card
 */
@SuppressWarnings("serial")
public class CardView extends JPanel {
	public CardView(PlayingCard card) {
		setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		JPanel topLeftPanel = new JPanel();
		topLeftPanel.setBackground(Color.WHITE);
		topPanel.add(topLeftPanel);
		
		//set the card value on top left panel of the card
		JLabel topCardValue = new JLabel(card.getValue().toString());
		topLeftPanel.add(topCardValue);
		
		JPanel topRightPanel = new JPanel();
		topRightPanel.setBackground(Color.WHITE);
		topPanel.add(topRightPanel);
		
		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		
		JPanel bottomLeftPanel = new JPanel();
		bottomLeftPanel.setBackground(Color.WHITE);
		bottomPanel.add(bottomLeftPanel);
		
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setBackground(Color.WHITE);
		bottomPanel.add(bottomRightPanel);
		
		// set suit value on bottom right panel of card.
		JLabel bottomCardSuiteLabel = new JLabel(card.getSuit().toString());
		bottomRightPanel.add(bottomCardSuiteLabel);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(null);
		setVisible(true);
		revalidate();
	}
}
