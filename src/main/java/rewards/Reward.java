package rewards;

public class Reward {
	private int x;
	private int y;
	private int score;
	
	Reward(int x, int y, int s){
		this.x = x;
		this.y = y;
		this.score = s;
	}
	
	void Set_score(int s) {
		this.score = s;
	}
	
	void Set_poistion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	int Xposition() {
		return x;
	}
	
	int Yposition() {
		return y;
	}
	
	int getPoint() {
		return score;
	}
}
