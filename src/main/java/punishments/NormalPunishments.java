package punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The standard punishment in the maze game.
 */
public class NormalPunishments extends Punishments{

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

}
