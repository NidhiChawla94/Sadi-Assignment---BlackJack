package controller.observable;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 *
 *	Player card observer interface
 */
public interface IPlayerCardObserver {

	public void updateNextPlayerCard(Player player, PlayingCard playingCard);	
	public void updateBustPlayerCard(Player player, PlayingCard playingCard);	
}
