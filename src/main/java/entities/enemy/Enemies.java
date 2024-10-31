package entities.enemy;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextureRegion enemy_texture;
    private Direction facing;

    public Enemies(){
        enemyNr = enemyCnt++;
    }
    public Enemies(int initial_x, int initial_y, TextureRegion texture) {
        setX(initial_x);
        setY(initial_y);
        this.enemy_texture = texture;
        this.enemyNr = enemyCnt++;
    }
    public void direction(char input, Board gameBoard) {
        Block toMoveTo;
        switch(input) {
            case 'W':
                this.setFacing(Direction.North);
                if(gameBoard.getBlock(getX(), getY() + 1) != null) {
                    toMoveTo = gameBoard.getBlock(getX(), getY() + 1);
                    if(toMoveTo.enter() == true) {
                        this.setY(this.getY() + 1);
                    }
                }
                break;
            case 'S':
                this.setFacing(Direction.South);
                if(gameBoard.getBlock(getX(), getY() - 1) != null) {
                    toMoveTo = gameBoard.getBlock(getX(), getY() - 1);
                    if(toMoveTo.enter() == true) {
                        this.setY(this.getY() - 1);
                    }
                }
                break;
            case 'A':
                this.setFacing(Direction.West);
                if(gameBoard.getBlock(getX() - 1, getY()) != null) {
                    toMoveTo = gameBoard.getBlock(getX() - 1, getY());
                    if(toMoveTo.enter() == true) {
                        this.setX(this.getX() - 1);
                    }
                }
                break;
            case 'D':
                this.setFacing(Direction.East);
                if(gameBoard.getBlock(getX() + 1, getY()) != null) {
                    toMoveTo = gameBoard.getBlock(getX() + 1, getY());
                    if(toMoveTo.enter() == true) {
                        this.setX(this.getX() + 1);
                    }
                }
                break;
        }
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public Direction getFacing() {
        return this.facing;
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
    protected void setFacing(Direction d) {
        this.facing = d;
    }

    public void draw(Batch batch) {
        batch.draw(enemy_texture,x,y,32,32);
            }
}