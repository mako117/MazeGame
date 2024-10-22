/**
 * The Wall class is used to create a wall and its interactions
 */
class Wall extends Mapsite {
    /**
     * Creates a wall and assigns the wall a unique number
     */
    Wall() {
        _wallNr = _wallCnt++;
        System.out.println("creating Wall #" + new Integer(_wallNr).toString());
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
        return "Wall #" + new Integer(_wallNr).toString();
    }

    private int _wallNr;
    private static int _wallCnt = 1;

}