package collectables.rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This standard Reward in the game; extends Reward.
 */
public class Regular_Reward extends Reward {
	
	/**
	 * This method creates a regular reward with default position and score, but no texture.
	 */
	public Regular_Reward(){
		super();
	}
	
	/**
	 * This method creates a regular reward with <code>x</code>, <code>y</code>, <code>score</code>, and <code>collectableTexture</code> for regular reward.
	 * @param x	Possible value for the regular reward's <x>-coordinate.
	 * @param y	Possible value for the regular reward's <y>-coordinate.
	 * @param s	Possible value for the regular reward's <score>.
	 * @param inputTexture	Possible texture for the regular reward's <RewardTexture>.
	 */
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s,inputTexture);
	}

	/**
     * Sets the regular reward's <code>x</code>-coordinate.
     * @param inputX    Input for <code>x</code>.
     */
    protected void setX(int inputX) {
        super.setX(inputX);
    }
	/**
     * Sets the regular reward's <code>y</code>-coordinate.
     * @param inputX    Input for <code>y</code>.
     */
    protected void setY(int inputY) {
        super.setY(inputY);
    }
	/**
	 * This method set the <code>score</code> for reward.
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
