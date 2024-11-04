package entities.enemy;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;

/**
 * An enemy in the maze game. <br>
 * This enemy can move throughout the maze.<br>
 * Each enemy has a unique identifier and texture associated with it.
 */
 public class Enemies {
    private int enemyNr;
    private static int enemyCnt = 1;
    private int x;
    private int y;
    private TextureRegion enemy_texture;
    private Direction facing;


    /**
     * Create a new enemy at the given initial location. <br>
     * @param initial_x The x position.
     * @param initial_y The y position.
     * @param texture The texture.
     */
    public Enemies(int initial_x, int initial_y, TextureRegion texture) {
        setX(initial_x);
        setY(initial_y);
        setFacing(Direction.Down);
        this.enemy_texture = texture;
        this.enemyNr = enemyCnt++;
    }

    /**
     * Moves the enemy to the next block on the game board. <br>
     * Returns whether the move is successful.
     * @param input The direction to move the enemy.
     * @param gameBoard The game board.
     * @return Whether the move was successful.
     */
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

    /**
     * Return the x position of the enemy.
     * @return The x position.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y position of the enemy.
     * @return The y position.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Get the direction that the enemy is facing.
     * @return The direction.
     */
    public Direction getFacing() {
        return this.facing;
    }

    /**
     * Set the x position of the enemy to the given x coordinate.
     * @param xCoord The new x coordinate.
     */
    protected void setX(int xCoord) {
        if(xCoord >= 0) {
            this.x = xCoord;
        }
    }

    /**
     * Set the y position of the enemy to the given y coordinate.
     * @param yCoord The new y coordinate.
     */
    protected void setY(int yCoord) {
        if(yCoord >= 0) {
            this.y = yCoord;
        }
    }

    /**
     * Set the direction the enemy is facing to the given direction.
     * @param d The new direction.
     */
    protected void setFacing(Direction d) {
        this.facing = d;
    }

    /**
     * Draw the enemy to the screen.
     * @param batch The spritebatch.
     * @param tileSize The tile size.
     * @param offset The image offset.
     */
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