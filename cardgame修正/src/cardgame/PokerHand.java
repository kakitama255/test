package cardgame;

import static java.util.Comparator.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class PokerHand {
	public static void main(String[] args) {
		CardDeck cardDeck = new CardDeck();
		Player player01 = new Player();

		cardDeck.chargeDeck52();
		cardDeck.deckShuffle();
		int i = 0;
		while(i < 5) {
			player01.cardHit(cardDeck);
			i++;
		}
		List<Card> deck = player01.getDeck();
		if(royalStraightFlash(deck)){
			System.out.println("RoyalStraightFlash");
		}else if(straightFlash(deck)){
			System.out.println("StraightFlash");
		}else if(fourOfAKind(deck)){
			System.out.println("fourOfAKind");
		}else if(fullHouse(deck)){
			System.out.println("fullHouse");
		}else if(flash(deck)){
			System.out.println("flash");
		}else if(straight(deck)){
			System.out.println("straight");
		}else if(threeOfAKind(deck)){
			System.out.println("threeOfAKind");
		}else if(twoPair(deck)){
			System.out.println("twoPair");
		}else if(pair(deck)){
			System.out.println("onepair");
		}else{
			System.out.println("buta");
		}
		System.out.println(deck);
	}

	public static boolean royalStraightFlash(List<Card> deck) {
		deck.sort(naturalOrder());
		if (flash(deck)) {
			if (deck.get(0).getNumber() == 1) {
				int ten = 10;
				int j = 0;
				for(int i=1;i < deck.size() ; i++){
					if(ten++ == deck.get(i).getNumber()){
						j++;
					}
				}

				if (j == 4) return true;
			}
		}
		return false;
	}

	public static boolean straightFlash(List<Card> deck) {
		return straight(deck) && flash(deck);
	}

	public static boolean straight(List<Card> deck) {
		deck.sort(naturalOrder());
		int b = deck.get(0).getNumber();
		int i = 0;
		for(Card card : deck) {
			if(b++ == card.getNumber()){
				i++;
			}else{
				break;
			}
			if(i == 5) return true;
		}
		return false;
	}

	public static boolean flash(List<Card> deck) {
		if(deck.stream().allMatch(s -> s.getSuit().equals("spade"))) 	return true;
		if(deck.stream().allMatch(s -> s.getSuit().equals("club"))) 	return true;
		if(deck.stream().allMatch(s -> s.getSuit().equals("heart"))) 	return true;
		if(deck.stream().allMatch(s -> s.getSuit().equals("dia"))) 		return true;
		return false;
	}

	public static boolean fourOfAKind(List<Card> deck) {
		deck.sort(naturalOrder());
		Stream<Card> stream = deck.stream().filter
				(num -> deck.get(0).getNumber() == num.getNumber());
		if(stream.count() == 4) return true;

		Stream<Card> stream2 = deck.stream().filter
				(num ->deck.get(1).getNumber() == num.getNumber());
		if(stream2.count() == 4) return true;

		return false;
	}

	public static boolean fullHouse(List<Card> deck) {
		return threeOfAKind(deck) && pair(deck);
	}


	public static boolean threeOfAKind(List<Card> deck) {
		deck.sort(naturalOrder());
		Stream<Card> stream = deck.stream().filter
				(num -> deck.get(0).getNumber() == num.getNumber());
		if(stream.count() == 3) return true;

		Stream<Card> stream2 = deck.stream().filter
				(num ->deck.get(1).getNumber() == num.getNumber());
		if(stream2.count() == 3) return true;

		Stream<Card> stream3 = deck.stream().filter
				(num ->deck.get(2).getNumber() == num.getNumber());
		if(stream3.count() == 3) return true;
		return false;
	}

	public static boolean twoPair(List<Card> deck) {
		deck.sort(naturalOrder());
		int number1 = deck.get(0).getNumber();
		int number2 = deck.get(1).getNumber();
		int number3 = deck.get(2).getNumber();
		int number4 = deck.get(3).getNumber();
		int number5 = deck.get(4).getNumber();
		if(number1 == number2){
			if(number3 == number4 || number4 == number5){
				return true;
			}
		}else if(number2 == number3){
			if(number4 == number5){
				return true;
			}
		}
		return false;
	}

	public static boolean pair(List<Card> deck) {
		deck.sort(naturalOrder());
		Stream<Card> stream = deck.stream().filter
				(num ->deck.get(0).getNumber() == num.getNumber());
		List<Card> cheackSize = stream.collect(Collectors.toList());
		if(cheackSize.size() == 2 && cheackSize.size() < 3) return true;

		Stream<Card> stream2 = deck.stream().filter
				(num ->deck.get(1).getNumber() == num.getNumber());
		cheackSize = stream2.collect(Collectors.toList());
		if(cheackSize.size() == 2 && cheackSize.size() < 3) return true;

		Stream<Card> stream3 = deck.stream().filter
				(num ->deck.get(2).getNumber() == num.getNumber());
		cheackSize = stream3.collect(Collectors.toList());
		if(cheackSize.size() == 2 && cheackSize.size() < 3) return true;

		Stream<Card> stream4 = deck.stream().filter
				(num ->deck.get(3).getNumber() == num.getNumber());
		cheackSize = stream4.collect(Collectors.toList());
		if(cheackSize.size() == 2 && cheackSize.size() < 3) return true;
		return false;
	}

}
