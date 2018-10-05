package cardgame;

import lib.Input;

public class GameRun {

	public static void main(String[] args) {
		// ゲーム選択用のオブジェクト
		Game game = choiceGame();
		runAndEnd(game);

		System.out.println();
		System.out.println("GameEnd");
	}

	public static Game choiceGame() {
		Game game;
		System.out.println("--------------------------------");
		System.out.println("----------ChoiceTheGame---------");
		System.out.println("--------------------------------");
		System.out.println();
		System.out.println("1,BlackJack / 2.Poker...");
		System.out.println();

		// ゲームを選択
		int choiceGame = Input.getInt("選択1or2");

		// ゲームをスイッチで分岐(defalut含めて後々改良の余地あり)
		switch(choiceGame){
			case 1:
				game = new BlackJack();
				break;
			case 2:
				game = new Poker();
				break;
			default:
				game = new BlackJack();
		}
		return game;
	}

	public static void runAndEnd(Game game) {
		game.gameHeader();
		boolean cheak = true;
		do{
			if(game.restartGame("restart? yes=1 / no=0")) {
				game.playGame();
			}else if(cheak = game.restartGame("back to ChoiceTheGame?  yes=1 / no=0")){
				game = choiceGame();
				game.gameHeader();
			}
		}while(cheak);
	}
}
