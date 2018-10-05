package cardgame;
import java.util.List;

import lib.Input;
public class BlackJack extends Game {

	/*
	 * @see cardgame.Games#newGame()
	 */
	@Override
	public void gameHeader(){
		System.out.println("--------------------------------");
		System.out.println("------------BlackJack-----------");
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

	/*
	 * @see cardgame.Games#playGame()
	 * ゲーム本体の実行動作を格納しているメソッド
	 * もう少し細かくメソッドに処理を任せることを考慮
	 */
	@Override
	public void playGame(){
		// ブラックジャック用の山札と手札を生成
		CardDeck cardDeck = new CardDeck();
		Player player01 = new Player();
		Dealer dealer = new Dealer();

		/*
		 * プレイヤー、ディーラ用の手札の合計値
		 * ヒットするかどうかの判定用の変数を定義
		 */
		int playerSumNumber = 0;
		int dealerSumNumber = 0;
		int hitCheck = 0;

		// 山札に52枚チャージ、それをシャッフル
		cardDeck.chargeDeck52();
		cardDeck.deckShuffle();

		dealer.cardHit(cardDeck);	// ディーラが1枚引く(本来のBJは伏せカード1枚、見せカード1枚をHIT)

		player01.cardHit(cardDeck);	// プレイヤーが2枚引く
		player01.cardHit(cardDeck);

		// 繰り返し処理部(プレイヤーHit中)
		do{
			//ナチュラルBJかを手札が2枚の時に判定
			if(player01.getDeck().size() <= 2){
				if(naturalBjCheck(player01)) break;
			}

			// 合計値が21の場合処理を抜ける
			if(playerSumNumber == 21) break;

			bjFiled(dealer);							// dealerの手札を表示
			bjFiled(player01);							// プレイヤーの手札を表示
			if(burstCheck(playerSumNumber)) break;		// バーストしていたら処理を抜ける
			hitCheck = 0;								// hitCheck変数をリセット
		while(!(hitCheck == 1 || hitCheck == 2)){		// hitCheck変数が1か2なら処理を抜ける
			System.out.println("Hit=1 or Stand=2"); 	// 1ならhit、2ならstand
			hitCheck = Input.getInt();
		}
		if(hitCheck == 1) player01.cardHit(cardDeck);	// hitが選択された場合、カードを1枚引く
		playerSumNumber = sumCheak(player01);			// 現在の手札の合計値を処理
		}while(hitCheck != 2);							// 2のスタンドが選択された場合、処理を抜ける


		if (burstCheck(playerSumNumber)) {
			// プレイヤーの手札がバーストしていた場合は負け
			System.out.println("[You Lose!!]");
		} else {

			// プレイヤーがバーストしていなかった場合は処理を続行
			// ディーラーの現時点での手札の合計値を計算
			dealerSumNumber = sumCheak(dealer);

			// (ルール)ディーラーは17以上になるまでカードを引く
			while (dealerSumNumber < 17) {
				dealer.cardHit(cardDeck);
				dealerSumNumber = sumCheak(dealer);
			}

			// 両者の手札を表示
			bjFiled(dealer);
			bjFiled(player01);

			// ナチュラルブラックジャックかを判定
			if (naturalBjCheck(player01)) {
				if (naturalBjCheck(dealer)) {
					// ディーラーもNaBJなら引き分け
					System.out.println("[Drow Game]");
				} else {
					// でなければ勝利
					System.out.println("[Natural BlackJack Win!!]");
				}
			} else if (playerSumNumber == 21) {
				if (naturalBjCheck(dealer)) {
					// ディーラーがNaBJなら負け
					System.out.println("[You Lose!!]");
				}else if (dealerSumNumber == 21) {
					// ディーラーもBJなら引き分け
					System.out.println("[Drow Game]");
				} else {
					// でなければ勝利
					System.out.println("[BlackJack Win!!]");
				}
			} else if (burstCheck(dealerSumNumber)) {
				// ディーラーがバーストしていたらプレイヤーの勝ち
				System.out.println("[You Win!!]");
			} else if (playerSumNumber > dealerSumNumber) {
				// ディーラーより合計値が大きかったらプレイヤーの勝ち
				System.out.println("[You Win!!]");
			} else if (playerSumNumber == dealerSumNumber) {
				naturalBjCheck(player01);

				// 手札の数字が同じだったら引き分け
				System.out.println("[Drow Game]");
			} else {
				// それ以外は負け
				System.out.println("[You Lose!!]");
			}
		}

	}

	// 手札を表示するメソッド
	public void bjFiled(Playable members){
			System.out.println();
			// オブジェクトの一致によりコンソールの表示を変更
			if(members instanceof Dealer) System.out.println("[Dealers Card]");
			if(members instanceof Player) System.out.println("[Players Card]");
			// 拡張for文で手札をコンソールに表示(toString)
			for(Card c: members.getDeck()) System.out.println(c);
	}

	// 合計値の計算をするメソッド
	public int sumCheak(Playable members){
		int sum = 0;
		// localDeck変数にmembers.getDeck()で手札の配列を代入
		List<Card> localDeck = members.getDeck();
		// 拡張for文で取り出して合計する
		for(Card c: localDeck){
			// 11以上は+10に直す それ以外はそのまま代入
			if(c.getNumber() >= 11) sum += 10;
			if(c.getNumber() < 11) sum += c.getNumber();
		}

		/*
		 *  配列内にエースがあり、かつ12未満の場合は更に+10をして帳尻を合わせる
		 *  (ルール：エースは11としても扱える)
		 */
		for(Card c: localDeck){
			if(sum < 12){
				if(c.getNumber() == 1) sum += 10;
			}
		}
		return sum;
	}

	// バースト(21より大きい)かをチェックするメソッド
	public boolean burstCheck(int sumNumber){
		return sumNumber > 21;
	}

	// ナチュラルブラックジャック(エース+10以上)をチェックするメソッド
	public boolean naturalBjCheck(Playable members){
		// 初期手札の2枚(要素0と1)を変数へ
		Card card0 = members.getDeckCard(0);
		Card card1 = members.getDeckCard(1);
		int check0 = card0.getNumber();
		int check1 = card1.getNumber();

		// どちらかが1で、どちらかが10以上であればtrue
		if(check0 == 1 && check1 >= 10) return true;
		if(check1 == 1 && check0 >= 10) return true;
		return false;
	}

}
