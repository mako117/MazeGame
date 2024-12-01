package collectables.punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The bonus punishment in the maze game.
 */
public class BonusPunishments extends Punishments{
    private int starttime;
    private int endtime;

    /**
     * Initializes the BonusPunishment's position, starttime, and endttime to -1, and sets the <score> to 0.
     */
    public BonusPunishments(){
        super();
        this.setTime(-1, -1);
    }
    /**
     * Initializes the BonusPunishment's <x> and <y>-coordinates, <score>, <PunishmentsTexture>, <starttime>, and <endtime>.
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
     * Gets the punishment's <starttime>.
     * @return  <starttime>.
     */
    public int getStartTime() {
        return starttime;
    }
    /**
     * Gets the punishment's <endtime>.
     * @return  <endtime>.
     */
    public int  getEndTime(){
        return endtime;
    }
    
    public void setTime(int start, int end) {
    	this.starttime = start;
    	this.endtime = end;
    }
    /**
     * Sets the bonus punishment's <x>-coordinate.
     * @param inputX    Input for <x>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the bonus punishment's <y>-coordinate.
     * @param inputX    Input for <y>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method set the score for punishment.
	 * @param s
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method set the texture for punishment.
	 * @param inputTexture
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}