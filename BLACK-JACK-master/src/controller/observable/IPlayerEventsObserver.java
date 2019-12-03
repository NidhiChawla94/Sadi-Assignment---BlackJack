package controller.observable;

import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 *	Observer interface for events such as add player, place bet, selected player
 */
public interface IPlayerEventsObserver {

	public abstract void updateView(Player player, String eventName);
}
