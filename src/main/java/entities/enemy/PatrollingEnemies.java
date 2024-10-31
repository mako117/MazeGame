package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;

public class PatrollingEnemies extends Enemies {
    private Direction moveTowards;
    private char moveTo;
    int xMax;
    int yMax;

    public PatrollingEnemies(int init_x, int init_y, Direction d, int xMax, int yMax, TextureRegion texture) {
        super(init_x, init_y, new TextureRegion(new Texture("temp_ptero.png")));
        setMoveTowards(d);
        setXMax(xMax);
        setYMax(yMax);
    }
    public void direction(Block currentBlock) {

    }
    public Direction getMoveTowards() {
        return moveTowards;
    }
    public int getXMax() {
        return this.xMax;
    }
    public int getYMax() {
        return this.yMax;
    }

    private void create_path() {
        switch(getMoveTowards()) {
            case North:
                if(getY() == getYMax()) {
                    setMoveTo('S');
                } else {
                    setMoveTo('W');
                }
                break;
            case East:
                if(getX() == 0) {
                    setMoveTo('D');
                } else {
                    setMoveTo('A');
                }
                break;
            case South:
                if(getY() == 0) {
                    setMoveTo('W');
                } else {
                    setMoveTo('S');
                }
                break;
            case West:
                if(getX() == getXMax()) {
                    setMoveTo('A');
                } else {
                    setMoveTo('D');
                }
                break;
        }
    }
    private void setMoveTowards(Direction d) {
        this.moveTowards = d;
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
    private void setMoveTo(char input) {
        this.moveTo = input;
    }
}