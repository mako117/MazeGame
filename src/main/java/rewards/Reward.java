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
     * Initializes position to an invalid value, and the punishment score to 0.
     */
	public Reward() {
		this.setX(-1);
		this.setY(-1);
		this.setScore(0);
	}
	
	/**
	 * This method use x, y, score to set reward.
	 * @param x
	 * @param y
	 * @param s
	 */
	public Reward(int x, int y, int s, TextureRegion inputTexture) {
		this.setX(x);
		this.setY(y);
		this.setScore(s);
		this.setTextureRegion(inputTexture);
	}

	/**
	 * This method get the x position for reward.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * This method get the y position for reward.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * This method get the score for reward.
	 * @return score
	 */
	public int getRewardScore() {
		return score;
	}

	/**
	 * This method draw the reward in game.
	 * @param batch
	 * @param tilesize
	 */
	public void draw(Batch batch, int tilesize) {
        batch.draw(RewardTexture, tilesize*this.getX(),  tilesize*this.getY(), tilesize, tilesize);
    }
	
	/**
     * Sets the reward's <x>-coordinate.
     * @param inputX    Input for <x>.
     */
    protected void setX(int inputX) {
        this.x = inputX;
    }
    /**
     * Sets the reward's <y>-coordinate.
     * @param inputY    Input for <y>.
     */
    protected void setY(int inputY) {
        this.y = inputY;
    }
	/**
	 * This method set the score for reward.
	 * @param s
	 */
	protected void setScore(int s) {
		if(s >= 0) {
			this.score = s;
		}
	}
	/**
	 * This method set the texture for reward.
	 * @param inputTexture
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		this.RewardTexture = inputTexture;
	}
}
