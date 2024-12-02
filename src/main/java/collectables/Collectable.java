package collectables;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The public Collectable parent class for Collectables
 */
public class Collectable {
    private int x;
    private int y;
    private int score;
    private TextureRegion collectableTexture;

    /**
     * Initializes position to an invalid value, and the collectable score to 0.
     */
	public Collectable() {
		this.setX(-1);
		this.setY(-1);
		this.setScore(0);
	}
	
	/**
	 * This method use x, y, score to set the collectable.
	 * @param x The collectibles x position
	 * @param y The collectibles y position
	 * @param s the collectibles score
	 * @param inputTexture the collectibles texture
	 */
	public Collectable(int x, int y, int s, TextureRegion inputTexture) {
		this.setX(x);
		this.setY(y);
		this.setScore(s);
		this.setTextureRegion(inputTexture);
	}

	/**
	 * This method get the x position for the collectable.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * This method get the y position for the collectable.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * This method get the score for the collectable.
	 * @return score
	 */
	public int getCollectableScore() {
		return score;
	}

	/**
	 * This method draw the collectable in game.
	 * @param batch the batch for drawing the collectable
	 * @param tilesize the tile size of the collectable
	 */
	public void draw(Batch batch, int tilesize) {
        batch.draw(collectableTexture, tilesize*this.getX(),  tilesize*this.getY(), tilesize, tilesize);
    }
	
	/**
     * Sets the collectable's x-coordinate.
     * @param inputX    Input for x.
     */
    protected void setX(int inputX) {
        this.x = inputX;
    }
    /**
     * Sets the collectable's y-coordinate.
     * @param inputY    Input for y.
     */
    protected void setY(int inputY) {
        this.y = inputY;
    }
	/**
	 * This method set the score for the collectable.
	 * @param s the score set for the collectable
	 */
	protected void setScore(int s) {
		if(s >= 0) {
			this.score = s;
		}
	}
	/**
	 * This method set the texture for the collectable.
	 * @param inputTexture the texture for the collectable
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		this.collectableTexture = inputTexture;
	}
}
