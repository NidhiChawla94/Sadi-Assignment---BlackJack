package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.observable.EventObservable;
import controller.observable.IPlayerCardObserver;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 *
 */
@SuppressWarnings("serial")
public class PlayerPanel extends JPanel implements IPlayerCardObserver {

	private Player panelOfPlayer;
	private JLabel playerNameLabel;
	private JPanel playerCards;

	/**
	 * @param modelObservable
	 * @param panelOfPlayer
	 */
	public PlayerPanel(EventObservable modelObservable, Player panelOfPlayer) {
		this.panelOfPlayer = panelOfPlayer;
		modelObservable.addToPlayerCardObserverList(this);
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(50, 100));
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		add(panel, BorderLayout.NORTH);

		playerNameLabel = new JLabel(panelOfPlayer.getPlayerName());
		panel.add(playerNameLabel);

		playerCards = new JPanel();
		playerCards.setBorder(new TitledBorder(null, "Player Cards", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(playerCards, BorderLayout.CENTER);
		playerCards.setLayout(new FlowLayout(FlowLayout.LEFT));
	}

	/** add new card if the card received in the parameter is for the player this panel is for
	 * @see controller.observable.IPlayerCardObserver
	 */
	@Override
	public void updateNextPlayerCard(Player player, PlayingCard playingCard) {
		if (player.getPlayerId().equals(panelOfPlayer.getPlayerId())) {
			playerCards.add(new CardView(playingCard));
			revalidate();
		}

	}

	/** add bust card if the card received in the parameter is for the player this panel is for
	 * @see controller.observable.IPlayerCardObserver
	 */
	@Override
	public void updateBustPlayerCard(Player player, PlayingCard playingCard) {
		if (player.getPlayerId().equals(panelOfPlayer.getPlayerId())) {
			playerCards.add(new CardView(playingCard));
			revalidate();
		}
	}

}
