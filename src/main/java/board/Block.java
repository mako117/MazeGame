package board;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import directions.Direction;

/**
 * A block is a cell on the board.
 */
public abstract class Block {
    private int x; 
    private int y;
    private TextureRegion blockTexture;

    public Block(){
        x = -1;
        y = -1;
    }
    public Block(int x1, int y1, TextureRegion blockTexture){
        x = x1;
        y = y1;
        this.blockTexture = blockTexture;
    }

    public int getXPosition(){
        return x;
    }
    public int getYPosition(){
        return y;
    }

    public void setXPosition(int x1){
        x = x1;
    }
    public void setYPosition(int y1){
        y = y1;
    }
    public abstract boolean enter();

    public void draw(Batch batch, int tileSize) {
        batch.draw(new TextureRegion(blockTexture), tileSize*x, tileSize*y, tileSize, tileSize);
    }
}
