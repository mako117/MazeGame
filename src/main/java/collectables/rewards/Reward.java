package collectables.rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import collectables.Collectable;

/**
 * A reward is a collectable which increases the score of any Character that enters its space.
 */
public class Reward extends Collectable {

    /**
     * Initializes position to an invalid value, and the reward's score to 0.
     */
    public Reward() {
        super();
    }
    /**
     * Initializes the reward's position, the amount it decreases the character's score by, and the texture that represents it on the board.
     * @param inputX    Input for the reward's <code>x</code>-coordinate.
     * @param inputY    Input for the reward's <code>y</code>-coordinate.
     * @param inputScore  Input for the reward's <code>score</code>.  
     * @param textureRegion Input for the reward's <code>collectableTexture</code>.
     */
    public Reward(int inputX, int inputY, int inputScore, TextureRegion textureRegion) {
        super(inputX,inputY,inputScore,textureRegion);
    }

}
