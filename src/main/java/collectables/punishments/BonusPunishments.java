package collectables.punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The bonus punishment in the maze game.
 */
public class BonusPunishments extends Punishments{
    private int starttime;
    private int endtime;

    /**
     * Initializes the BonusPunishment's position, start time, and end time to -1, and sets the score to 0.
     */
    public BonusPunishments(){
        super();
        this.setTime(-1, -1);
    }
    /**
     * Initializes the BonusPunishment's x and y-coordinates, score, PunishmentsTexture, starttime, and endtime.
     * @param inputX    Input for the punishment's x-coordinate.
     * @param inputY    Input for the punishment's y-coordinate.
     * @param inputScore    Input for the punishment's score. 
     * @param inputTexture  Input for the punishment's texture.
     * @param startT    Input for the punishment's start time.
     * @param endT  Input for the punishment's end time.
     */
    public BonusPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture, int startT, int endT){
        super(inputX, inputY, inputScore, inputTexture);
        this.setTime(startT, endT);
    }

    /**
     * Gets the punishment's start time.
     * @return  the start time.
     */
    public int getStartTime() {
        return starttime;
    }
    /**
     * Gets the punishment's end time.
     * @return  the end time.
     */
    public int  getEndTime(){
        return endtime;
    }

    /**
     * Setter for the time
     * @param start the start time of the punishment
     * @param end the end time of the punish,ent
     */
    public void setTime(int start, int end) {
    	this.starttime = start;
    	this.endtime = end;
    }
    /**
     * Sets the bonus punishment's x-coordinate.
     * @param inputX    Input for x.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the bonus punishment's y-coordinate.
     * @param inputY    Input for y.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method set the score for punishment.
	 * @param s the score for the punishment
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method set the texture for punishment.
	 * @param inputTexture the texture for the punishment
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}