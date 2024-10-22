public class Board {
    private RoomBlock startRoomBlock;
    private RoomBlock endRoomBlock;
    private Block [][] array;

    public Board createBoard() {

    }
    public void setStart(RoomBlock thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }
    public void setEnd(RoomBlock thisRoomBlock) {
        this.endRoomBlock = thisRoomBlock;
    }

    public Block getBlock(int x, int y) {
        return array[x][y];
    }
}