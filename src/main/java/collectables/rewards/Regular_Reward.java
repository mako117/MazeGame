package collectables.rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class creates the regular reward in the game, extends Reward
 */
public class Regular_Reward extends Reward {
	
	/**
	 * This method used to create a regular reward with default position and score, and no texture
	 */
	public Regular_Reward(){
		super();
	}
	
	/**
	 * This method creates a regular reward with x, y, score, texture for regular reward
	 * @param x	Possible value for the regular reward's x-coordinate.
	 * @param y	Possible value for the regular reward's y-coordinate.
	 * @param s	Possible value for the regular reward's score.
	 * @param inputTexture	Possible texture for the regular reward's RewardTexture.
	 */
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s,inputTexture);
	}

	/**
     * Sets the regular reward's x-coordinate.
     * @param inputX    Input for x.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the regular reward's y-coordinate.
     * @param inputY    Input for y.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method set the score for reward.
	 * @param s the score for the reward
	 */
	protected void setScore(int s) {
		super.setScore(s);
	}
	/**
	 * This method set the texture for reward.
	 * @param inputTexture the texture for the reward
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		super.setTextureRegion(inputTexture);
	}
}
