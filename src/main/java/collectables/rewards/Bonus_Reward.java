package collectables.rewards;

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
		super();
		this.setTime(-1, -1);
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
		super(x,y,s,inputTexture);
		this.setTime(start,end);
	}
	
	/**
	 * This method gets the <starttime> for the bonus reward.
	 * @return	<starttime>.
	 */
	public int getStartTime() {
		return starttime;
	}
	/**
	 * This method gets the endtime for the bonus reward.
	 * @return <endtime>
	 */
	public int getEndTime() {
		return endtime;
	}

	/**
	 * This method sets the time for the bonus reward. 
	 * @param start Possible value for the bonus reward's <starttime>.
	 * @param end	Possible value for the bonus reward's <endtime>.
	 */
	protected void setTime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	/**
     * Sets the bonus reward's <x>-coordinate.
     * @param inputX    Input for <x>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the bonus reward's <y>-coordinate.
     * @param inputX    Input for <y>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method set the score for reward.
	 * @param s
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method set the texture for reward.
	 * @param inputTexture
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}
