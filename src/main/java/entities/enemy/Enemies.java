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
            if(gameBoard.getBlock(getX(), getY() + 1) != null) {
                toMoveTo = gameBoard.getBlock(getX(), getY() + 1);
                if(toMoveTo.enter() == true) {
                    setY(getY() + 1);
                }
            }
            case 'S':
            if(gameBoard.getBlock(getX(), getY() - 1) != null) {
                toMoveTo = gameBoard.getBlock(getX(), getY() - 1);
                if(toMoveTo.enter() == true) {
                    setY(getY() - 1);
                }
            }
            case 'A':
            if(gameBoard.getBlock(getX() - 1, getY()) != null) {
                toMoveTo = gameBoard.getBlock(getX() - 1, getY());
                if(toMoveTo.enter() == true) {
                    setX(getX() - 1);
                }
            }
            case 'D':
            if(gameBoard.getBlock(getX() + 1, getY()) != null) {
                toMoveTo = gameBoard.getBlock(getX() + 1, getY());
                if(toMoveTo.enter() == true) {
                    setX(getX() + 1);
                }
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

    public void draw(Batch batch) {
        batch.draw(enemy_texture,x,y,32,32);
            }
}