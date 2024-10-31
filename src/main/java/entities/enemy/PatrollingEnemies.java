package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;

public class PatrollingEnemies extends Enemies {
    private char moveTo;
    int xMax;
    int yMax;

    public PatrollingEnemies(int init_x, int init_y, Direction d, int xMax, int yMax, TextureRegion texture) {
        super(init_x, init_y, new TextureRegion(new Texture("temp_ptero.png")));
        setFacing(d);
        setXMax(xMax);
        setYMax(yMax);
    }
    public @Override void direction(char input, Board gameBoard) {
        // not going to use char input, but want to prevent anybody from being able to move PatrollingEnemies in a different way
        this.create_path();
        super.direction(this.getMoveTo(), gameBoard);      
    }
    public char getMoveTo() {
        return this.moveTo;
    }
    public int getXMax() {
        return this.xMax;
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
                if(getX() == 0) {
                    setMoveTo('D');
                    setFacing(Direction.Left);
                } else {
                    setMoveTo('A');
                }
                break;
            case Down:
                if(getY() == 0) {
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