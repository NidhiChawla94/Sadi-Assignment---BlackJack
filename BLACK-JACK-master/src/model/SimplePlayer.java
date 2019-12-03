package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	String playerName;
	String playerId;
	int points;
	int bet;
	int result;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.playerName = playerName;
		this.playerId = playerId;
		this.points = initialPoints;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
		
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
		
	}

	@Override
	public String getPlayerId() {
		return this.playerId;
	}

	@Override
	public boolean placeBet(int bet) {
		if(bet>=0 && bet<= points){
			this.bet = bet;
			return true;
		}else{
			this.bet =0;
			return false;	
		}
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
	}

	@Override
	public int getResult() {
		return this.result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
		
	}

}
