package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;

public class PatrollingEnemies extends Enemies {
    private char moveTo;
    int xMax;
    int yMax;
    int xMin;
    int yMin;

    public PatrollingEnemies(int init_x, int init_y, Direction d, int xMin, int xMax, int yMin, int yMax, TextureRegion texture) {
        super(init_x, init_y, new TextureRegion(new Texture("ptero.png"),0,0, 31,16));
        setFacing(d);
        setXMin(xMin);
        setXMax(xMax);
        setYMin(yMin);
        setYMax(yMax);
    }
    public @Override boolean direction(char input, Board gameBoard) {
        // not going to use char input, but want to prevent anybody from being able to move PatrollingEnemies in a different way
        this.create_path();
        switch (moveTo){
            case 'W':
                this.setFacing(Direction.Up);
                if(gameBoard.getBlock(getX(), getY() + 1) != null) {
                    this.setY(this.getY() + 1);
                }
                break;
            case 'S':
                this.setFacing(Direction.Down);
                if(gameBoard.getBlock(getX(), getY() - 1) != null) {
                    this.setY(this.getY() - 1);
                }
                break;
            case 'A':
                this.setFacing(Direction.Left);
                if(gameBoard.getBlock(getX() - 1, getY()) != null) {
                    this.setX(this.getX() - 1);
                }
                break;
            case 'D':
                this.setFacing(Direction.Right);
                if(gameBoard.getBlock(getX() + 1, getY()) != null) {
                    this.setX(this.getX() + 1);
                }
                break;
        }
        return true;
    }
    public char getMoveTo() {
        return this.moveTo;
    }
    public int getXMin() {
        return this.xMin;
    }
    public int getXMax() {
        return this.xMax;
    }
    public int getYMin() {
        return this.yMin;
    }
    public int getYMax() {
        return this.yMax;
    }

    private void create_path() {
        switch(this.getFacing()) {
            case Up:
                if(getY() == getYMax()) {
                    setMoveTo('S');
                    setFacing(Direction.Down);
                } else {
                    setMoveTo('W');
                }
                break;
            case Right:
                if(getX() == getXMin()) {
                    setMoveTo('D');
                    setFacing(Direction.Left);
                } else {
                    setMoveTo('A');
                }
                break;
            case Down:
                if(getY() == getYMin()) {
                    setMoveTo('W');
                    setFacing(Direction.Up);
                } else {
                    setMoveTo('S');
                }
                break;
            case Left:
                if(getX() == getXMax()) {
                    setMoveTo('A');
                    setFacing(Direction.Right);
                } else {
                    setMoveTo('D');
                }
                break;
        }
    }
    private void setXMin(int minX) {
        if(minX > 0) {
            this.xMin = minX;
        } 
    }
    private void setXMax(int maxX) {
        if(maxX > 0) {
            this.xMax = maxX;
        }
    }
    private void setYMin(int minY) {
        if(minY > 0) {
            this.yMin = minY;
        } 
    }
    private void setYMax(int maxY) {
        if(maxY > 0) {
            this.yMax = maxY;
        }
    }
    private void setMoveTo(char input) {
        this.moveTo = input;
    }
}