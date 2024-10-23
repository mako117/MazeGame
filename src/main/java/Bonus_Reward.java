
public class Bonus_Reward extends Reward {
	private int bonusNr;
	private static int bonusCnt = 1;
	private int starttime;
	private int endtime;
	
	Bonus_Reward(){
		super(-1,-1,0);
		starttime = -1;
		endtime = -1;
		bonusNr = bonusCnt;
		bonusCnt++;
	}
	
	Bonus_Reward(int x, int y, int s, int start, int end){
		super(x,y,s);
		starttime = start;
		endtime = end;
	}
	
	void Settime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	
	int getStarttime() {
		return starttime;
	}
	
	int getEndtime() {
		return endtime;
	}
	
	
	
}
