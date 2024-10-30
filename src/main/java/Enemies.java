/**
 * Enemies Class
 */

public class Enemies {
    private int enemyNr;
    private static int enemyCnt = 0;
    private int x;
    private int y;

    /**
     * Default Enemies Constructor
     */
    public Enemies(){
        this.enemyNr = enemyCnt++;
    }

    /**
     * Enemies Constructor with starting position
     * 
     * @param initial_x Starting x position
     * @param initial_y Starting y position
     */
    public Enemies(int initial_x, int initial_y) {
        setX(initial_x);
        setY(initial_y);
        this.enemyNr = enemyCnt++;
    }
    /**
     * Moves the Enemy
     */
    public void direction(Character aCharacter, Block currentBlock) {
        int xDistance = this.x - aCharacter.getX();
        int yDistance = this.y - aCharacter.getY();
        double moveXDistance;
        double moveYDistance;
        if(xDistance < 0) {
            moveXDistance = Math.sqrt( ((xDistance+1)^2 + yDistance^2) );
        } else {
            moveXDistance = Math.sqrt( ((xDistance-1)^2 + yDistance^2) );
        }
        if(yDistance < 0) {
            moveYDistance = Math.sqrt( ((yDistance+1)^2 + xDistance^2) );
        } else {
            moveYDistance = Math.sqrt( ((yDistance-1)^2 + xDistance^2) );
        }

        if(moveYDistance < moveXDistance) {
            if((yDistance < 0) && (currentBlock.getSide(Direction.South)).enter() == true) {
                setY(getY() + 1);
                // this.y++;
            } else if ((currentBlock.getSide(Direction.North)).enter() == true) {
                setY(getY() - 1);
                // this.y--;
            } else if((xDistance < 0) && (currentBlock.getSide(Direction.West)).enter() == true) {
                setX(getX() + 1);
                // this.x++;
            } else if ((currentBlock.getSide(Direction.East)).enter() == true) {
                setX(getX() - 1);
                // this.x--;
            }
        } else {
            if((xDistance < 0) && (currentBlock.getSide(Direction.West)).enter() == true) {
                setX(getX() + 1);
                // this.x++;
            } else if ((currentBlock.getSide(Direction.East)).enter() == true) {
                setX(getX() - 1);
                // this.x--;
            } else if((yDistance < 0) && (currentBlock.getSide(Direction.South)).enter() == true) {
                setY(getY() + 1);
                // this.y++;
            } else if ((currentBlock.getSide(Direction.North)).enter() == true) {
                setY(getY() - 1);
                // this.y--;
            }
        }

    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    protected void setX(int xCoord) {
        if(xCoord >= 0) {
            this.x = xCoord;
        }
    }
    protected void setY(int yCoord) {
        if(yCoord >= 0) {
            this.y = yCoord;
        }
    }
}