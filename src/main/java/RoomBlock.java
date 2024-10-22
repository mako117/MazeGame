/**
 * The RoomBlock class creates room blocks for the maze
 * Rooms have sides that can be Walls or Doors
 */
class RoomBlock extends Block {
    /**
     * Creates a Room and assigns it a unique room number
     */
    RoomBlock() {
        _roomBlockNr = _roomBlockCnt++;
        System.out.println("Creating Room #" + _roomBlockNr);

    }

    /**
     * Creates a Room with a unique room number and a set position
     * 
     * @param x_pos The x position of the block
     * @param y_pos The y position of the block
     */
    RoomBlock( int x_pos, int y_pos) {
        this.x = x_pos;
        this.y = y_pos;
        _roomBlockNr = _roomBlockCnt++;
        System.out.println("Creating Room #" + _roomBlockNr + " with x position " + x + " and y position " + y);
    }

    /**
     * Return the Room block number as a string
     * 
     * @return a String of the Room Number
     */
    public String toString() {
        return "Room Block #" + new Integer(_roomBlockNr).toString();
    }

    private int _roomBlockNr;
    private static int _roomBlockCnt = 1;
    private int x;
    private int y;

}
