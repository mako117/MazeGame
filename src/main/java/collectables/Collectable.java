package collectables;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The Collectable class represents stationary objects which can be collected to various effects.
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
	 * @param x	The <code>x</code>-coordinate of the collectable.
	 * @param y	The <code>y</code>-coordinate of the collectable.
	 * @param s	The <code>score</code> value of the collectable.
	 */
	public Collectable(int x, int y, int s, TextureRegion inputTexture) {
		this.setX(x);
		this.setY(y);
		this.setScore(s);
		this.setTextureRegion(inputTexture);
	}

	/**
	 * This method gets the <code>x</code> position for the collectable.
	 * @return The <code>x</code>-coordinate of the collectable.
	 */
	public int getX() {
		return x;
	}
	/**
	 * This method gets the <code>y</code> position for the collectable.
	 * @return The <code>y</code>-coordinate of the collectable.
	 */
	public int getY() {
		return y;
	}
	/**
	 * This method gets the <code>score</code> for the collectable.
	 * @return The <code>score</code> value of the collectable.
	 */
	public int getCollectableScore() {
		return score;
	}

	/**
	 * This method draws the collectable on the screen.
	 * @param batch	The SpriteBatch object from the screen class that is rendering everything.
	 * @param tilesize	How many pixels each coordinate difference takes up on the screen.
	 */
	public void draw(Batch batch, int tilesize) {
        batch.draw(collectableTexture, tilesize*this.getX(),  tilesize*this.getY(), tilesize, tilesize);
    }
	
	/**
     * Sets the collectable's <code>x</code>-coordinate.
     * @param inputX    Input for <code>x</code>.
     */
    protected void setX(int inputX) {
        this.x = inputX;
    }
    /**
     * Sets the collectable's <code>y</code>-coordinate.
     * @param inputY    Input for <code>y</code>.
     */
    protected void setY(int inputY) {
        this.y = inputY;
    }
	/**
	 * This method sets the <code>score</code> for the collectable.
	 * @param s Input for <code>score</code>.
	 */
	protected void setScore(int s) {
		if(s >= 0) {
			this.score = s;
		}
	}
	/**
	 * This method sets the texture for the collectable.
	 * @param inputTexture	Input for <code>collectableTexture</code>.
	 */
	protected void setTextureRegion(TextureRegion inputTexture) {
		this.collectableTexture = inputTexture;
	}
}
