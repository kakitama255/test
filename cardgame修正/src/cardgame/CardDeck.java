package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
	// 山札(deck)をフィールドに定義
	private List<Card> deck;

	/*
	 * コンストラクタ
	 * 山札の空Arrayリストを生成する
	 */
	public CardDeck() {
		this.deck = new ArrayList<>();
	}

	// 山札のnum番のカードのゲッター
	public Card getDeck(int num) {
		return this.deck.get(num);
	}

	// 山札配列リストのゲッター
	public List<Card> getFiledDeck() {
		return this.deck;
	}

	/*
	 * ジョーカー抜きの52枚の山札を、スートの1ずつチャージしていく
	 * 山札をシャッフルしたい場合はdeckShuffle()を使用する
	 */
	// (koyama:この時点では綺麗に並んでいて、別のシャッフルメソッドを使うように指示のコメントが必要。)
	// OK
	public void chargeDeck52() {
		for (int i = 1; i <= 13; i++) {
			this.deck.add(new Card("spade", i));
			this.deck.add(new Card("club", i));
			this.deck.add(new Card("heart", i));
			this.deck.add(new Card("dia", i));

			// koyama:こんな感じでも可読性は落ちません
//			this.deck.add(new Card("dia", i));
			// OK
		}
	}
	public void chargeDeckSpade() {
		for (int i = 1; i <= 13; i++) {
			this.deck.add(new Card("spade", i));
		}
	}

	/* (未使用)
	 * 山札の先頭の一枚を除外
	 */
	public void removeCard(){
		this.deck.remove(0);
	}

	/*
	 * 山札の配列をシャッフルする
	 */
	public void deckShuffle(){
		Collections.shuffle(this.deck);
	}
}
