package cardgame;

import java.util.List;

import lib.Input;

public class Poker extends Game {
	public void gameHeader(){
		System.out.println("--------------------------------");
		System.out.println("--------------Poker-------------");
		System.out.println("--------------------------------");
		// (koyama:ここでnewしていないので名前で誤解を招く)
		// ヘッダーとした方がよい？ 暫定的に変更
		playGame();
	}

	/*
	 * @see cardgame.Games#continueGame()
	 * 再戦の確認を行い、真偽を返すための型
	 */
	@Override
	public boolean restartGame(String stg){
		int restart = 0;
		do{
			System.out.println(stg);
			restart = Input.getInt("1or0");
		}while(!(restart==0 || restart==1));
		if(restart==0)return false;
		return true;
	}

	public void playGame() {
		CardDeck cardDeck = new CardDeck();
		Player player = new Player();

		cardDeck.chargeDeck52();
		cardDeck.deckShuffle();
		int e = 0;
		while(e < 5) {
			player.cardHit(cardDeck);
			e++;
		}

		List<Card> deck = player.getDeck();

		System.out.println();
		System.out.println(deck);
		System.out.println();

		int[] numbers = rechoiceTheCard(deck);
		for(int i=0; i<deck.size(); i++){
			if(numbers[i] == 1){
				player.cardHitAddDesignation(i , cardDeck);
			}
		}

		Player copePlayer = new Player();
		List<Card> copyDeck = copePlayer.getDeck();
		for (Card d : deck) {
			copyDeck.add((new Card(d.getSuit(), d.getNumber())));
		}

		System.out.println();

		if(PokerHand.royalStraightFlash(deck)){
			System.out.println("RoyalStraightFlash");
		}else if(PokerHand.straightFlash(deck)){
			System.out.println("StraightFlash");
		}else if(PokerHand.fourOfAKind(deck)){
			System.out.println("fourOfAKind");
		}else if(PokerHand.fullHouse(deck)){
			System.out.println("fullHouse");
		}else if(PokerHand.flash(deck)){
			System.out.println("flash");
		}else if(PokerHand.straight(deck)){
			System.out.println("straight");
		}else if(PokerHand.threeOfAKind(deck)){
			System.out.println("threeOfAKind");
		}else if(PokerHand.twoPair(deck)){
			System.out.println("twoPair");
		}else if(PokerHand.pair(deck)){
			System.out.println("onepair");
		}else{
			System.out.println("buta");
		}

		System.out.println(copyDeck);
		System.out.println("-----------------↓sort↓------------------");
		System.out.println(deck);
		System.out.println();
	}

	public int chiceChangeCard(int number) {
		do{
			number = Input.getInt("change=1 / not=0");
		}while(!(number == 1 || number == 0));
		return number;
	}

	public int[] rechoiceTheCard(List<Card> deck) {
		int[] numbers;
		int number1 = 0;
		int number2 = 0;
		int number3 = 0;
		int number4 = 0;
		int number5 = 0;
		int choiceCheck = 1;

		while(choiceCheck != 0){
			System.out.println("[choice the change card(左から変えるカードを選びます)]");
			System.out.println();
			System.out.println(deck);
			numbers = new int[]{
					number1 = chiceChangeCard(number1),
					number2 = chiceChangeCard(number2),
					number3 = chiceChangeCard(number3),
					number4 = chiceChangeCard(number4),
					number5 = chiceChangeCard(number5),
					};
			int j = 0;
			System.out.println();
			System.out.println(deck);
			while(j<5){
				for(int n : numbers){
					String s = (n==1 ? "[change] " : "[AsItIs] ");
					System.out.print(s);
					j++;
				}
			}
			System.out.println();
			System.out.println("[mistake selection? yes=1 / no=0]");
			do{
				choiceCheck = Input.getInt("1or0");
			}while(!(choiceCheck == 1 || choiceCheck == 0));
			if(choiceCheck == 0) return numbers;
		}
		return numbers = new int[]{0,0,0,0,0};
	}
}
