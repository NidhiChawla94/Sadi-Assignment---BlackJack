package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.observable.EventObservable;
import controller.observable.IHouseCardObserver;
import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 *	part of board panel responsible for displaying the cards being dealt to  the house 
 */
@SuppressWarnings("serial")
public class HousePanel extends JPanel implements IHouseCardObserver {

	private JPanel houseCards;

	public HousePanel(EventObservable modelObservable) {

		modelObservable.addToHouseCardsObserverList(this);
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(50, 100));
		populateUI();
	}
	/** add GUI components to the panel **/
	private void populateUI() {
		JPanel housePanel = new JPanel();
		housePanel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		add(housePanel, BorderLayout.NORTH);
		housePanel.add(new JLabel("HOUSE"));
		//create panel just for cards.
		houseCards = new JPanel();
		houseCards.setBorder(new TitledBorder(null, "House Cards", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(houseCards, BorderLayout.CENTER);
		houseCards.setLayout(new FlowLayout(FlowLayout.LEFT));
	}
	
	/** function called when next card dealt to house. **/
	@Override
	public void updateNextHouseCard(PlayingCard playingCard) {
		addCardsToThePanel(playingCard);
	}

	/** add the bust card to the panel. **/
	@Override
	public void updateBustHouseCard(PlayingCard playingCard) {
		addCardsToThePanel(playingCard);
	}
	/** function called when house busted. **/
	private void addCardsToThePanel(PlayingCard playingCard) {
		houseCards.add(new CardView(playingCard));
		revalidate();
	}
}
