package board;
import directions.Direction;

/**
 * A block is a cell on the board.
 */
public abstract class Block extends Mapsite {
    private Mapsite n;
    private Mapsite s;
    private Mapsite e;
    private Mapsite w;

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

    /**
     * Sets the side of this room to MapSite <code>site</code> in the directions.Direction <code>d</code>.
     * @param d the direction.
     * @param site the map site to connect to this room's side.
     */
    public void setSide(Direction d, Mapsite site){
        switch(d){
            case North:
                n = site;
            case South:
                s = site;
            case East:
                e = site;
            case West:
                w = site;
        }
        
    }

    /**
     * Returns the MapSite object on the directions.Direction <code>d</code> side of this room.
     * @param d  the direction
     * @return the MapSite object to the corresponding side.
     */
    public Mapsite getSide(Direction d){
        Mapsite result = null;
        switch (d){
            case North:
                result = n;
            case South:
                result = s;
            case East:
                result = e;
            case West:
                result = w;
        }
        return result;
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
}
