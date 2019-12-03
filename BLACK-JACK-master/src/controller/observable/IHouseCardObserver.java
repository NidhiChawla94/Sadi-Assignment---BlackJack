package controller.observable;

import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 *
 *	House Card Observer interface
 */
public interface IHouseCardObserver {

	public abstract void updateNextHouseCard(PlayingCard playingCard);
	public abstract void updateBustHouseCard(PlayingCard playingCard);
}
