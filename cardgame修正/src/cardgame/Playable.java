package cardgame;

import java.util.List;

/* hitなどの共通動作、共通取得をインターフェイスplayとして定義 */
public interface Playable {

	// カードをヒットする ×
	// koyama:「ヒットする」の意味が曖昧です。明確に各ゲームごとにプレーヤ等がここで何をすべきか記載してください。
	// カードを(山札などから)引く
	void cardHit(CardDeck hit);

	// カードを手札から(任意で)出す、捨てる
	// koyama:このメソッドの説明は誤解されにくいですね。とてもいいです。
	Card cardThrowAway(int chooseNumber);

	// ゲッター・getDeck()を明示的に各クラスに定義
	List<Card> getDeck();

	// ゲッター・getDeckCard()を明示的に各クラスに定義
	Card getDeckCard(int num);

}
