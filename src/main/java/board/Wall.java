package board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The board.Wall class is used to create a wall and its interactions.
 */
public class Wall extends Block {
    /**
     * Creates a wall and assigns the wall a unique number.
     */
    public Wall() {
    	super();
        _wallNr = _wallCnt++;
        System.out.println("creating board.Wall #" + _wallNr);
    }

    /**
     * Create a wall at the given coordinates.
     * @param x The <code>x</code> coordinate.
     * @param y The <code>y</code> coordinate.
     * @param textureRegion The texture.
     */
    public Wall(int x, int y, TextureRegion textureRegion){
    	super(x,y, textureRegion);
    }

    /**
     * Enter a wall.<br>
     * Returns false.
     */
    public boolean enter() {
        return false;
    }



    private int _wallNr;
    private static int _wallCnt = 1;

}