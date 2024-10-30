package entities.enemy;
import directions.Direction;
import board.*;

/**
 * Enemies Class
 */

 public class Enemies {
    private int enemyNr;
    private static int enemyCnt = 1;
    private int x;
    private int y;

    public Enemies(){
        enemyNr = enemyCnt++;
    }
    public Enemies(int initial_x, int initial_y) {
        setX(initial_x);
        setY(initial_y);
        this.enemyNr = enemyCnt++;
    }
    public void direction(char input, Block currentBlock) {
        Mapsite toMoveTo;
        switch(input) {
            case 'W':
            toMoveTo = currentBlock.getSide(Direction.North);
            if(toMoveTo.enter() == true) {
                setY(getY() + 1);
                // this.y += this.speed;
            }
            case 'S':
            toMoveTo = currentBlock.getSide(Direction.South);
            if(toMoveTo.enter() == true) {
                setY(getY() - 1);
                // this.y -= this.speed;
            }
            case 'A':
            toMoveTo = currentBlock.getSide(Direction.West);
            if(toMoveTo.enter() == true) {
                setX(getX() - 1);
                // this.x -= this.speed;
            }
            case 'D':
            toMoveTo = currentBlock.getSide(Direction.East);
            if(toMoveTo.enter() == true) {
                setX(getX() + 1);
                // this.x += this.speed;
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