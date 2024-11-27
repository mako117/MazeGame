package board;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The BarriersBlock class creates barrier blocks for the maze
 */
public class BarrierBlock extends Block {
    private int _BarriersBlockNr;
    private static int _BarriersBlockCnt = 1;

    /**
     * Create a default barrier block.
     */
    public BarrierBlock(){
        super();
        _BarriersBlockNr = _BarriersBlockCnt++;
    }

    /**
     * Creates new barrier block at the given coordinate.
     * 
     * @param x_pos The x position of the barrier
     * @param y_pos The y position of the barrier
     * @param textureRegion The texture of the block
     */
    public BarrierBlock(int x_pos, int y_pos, TextureRegion  textureRegion) {
        super(x_pos, y_pos, textureRegion);
        _BarriersBlockNr = _BarriersBlockCnt++;

//         For verification purposes
//        System.out.println("Creating Room #" + _BarriersBlockNr + " with x position " + x_pos + " and y position " + y_pos);
    }

    /**
     * Entering a barrier block is not possible so we return false.
     * @return false
     */
    public boolean enter(){
        return false;
    }


}
