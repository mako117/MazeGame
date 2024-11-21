package entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Block;
import board.Board;
import directions.Direction;

public class Entity {
    private int x;
    private int y;
    private Direction facing = Direction.None;
    private TextureRegion entityTexture;

    /**
     * Creates an Entity and initializes its <x> and <y> coordinates, <score>, <speed>, and number of <rewardsCollected>
     */
    public Entity(TextureRegion playerTexture, int startX, int startY, Direction startFacing) {
        setX(startX);
        setY(startY);
        setFacing(startFacing);
        this.entityTexture = playerTexture;
    }

    /**
     * Moves the Entity one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     * @return Whether the movement was successful.
     */
    public boolean direction(char input, Board gameBoard) {
        Block toMoveTo;
        // System.out.println(x + " " + y);
        switch(input){
            case 'W':
                this.setFacing(Direction.Up);
                toMoveTo = gameBoard.getBlock(x, y+1);
                if(toMoveTo != null && toMoveTo.enter()){
                    //y++;
                    this.setY(getY() + 1);
                    return true;
                }
                break;
            case 'S':
                this.setFacing(Direction.Down);
                toMoveTo = gameBoard.getBlock(x, y-1);
                if(toMoveTo != null && toMoveTo.enter()){
                    // y--;
                    this.setY(getY() - 1);
                    return true;
                }
                break;
            case 'A':
                this.setFacing(Direction.Left);
                toMoveTo = gameBoard.getBlock(x-1, y);
                if(toMoveTo != null && toMoveTo.enter()){
                    // x--;
                    this.setX(getX() - 1);
                    return true;
                }
                break;
            case 'D':
                this.setFacing(Direction.Right);
                toMoveTo = gameBoard.getBlock(x+1, y);
                if(toMoveTo != null && toMoveTo.enter()){
                    // x++;
                    this.setX(getX() + 1);
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Returns the x-coordinate of the Entity's position as an integer.
     * @return  <x>
     */
    public int getX() {
        return this.x;
    }
    /**
     * Returns the y-coordinate of the Entity's position as an integer.
     * @return <y>
     */
    public int getY() {
        return this.y;
    }
    /**
     * Returns the direction in which the Entity is facing.
     * @return  <facing>.
     */
    public Direction getFacing() {
		return this.facing;
    }
    public TextureRegion getTexture() {
        return this.entityTexture;
    }

    /**
     * Sets the x-coordinate of the Entity's position as an integer.
     * @param xCoord Possible new value for <x>.
     */
    protected void setX(int xCoord) {
        if(xCoord >= 0) {
            this.x = xCoord;
        }
    }
    /**
     * Sets the y-coordinate of the Entity's position as an integer.
     * @param yCoord Possible new value for <y>.
     */
    protected void setY(int yCoord) {
        if(yCoord >= 0) {
            this.y = yCoord;
        }
    }
    /**
     * Sets the direction in which the Entity is facing as a Direction.
     * @param d Possible new value for <facing>.
     */
    protected void setFacing(Direction d) {
        this.facing = d;
    }
}
