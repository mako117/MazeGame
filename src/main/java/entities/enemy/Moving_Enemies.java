package entities.enemy;
import directions.Direction;
import board.*;

class Moving_Enemies extends Enemies {
    private Direction d; // can't think of a better name for it
    private int xMax;
    private int yMax;
    /**
     * Moving entities.Enemies Constructor
     */
    public Moving_Enemies(int init_x, int init_y, Direction d, int maxX, int maxY) {
        super(init_x,init_y);
        setDirection(d);   
        setXMax(maxX);
        setYMax(maxY);     
    }
    /**
     * Controls the movement of the enemy
     */
    public void direction(Character aCharacter, Block currentBlock) {
        Direction compass = getDirection();
        switch(compass) {
            case North:
            if(getY() == getYMax()) {
                setY(getY() - 1);
            }
            case East:
            if(getX() == 0) {
                setX(1);
            }
            case South:
            if(getY() == 0) {
                setY(1);
            }
            case West:
            if(getX() == getXMax()) {
                setX(getX() + 1);
            }
        }
    }
    public Direction getDirection() {
        return this.d;
    }
    public int getXMax() {
        return this.xMax;
    }
    public int getYMax() {
        return this.yMax;
    }

    private void setDirection(Direction d) {
        this.d = d;
    }
    private void setXMax(int maxX) {
        if(maxX > 0) {
            this.xMax = maxX;
        }
    }
    private void setYMax(int maxY) {
        if(maxY > 0) {
            this.yMax = maxY;
        }
    }
}