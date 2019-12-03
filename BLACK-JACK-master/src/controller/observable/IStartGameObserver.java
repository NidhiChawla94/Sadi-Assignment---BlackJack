package controller.observable;

import java.util.List;

import model.interfaces.Player;

/**
 * @author nidhi chawla
 *
 *Start game observer interface
 */
public interface IStartGameObserver {
	public abstract void gameStartedObserved(List<Player> playersPlayingGame);
}
