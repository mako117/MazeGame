package rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class create the regular reward in the game, extends Reward
 */
public class Regular_Reward extends Reward {
	
	/**
	 * This method use to create regular reward with default set
	 */
	public Regular_Reward(){
		super(-1,-1,0);
	}
	
	/**
	 * This method set x, y, score, texture for regular reward
	 * @param x
	 * @param y
	 * @param s
	 * @param inputTexture
	 */
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s);
		this.setTexture(inputTexture);
	}
}
