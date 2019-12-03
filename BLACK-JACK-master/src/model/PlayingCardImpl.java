package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {
	
	Suit cardSuite;
	Value cardValue;
	
	public PlayingCardImpl(Suit cardSuite, Value cardValue) {
		super();
		this.cardSuite = cardSuite;
		this.cardValue = cardValue;
	}
	
	@Override
	public Suit getSuit() {
		return this.cardSuite;
	}

	@Override
	public Value getValue() {
		return this.cardValue;
	}

	@Override
	public int getScore() {
		
		int score = -1;
		switch (this.getValue()){
			case ACE:
				score = 1;
				break;
			case TWO:
				score = 2;
				break;
			case THREE:
				score = 3;
				break;
			case FOUR:
				score = 4;
				break;
			case FIVE:
				score = 5;
				break;
			case SIX:
				score = 6;
				break;
			case SEVEN:
				score = 7;
				break;
			case EIGHT:
				score = 8;
				break;
			case NINE:
				score = 9;
				break;
			case TEN:
			case JACK:
			case QUEEN:
			case KING:
				score = 10;
				break;
		}
			
		return score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		return equals((Object) card);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardSuite == null) ? 0 : cardSuite.hashCode());
		result = prime * result + ((cardValue == null) ? 0 : cardValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PlayingCard))
			return false;
		PlayingCard other = (PlayingCard) obj;
		if (cardSuite != other.getSuit())
			return false;
		if (cardValue != other.getValue())
			return false;
		return true;
	}
	
}
