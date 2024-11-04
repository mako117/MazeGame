package board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The board.Wall class is used to create a wall and its interactions
 */
class Wall extends Block {
    /**
     * Creates a wall and assigns the wall a unique number
     */
    Wall() {
    	super();
        _wallNr = _wallCnt++;
        System.out.println("creating board.Wall #" + _wallNr);
    }

    /**
     * Create a wall at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param textureRegion The texture.
     */
    Wall(int x, int y, TextureRegion textureRegion){
    	super(x,y, textureRegion);
    }

    /**
     * Enter a wall.<br>
     * Returns false.
     */
    public boolean enter() {
        return false;
    }

    /**
     * Return the wall number as a string
     * 
     * @return a wall number as a String
     */
    public String toString() {
        return "board.Wall #" + _wallNr;
    }

    private int _wallNr;
    private static int _wallCnt = 1;

}