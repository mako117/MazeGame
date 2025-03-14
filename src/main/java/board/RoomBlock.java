package board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The board.RoomBlock class creates room blocks for the maze.
 */
public class RoomBlock extends Block {
    /**
     * Creates a Room and assigns it a unique room number
     */
    public RoomBlock() {
        super();
        _roomBlockNr = _roomBlockCnt++;
//        System.out.println("Creating Room #" + _roomBlockNr);

    }

    /**
     * Creates a Room with a unique room number and a set position
     * 
     * @param x_pos The <code>x</code> position of the block.
     * @param y_pos The <code>y</code> position of the block.
     * @param texture The texture of the block.
     */
    public RoomBlock( int x_pos, int y_pos, TextureRegion texture) {
        super(x_pos, y_pos, texture);
        _roomBlockNr = _roomBlockCnt++;
    }

    @Override
    /**
     * Entering into a room block is always successful.
     * 
     * @return true
     */
    public boolean enter() {
        return true;
    }
    

    private int _roomBlockNr;
    private static int _roomBlockCnt = 1;
}
