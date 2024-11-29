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
	 * This method creates a bonus reward with x, y, score, texture, start time and end time for bonus reward
	 * @param x	Possible value for the bonus reward's <x>-coordinate.
	 * @param y	Possible value for the bonus reward's <y>-coordinate.
	 * @param s	Possible value for the bonus reward's <score>.
	 * @param inputTexture	Possible texture for the bonus reward's <RewardTexture>.
	 * @param start Possible value for the bonus reward's <starttime>.
	 * @param end	Possible value for the bonus reward's <endtime>.
	 */
	public Bonus_Reward(int x, int y, int s, TextureRegion inputTexture, int start, int end){
		super(x,y,s);
		this.Settime(start,end);
		this.setTexture(inputTexture);
	}
	
	/**
	 * This method sets the time for the bonus reward. 
	 * @param start Possible value for the bonus reward's <starttime>.
	 * @param end	Possible value for the bonus reward's <endtime>.
	 */
	public void Settime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	
	/**
	 * This method gets the <starttime> for the bonus reward.
	 * @return	<starttime>.
	 */
	public int getStarttime() {
		return starttime;
	}
	
	/**
	 * This method gets the endtime for the bonus reward.
	 * @return <endtime>
	 */
	public int getEndtime() {
		return endtime;
	}
}
