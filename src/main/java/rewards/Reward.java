package rewards;

public class Reward {
	private int x;
	private int y;
	private int score;
	
	public Reward(int x, int y, int s){
		this.x = x;
		this.y = y;
		this.score = s;
	}
	
	public void Set_score(int s) {
		this.score = s;
	}
	
	public void Set_poistion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int Xposition() {
		return x;
	}
	
	public int Yposition() {
		return y;
	}
	
	public int getPoint() {
		return score;
	}
}
