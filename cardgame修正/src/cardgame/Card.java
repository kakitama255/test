package cardgame;
public class Card implements Comparable<Card> {
	// フィールド変数
	private String suit;
	private int number;

	// コンストラクタ
	public Card(String suit, int number){
		this.suit = suit;
		this.number = number;
	}

	/*
	 * アクセサメソッド
	 * ・getSuit() / スートの取得
	 * ・getNumber() / 数字の取得(全て数での取得)
	 * ・getNumberMark() / 数字を取得 11～13を英字で取得
	 * ・getSuitMark() / スートを(あまりよろしくないが)絵文字に変換
	 */
	public String getSuit() {
		return suit;
	}
	public int getNumber() {
		return number;
	}

	public String getNumberMark() {
		int n = this.getNumber();
		String numberMark;
		if(n == 11){
			numberMark = "J";
		}else if(n == 12){
			numberMark = "Q";
		}else if(n == 13){
			numberMark = "K";
		}else if(n == 1){
			numberMark = "A";
		}else{
			numberMark = ""+this.getNumber();
		}
		return numberMark;
	}

	public String getSuitMark() {
		String s = this.getSuit();
		String mark;
		if(s == "heart"){
			mark = "♥";
		}else if(s == "club"){
			mark = "♣";
		}else if(s == "spade"){
			mark = "♠";
		}else{
			mark = "♦";
		}
		return mark;
	}

	/*
	 * @see java.lang.Object#toString()メソッド
	 */
	@Override
	public String toString(){

		return "["+getSuitMark()+"] ["+getNumberMark()+"]";
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int compareTo(Card card) {
		return this.number - card.getNumber();
	}

}
