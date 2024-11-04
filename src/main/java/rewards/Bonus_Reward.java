package rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bonus_Reward extends Reward {
	private int starttime;
	private int endtime;
	
	public Bonus_Reward(){
		super(-1,-1,0);
		starttime = -1;
		endtime = -1;
	}
	
	public Bonus_Reward(int x, int y, int s, TextureRegion inputTexture, int start, int end){
		super(x,y,s);
		starttime = start;
		endtime = end;
		this.setTexture(inputTexture);
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
