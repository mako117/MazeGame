package collectables.rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The bonus reward in the maze game.
 */
public class Bonus_Reward extends Reward {
	private int starttime;
	private int endtime;
	
	/**
	 * This method creates a bonus reward with default Reward constructor, and sets start and end times to -1.
	 */
	public Bonus_Reward(){
		super();
		this.setTime(-1, -1);
	}
	
	/**
	 * This method creates a bonus reward with x, y, score, texture, start time and end time.
	 * @param x	Possible value for the bonus reward's <code>x</code>-coordinate.
	 * @param y	Possible value for the bonus reward's <code>y</code>-coordinate.
	 * @param s	Possible value for the bonus reward's <code>score</code>.
	 * @param inputTexture	Possible texture for the bonus reward's <code>collectableTexture</code>.
	 * @param start Possible value for the bonus reward's <code>starttime</code>.
	 * @param end	Possible value for the bonus reward's <code>endtime</code>.
	 */
	public Bonus_Reward(int x, int y, int s, TextureRegion inputTexture, int start, int end){
		super(x,y,s,inputTexture);
		this.setTime(start,end);
	}
	
	/**
	 * This method gets the starting time for the bonus reward.
	 * @return	<code>starttime</code>.
	 */
	public int getStartTime() {
		return starttime;
	}
	/**
	 * This method gets the end time for the bonus reward.
	 * @return <code>endtime</code>
	 */
	public int getEndTime() {
		return endtime;
	}

	/**
	 * This method sets the time for the bonus reward. 
	 * @param start Possible value for the bonus reward's <code>starttime</code>.
	 * @param end	Possible value for the bonus reward's <code>endtime</code>.
	 */
	protected void setTime(int start, int end) {
		this.starttime = start;
		this.endtime = end;
	}
	/**
     * Sets the bonus reward's <code>x</code>-coordinate.
     * @param inputX    Input for <code>x</code>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the bonus reward's <code>y</code>-coordinate.
     * @param inputY    Input for <code>y</code>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * Sets the bonus reward's <code>score</code>.
	 * @param s		Input for <code>score</code>.
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * Sets the bonus reward's texture.
	 * @param inputTexture	Input for <code>collectableTexture</code>.
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}
