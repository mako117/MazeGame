package rewards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This class create reward in game.
 */
public class Reward {
	private int x;
	private int y;
	private int score;
	private TextureRegion RewardTexture;
	
	/**
	 * This method use x, y, score to set reward.
	 * @param x
	 * @param y
	 * @param s
	 */
	public Reward(int x, int y, int s) {
		this.Set_position(x, y);
		this.Set_score(s);
	}
	
	/**
	 * This method set the score for reward.
	 * @param s
	 */
	public void Set_score(int s) {
		if(s >= 0) {
			this.score = s;
		}
	}
	
	/**
	 * This method set the x, y position for reward.
	 * @param x
	 * @param y
	 */
	public void Set_position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This method get the x position for reward.
	 * @return x
	 */
	public int Xposition() {
		return x;
	}
	
	/**
	 * This method get the y position for reward.
	 * @return y
	 */
	public int Yposition() {
		return y;
	}
	
	/**
	 * This method get the score for reward.
	 * @return score
	 */
	public int getPoint() {
		return score;
	}

	/**
	 * This method set the texture for reward.
	 * @param inputTexture
	 */
	protected void setTexture(TextureRegion inputTexture) {
		this.RewardTexture = inputTexture;
	}
	
	/**
	 * This method draw the reward in game.
	 * @param batch
	 * @param tilesize
	 */
	public void draw(Batch batch, int tilesize) {
        batch.draw(RewardTexture, tilesize*this.Xposition(),  tilesize*this.Yposition(), tilesize, tilesize);
    }
}
