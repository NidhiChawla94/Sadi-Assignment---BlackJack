package controller.observable;

import model.interfaces.Player;
/**
 * @author nidhi chawla
 *
 *	Game result Observer Interface  
 */
public interface IGameResultsObserver {

	public abstract void updatePlayerResult(Player player, int result);
	public abstract void updateHouseResult(int result);
}
