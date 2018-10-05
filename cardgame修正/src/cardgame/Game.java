package cardgame;

// 抽象クラスGames(再戦、ゲーム再選択を行わせるための親クラス)
// memo:単数の方が自然？
public abstract class Game {
	// (koyama:1ゲーム分のひな形クラスなのに何故Gamesと複数形になるのか？)

//	// 再戦用、ゲーム再選択用の共通フィールド変数を定義
//	private boolean contniue;
//	// koyama:continueのスペルミス？　だとしたら予約語と同じなのでNG。そうではないのであれば紛らわしいのでNG。
//	// そもそも続行判定にフラグを使う必要があるのか？
//	private boolean otherGame;
//	// koyama:こちらもフラグを使う必要があるのか検討する

	/*
	 *  ・gameHeader() / ゲームのヘッダー、タイトルの表示
	 *  ・playGame() / カードゲームそれぞれの動作本体
	 *  ・restartGame() / 再戦と終了、ゲーム再選択と終了の選択
	 */
	public abstract void gameHeader();
	// koyama:ゲームをスタートさせるメソッドなのでクラス名がnewなのはおかしい。各ゲームクラスをnewしているのはmainメソッドなので。
	public abstract void playGame();
	public abstract boolean restartGame(String stg);
	// koyama:continueなら今ゲーム中のものを継続させるという意味になる。再度ゲームをはじめからやるのであればrestartとかでは？
	// koyama:フラグを使わずにこのメソッドの戻り値がtrueならゲームを続けるという方が意味は通り易い。
	// アドバイスに従って変更

}
