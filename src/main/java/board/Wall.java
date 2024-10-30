package board;

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
    
    Wall(int x, int y){
    	super(x,y);
    }

    /**
     * Enter a wall
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