package collectables.punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The standard punishment in the maze game.
 */
public class NormalPunishments extends Punishments{

    /**
	 * This method used to create a normal punishment with default position and score, and no texture
	 */
	public NormalPunishments(){
		super();
	}

    /**
     * Initializes the NormalPunishment's <x> and <y>-coordinates, <score>, and <PunishmentsTexture>.
     * @param inputX    Input for the punishment's x-coordinate.
     * @param inputY    Input for the punishment's y-coordinate.
     * @param inputScore    Input for the punishment's score. 
     * @param inputTexture  Input for the punishment's texture.
     */
    public NormalPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture){
        super(inputX, inputY, inputScore, inputTexture);
    }

    /**
     * Sets the normal punishment's <x>-coordinate.
     * @param inputX    Input for <x>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the normal punishment's <y>-coordinate.
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
