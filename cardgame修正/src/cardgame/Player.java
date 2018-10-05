package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Player implements Playable {
	// 自身の手札を定義
	private List<Card> myDeck;

	// (未定義)自身の持ち金(予定)

	/*
	 * コンストラクタ
	 * 自身の手札を生成する
	 */
	public Player(){
		this.myDeck = new ArrayList<>();
	}

	public void cardHitAddDesignation(int i, CardDeck hit) {
		Card card = hit.getDeck(0);
		this.myDeck.set(i, card);
		hit.removeCard();
	}

	/*
	 * @see cardgame.Play#cardHit(cardgame.CardDeck)
	 * 引数で取得した山札オブジェクトからカードを1枚引き
	 * 引かれたカード1枚を山札から排除する
	 */
	@Override
	public void cardHit(CardDeck hit) {
		Card card = hit.getDeck(0);
		this.myDeck.add(card);
		hit.removeCard();
	}

	/*
	 * @see cardgame.Play#cardThrowAway(int)
	 * 自身のデッキから選択されたchooseNumber番のカードを取得し返す
	 * 取得されたカードをdealerDeckからリムーブし、捨てカードとして扱うメソッド
	 */
	@Override
	public Card cardThrowAway(int chooseNumber) {
		Card card = this.getDeckCard(0);
		this.myDeck.remove(0);
		return card;
	}

	// myDeck<Card>配列内のnum番のゲッター
	@Override
	public Card getDeckCard(int num) {
		return this.myDeck.get(num);
	}

	/*
	 * @see cardgame.Play#getDeck()
	 * myDeck配列リストのゲッター
	 */
	@Override
	public List<Card> getDeck(){
		return myDeck;
	}

}
