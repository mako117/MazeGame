/**
 * The BarriersBlock class creates barrier blocks for the maze
 * Rooms have sides that can be Walls or Doors
 */
class BarriersBlock extends Block {
    /**
     * Creates a Room and assigns it a unique room number
     */
    BarriersBlock() {
        _BarriersBlockNr = _BarriersBlockCnt++;
        System.out.println("Creating Room #" + _BarriersBlockNr);

    }

    /**
     * Creates a Room with a unique room number and a set position
     * 
     * @param x_pos The x position of the barrier
     * @param y_pos The y position of the barrier
     */
    BarriersBlock(int x_pos, int y_pos) {
        super(x_pos, y_pos);
        _BarriersBlockNr = _BarriersBlockCnt++;
        System.out.println("Creating Room #" + _BarriersBlockNr + " with x position " + x + " and y position " + y);
    }

    /**
     * Return the Barrier number as a string
     * 
     * @return a String of the Barrier Number
     */
    public String toString() {
        return "Barrier #" + _BarriersBlockNr;
    }

    /**
     * Entering a barrier block is not possible.
     * @return false
     */
    public boolean enter(){
        return false;
    }

    private int _BarriersBlockNr;
    private static int _BarriersBlockCnt = 1;
}
