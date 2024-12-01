package rewards;

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
	 * @param x	Possible value for the regular reward's <x>-coordinate.
	 * @param y	Possible value for the regular reward's <y>-coordinate.
	 * @param s	Possible value for the regular reward's <score>.
	 * @param inputTexture	Possible texture for the regular reward's <RewardTexture>.
	 */
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s,inputTexture);
	}
}
