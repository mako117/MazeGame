package collectables.punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The bonus punishment in the maze game.
 */
public class BonusPunishments extends Punishments{
    private int starttime;
    private int endtime;

    /**
     * Initializes the BonusPunishment's position, <code>starttime</code>, and <code>endttime</code> to -1, and sets the <code>score</code> to 0.
     */
    public BonusPunishments(){
        super();
        this.setTime(-1, -1);
    }
    /**
     * Initializes the BonusPunishment's <code>x</code> and <code>y</code>-coordinates, <code>score</code>, <code>collectabletexture</code>, <code>starttime</code>, and <code>endtime</code>.
     * @param inputX    Input for the bonus punishment's <code>x</code>-coordinate.
     * @param inputY    Input for the bonus punishment's <code>y</code>-coordinate.
     * @param inputScore  Input for the bonus punishment's <code>score</code>.  
     * @param inputTexture Input for the bonus punishment's <code>texture</code>.
     * @param startT    Input for the bonus punishment's <code>starttime</code>.
     * @param endT  Input for the bonus punishment's <code>endtime</code>.
     */
    public BonusPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture, int startT, int endT){
        super(inputX, inputY, inputScore, inputTexture);
        this.setTime(startT, endT);
    }

    /**
     * Gets the punishment's <code>starttime</code>.
     * @return  <code>starttime</code>.
     */
    public int getStartTime() {
        return starttime;
    }
    /**
     * Gets the punishment's <code>endtime</code>.
     * @return  <code>endtime</code>.
     */
    public int  getEndTime(){
        return endtime;
    }

    /**
     * Sets the punishment's <code>starttime</code> and <code>endtime</code>.
     * @param start Input for <code>starttime</code>.
     * @param end   Input for <code>endtime</code>.
     */
    public void setTime(int start, int end) {
    	this.starttime = start;
    	this.endtime = end;
    }
    /**
     * Sets the bonus punishment's <code>x</code>-coordinate.
     * @param inputX    Input for <code>x</code>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the bonus punishment's <code>y</code>-coordinate.
     * @param inputX    Input for <code>y</code>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method sets the <code>score</code> for punishment.
	 * @param s     Input for <code>score</code>.
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method sets the texture for punishment.
	 * @param inputTexture      Input for <code>collectableTexture</code>.
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}