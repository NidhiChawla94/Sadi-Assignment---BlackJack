package controller.observable;

import java.util.List;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * @author nidhi chawla
 * 
 * This class is responsible for storing the list of observers of the events that happen through out the application
 * This calss is also responsible for updating the observers about the event that they have registered for.
 * 
 *
 */
public interface EventObservable {

	/**Add Player Event
	Views subscribe themselves to get update about add player event by adding themselves in list of the observers through this function.**/ 
	public abstract void addToAddPlayerObserverList(IPlayerEventsObserver observer);
	/** Update the add player observer about the event and tell them about new player.* */
	public abstract void updateAddPlayerObserver(Player player);
	
	/**Player is selected event
	 * Views subscribe themselves to get update about selected player event by adding themselves in list of the observers through this function. **/
	public abstract void addSelectedPlayerObserver(IPlayerEventsObserver observer);
	/** Update the selected player observer about the event and tell them about selected player.* */
	public abstract void updateSelectedPlayerObserver(Player selectedPlayer);
	
	
	/**Place bet event
	 * Views subscribe themselves to get update about bet placed event by adding themselves in list of the observers through this function. **/
	public abstract void addToPlaceBetObserverList(IPlayerEventsObserver observer);
	/** Update the place bet observer about the event and tell them the player for which bet is placed.* */
	public abstract void updatePlaceBetObserver(Player player);
	
	
	/**Start game event
	 * Views subscribe themselves to get update about start game event by adding themselves in list of the observers through this function. **/
	public abstract void addToStartGameObserverList(IStartGameObserver observer);
	/** Update the start game observers about the event and tell them the players which are playing game.* */
	public abstract void updateStartGameObserver(List<Player> playersPlayingGame);
	
	
	/** Player has been dealt event - Called from GEC GUI to update the view of the next card and bust card of the player.
	 * Views subscribe themselves to get the next card and bust card details of the player by adding themselves in the observer list through this function.
	 * */
	public abstract void addToPlayerCardObserverList(IPlayerCardObserver observer);
	/**Update the observers about the next card of player.**/
	public abstract void updateNextCard(Player player, PlayingCard playingCard);
	/**Update the observer about the bust card of the players. **/
	public abstract void updateBustCard(Player player, PlayingCard playingCard);
	
	
	/**
	 * Player result event - Called from GEC GUI to update the view about the results of the player.
	 * Views subscribe themselves to the player result event by adding themselves to the observer list through this method.
	 * **/
	public abstract void addToPlayerResultObserver(IGameResultsObserver observer);
	/** Update player result observers**/
	public abstract void updateResult(Player player, int result);
	
	
	/**
	 * House has been dealt event - Called from GEC GUI to update the view of the next card and bust card of the house.
	 *  Views subscribe themselves to get the next card and bust card details of the house by adding themselves in the observer list through this function.
	 * **/
	public abstract void addToHouseCardsObserverList(IHouseCardObserver observer);
	/** Update the observers about next card of the house**/
	public abstract void updateNextHouseCard(PlayingCard playingCard);
	/** Update observers about bust card of house.***/
	public abstract void updateBustHouseCard(PlayingCard playingCard);
	
	
	/**
	 * House result event - Called from GEC GUI to update the view about the results of the House.
	 * Views subscribe themselves to the house result event by adding themselves to the observer list through this method.
	 * ***/
	public abstract void addToHouseResultObserver(IGameResultsObserver observer);
	/** Update house result observers**/
	public abstract void updateHouseResult(int result);
	
	
}
