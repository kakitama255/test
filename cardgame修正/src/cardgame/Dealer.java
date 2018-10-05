package cardgame;

import java.util.ArrayList;
import java.util.List;

public class Dealer implements Playable{
	// ディーラーのデッキdealerDeckをフィールドに定義
	private List<Card> dealerDeck;

	/*
	 * コンストラクタ
	 * ディーラーの手札の空Arrayリストを生成する
	 */
	public Dealer(){
		// koyama:実害はないと思いますが、 = new ArrayList<Card>(); にしておいた方が可読性が良いです。
		// OK
		this.dealerDeck = new ArrayList<Card>();
	}

	/*
	 * @see cardgame.Play#cardHit(cardgame.CardDeck)
	 * 引数で取得した山札オブジェクトからカードを1枚ヒットし
	 * ヒットされた1枚を山札からリムーブする
	 */
	@Override
	public void cardHit(CardDeck hit) {
		Card card = hit.getDeck(0);
		this.dealerDeck.add(card);
		hit.removeCard();
	}

	/* (未使用)
	 * @see cardgame.Play#cardThrowAway(int)
	 * 自身のデッキから選択されたchooseNumber番のカードを取得し返す
	 * 取得されたカードをdealerDeckからリムーブし、捨てカードとして扱うメソッド
	 */
	@Override
	public Card cardThrowAway(int chooseNumber) {
		Card card = this.getDeckCard(chooseNumber);
		this.dealerDeck.remove(chooseNumber);
		return card;
	}

	// dealerDeck<Card>配列内のnum番のゲッター
	public Card getDeckCard(int num) {
		return this.dealerDeck.get(num);
	}

	/*
	 * @see cardgame.Play#getDeck()
	 * dealerDeck配列リストのゲッター
	 */
	@Override
	public List<Card> getDeck() {
		return this.dealerDeck;
	}
}
