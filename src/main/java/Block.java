/**
 * A block is a cell on the board.
 */
public class Block extends MapSite{
    
    Mapsite n;
    Mapsite s;
    Mapsite e;
    Mapsite w;

    public Block(){

    }

    /**
     * Sets the side of this room to MapSite <code>site</code> in the Direction <code>d</code>.
     * @param d the direction.
     * @param site the map site to connect to this room's side.
     */
    public void setSide(Direction d, MapSite site){
        switch(d){
            case North:
                northSide = site;
            case South:
                southSide = site;
            case East:
                eastSide = site;
            case West:
                westSide = site;
        }
        
    }
    
}
