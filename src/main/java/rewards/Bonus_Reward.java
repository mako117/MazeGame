package rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class create the bonus reward in the game, extends from Reward
 */
public class Bonus_Reward extends Reward {
	private int starttime;
	private int endtime;
	
	/**
	 * This method use to create a bonus reward with default set
	 */
	public Bonus_Reward(){
		super(-1,-1,0);
		starttime = -1;
		endtime = -1;
	}
	
	/**
	 * This method have x, y, score, texture, start time and end time for bonus reward
	 * @param x
	 * @param y
	 * @param s
	 * @param inputTexture
	 * @param start
	 * @param end
	 */
	public Bonus_Reward(int x, int y, int s, TextureRegion inputTexture, int start, int end){
		super(x,y,s);
		starttime = start;
		endtime = end;
		this.setTexture(inputTexture);
	}
	
	/**
	 * This method set the time for bonus reward
	 * @param start
	 * @param end
	 */
	public void Settime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	
	/**
	 * This method set the start time for bonus reward
	 * @return
	 */
	public int getStarttime() {
		return starttime;
	}
	
	/**
	 * This method use to get the end time for bonus reward
	 * @return time for end
	 */
	public int getEndtime() {
		return endtime;
	}
	

	
}
