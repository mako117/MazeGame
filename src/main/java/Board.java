public class Board {
    private RoomBlock startRoomBlock;
    private RoomBlock endRoomBlock;
    private Block [][] array;

    public void createBoard() {
    	array = new Block[3][3];
    	array[0][0] = new RoomBlock(0,0);
    	array[0][1] = new RoomBlock(0,1);
    	array[0][2] = new RoomBlock(0,2);
    	array[1][0] = new RoomBlock(1,0);
    	array[1][1] = new BarriersBlock(1,1);
    	array[1][2] = new RoomBlock(1,2);
    	array[2][0] = new RoomBlock(2,0);
    	array[2][1] = new RoomBlock(2,1);
    	array[2][1] = new RoomBlock(2,2);
    	
    	Door d1 = new Door(array[0][0],array[0][1]);
    	Door d2 = new Door(array[0][1],array[0][2]);
    	Door d3 = new Door(array[0][2],array[1][2]);
    	Door d4 = new Door(array[1][2],array[2][2]);
    	Door d5 = new Door(array[2][2],array[2][1]);
    	Door d6 = new Door(array[2][1],array[2][0]);
    	Door d7 = new Door(array[2][0],array[1][0]);
    	Door d8 = new Door(array[1][0],array[0][0]);
    	
    	array[0][0].setSide(Direction.North, d8);
    	array[0][0].setSide(Direction.South, new Wall());
    	array[0][0].setSide(Direction.West, new Wall());
    	array[0][0].setSide(Direction.East, d1);
    	
    	array[0][1].setSide(Direction.North, new Wall());
    	array[0][1].setSide(Direction.South, new Wall());
    	array[0][1].setSide(Direction.West, d1);
    	array[0][1].setSide(Direction.East, d2);
    	
    	array[0][2].setSide(Direction.North, d3);
    	array[0][2].setSide(Direction.South, new Wall());
    	array[0][2].setSide(Direction.West, d2);
    	array[0][2].setSide(Direction.East, new Wall());
    	
    	array[1][0].setSide(Direction.North, d7);
    	array[1][0].setSide(Direction.South, d8);
    	array[1][0].setSide(Direction.West, new Wall());
    	array[1][0].setSide(Direction.East, new Wall());
    	
    	array[1][1].setSide(Direction.North, new Wall());
    	array[1][1].setSide(Direction.South, new Wall());
    	array[1][1].setSide(Direction.West, new Wall());
    	array[1][1].setSide(Direction.East, new Wall());
    	
    	array[1][2].setSide(Direction.North, d4);
    	array[1][2].setSide(Direction.South, d3);
    	array[1][2].setSide(Direction.West, new Wall());
    	array[1][2].setSide(Direction.East, new Wall());
    	
    	array[2][0].setSide(Direction.North, new Wall());
    	array[2][0].setSide(Direction.South, d7);
    	array[2][0].setSide(Direction.West, new Wall());
    	array[2][0].setSide(Direction.East, d6);
    	
    	array[2][1].setSide(Direction.North, new Wall());
    	array[2][1].setSide(Direction.South, new Wall());
    	array[2][1].setSide(Direction.West, d6);
    	array[2][1].setSide(Direction.East, d5);
    	
    	array[2][2].setSide(Direction.North, new Wall());
    	array[2][2].setSide(Direction.South, d4);
    	array[2][2].setSide(Direction.West, d5);
    	array[2][2].setSide(Direction.East, new Wall());
    	
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