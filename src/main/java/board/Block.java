package board;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The Block class represents a cell on the board.
 */
public abstract class Block {
    private int x;
    private int y;
    private TextureRegion blockTexture;

    /**
     * Create a default new block at coordinates (-1,-1).
     */
    public Block(){
        x = -1;
        y = -1;
    }

    /**
     * Create a new block at the given coordinates.
     * @param x1 The <code>x</code> position.
     * @param y1 The <code>y</code> position.
     * @param blockTexture The block texture.
     */
    public Block(int x1, int y1, TextureRegion blockTexture){
        x = x1;
        y = y1;
        this.blockTexture = blockTexture;
    }

    /**
     * Return the X position of the block.
     * @return The <code>x</code> position.
     */
    public int getXPosition(){
        return x;
    }

    /**
     * Returns the <code>y</code> position of the block.
     * @return the <code>y</code> position.
     */
    public int getYPosition(){
        return y;
    }

    /**
     * Set the <code>x</code> position to the given point.
     * @param x1 the <code>x</code> position to change to.
     */
    public void setXPosition(int x1){
        x = x1;
    }

    /**
     * Set the <code>y</code> position to given point.
     * @param y1 the <code>y</code> position to change to.
     */
    public void setYPosition(int y1){
        y = y1;
    }

    /**
     * Draw the block on the screen.
     * @param batch the sprite batch.
     * @param tileSize the size of the tile.
     */
    public void draw(Batch batch, int tileSize) {
        batch.draw(blockTexture, tileSize*x, tileSize*y, tileSize, tileSize);
    }

    /**
     * All block objects must return whether they can be entered.
     * @return true if the object entry is possible, false otherwise.
     */
    public abstract boolean enter();
}
