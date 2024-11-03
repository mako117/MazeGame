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
        setFacing(Direction.Down);
        this.enemy_texture = texture;
        this.enemyNr = enemyCnt++;
    }
    public boolean direction(char input, Board gameBoard) {
        Block toMoveTo;
        switch(input) {
            case 'W':
                this.setFacing(Direction.Up);
                if(gameBoard.getBlock(getX(), getY() + 1) != null) {
                    toMoveTo = gameBoard.getBlock(getX(), getY() + 1);
                    if(toMoveTo.enter() == true) {
                        this.setY(this.getY() + 1);
                        return true;
                    }
                }
                break;
            case 'S':
                this.setFacing(Direction.Down);
                if(gameBoard.getBlock(getX(), getY() - 1) != null) {
                    toMoveTo = gameBoard.getBlock(getX(), getY() - 1);
                    if(toMoveTo.enter() == true) {
                        this.setY(this.getY() - 1);
                        return true;
                    }
                }
                break;
            case 'A':
                this.setFacing(Direction.Left);
                if(gameBoard.getBlock(getX() - 1, getY()) != null) {
                    toMoveTo = gameBoard.getBlock(getX() - 1, getY());
                    if(toMoveTo.enter() == true) {
                        this.setX(this.getX() - 1);
                        return true;
                    }
                }
                break;
            case 'D':
                this.setFacing(Direction.Right);
                if(gameBoard.getBlock(getX() + 1, getY()) != null) {
                    toMoveTo = gameBoard.getBlock(getX() + 1, getY());
                    if(toMoveTo.enter() == true) {
                        this.setX(this.getX() + 1);
                        return true;
                    }
                }
                break;
        }
        return false;
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

    public void draw(Batch batch, int tileSize, float offset) {
        if (offset != 0) {
            switch (facing) {
                case Up:
                    batch.draw(enemy_texture, tileSize * x, tileSize * y - offset, tileSize, tileSize);
                    break;
                case Down:
                    batch.draw(enemy_texture, tileSize * x, tileSize * y + offset, tileSize, tileSize);
                    break;
                case Left:
                    batch.draw(enemy_texture, tileSize * x + offset, tileSize * y, tileSize, tileSize);
                    break;
                case Right:
                    batch.draw(enemy_texture, tileSize * x - offset, tileSize * y, tileSize, tileSize);
                    break;
            }
        } else {
            batch.draw(enemy_texture, tileSize * x, tileSize * y, tileSize, tileSize);
        }
    }
}