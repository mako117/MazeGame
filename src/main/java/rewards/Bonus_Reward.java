package rewards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bonus_Reward extends Reward {
	private int bonusNr;
	private static int bonusCnt = 1;
	private int starttime;
	private int endtime;
	
	public Bonus_Reward(){
		super(-1,-1,0);
		starttime = -1;
		endtime = -1;
		bonusNr = bonusCnt;
		bonusCnt++;
	}
	
	public Bonus_Reward(int x, int y, int s, int start, int end){
		super(x,y,s);
		starttime = start;
		endtime = end;
	}
	
	public void Settime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	
	public int getStarttime() {
		return starttime;
	}
	
	public int getEndtime() {
		return endtime;
	}
	

	
}
