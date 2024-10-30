package board;
import directions.Direction;

/**
 * A block is a cell on the board.
 */
public abstract class Block {
    protected int x; 
    protected int y;

    public Block(){
        x = -1;
        y = -1;
    }
    public Block(int x1, int y1){
        x = x1;
        y = y1;
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
    abstract boolean enter();
}
