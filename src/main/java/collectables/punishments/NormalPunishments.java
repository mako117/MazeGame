package collectables.punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The standard punishment in the maze game.
 */
public class NormalPunishments extends Punishments{

    /**
	 * This method used to create a normal punishment with default position and score, and no texture.
	 */
	public NormalPunishments(){
		super();
	}

    /**
     * Initializes the normal punishment's <code>x</code> and <code>y</code>-coordinates, <code>score</code>, and <code>collectableTexture</code>.
     * @param inputX    Input for the normal punishment's <code>x</code>-coordinate.
     * @param inputY    Input for the normal punishment's <code>y</code>-coordinate.
     * @param inputScore  Input for the normal punishment's <code>score</code>.  
     * @param inputTexture Input for the normal punishment's <code>texture</code>.
     */
    public NormalPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture){
        super(inputX, inputY, inputScore, inputTexture);
    }

    /**
     * Sets the normal punishment's <code>x</code>-coordinate.
     * @param inputX    Input for <code>x</code>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the normal punishment's <code>y</code>-coordinate.
     * @param inputX    Input for <code>y</code>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method sets the <code>score</code> for punishment.
	 * @param s     Input for <code>score</code>
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method set the texture for punishment.
	 * @param inputTexture      Input for <code>collectableTexture</code>.
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}

}
