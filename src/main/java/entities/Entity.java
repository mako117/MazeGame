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
        setTexture(playerTexture);
    }

    /**
     * Moves the Entity one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     * @return Whether the movement was successful.
     */
    public boolean direction(char input, Board gameBoard) {
        // System.out.println(x + " " + y);
        switch(input){
            case 'W':
                if(checkAndMove(1, false, gameBoard)) {
                    return true;
                }
                break;
            case 'S':
                if(checkAndMove(-1, false, gameBoard)) {
                    return true;
                }
                break;
            case 'A':
                if(checkAndMove(-1, true, gameBoard)) {
                    return true;
                }
                break;
            case 'D':
                if(checkAndMove(1, true, gameBoard)) {
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
    /**
     * Sets the texture that represents the Entity in the UI.
     * @param textureInput  Possible new value for <entityTexture>.
     */
    protected void setTexture(TextureRegion textureInput) {
        this.entityTexture = textureInput;
    }

    /**
     * Checks if the block the entity wants to move to is enterable, and updates the entity's position and direction accordingly.
     * @param increment The amount the entity wants to increase its position on one of the axis by; if they want to decrease their position, this number is negative.
     * @param movingOnX Indicates whether the entity wants to move along the X axis; if not, then they want to move along the Y axis.
     * @param gameBoard The Board object on which the entity is moving.
     * @return  true if they successfully moved, else return false.
     */
    protected boolean checkAndMove(int increment, boolean movingOnX, Board gameBoard){
        Block toMoveTo = getTargetBlock(increment, movingOnX,gameBoard);
        if (toMoveTo != null && toMoveTo.enter()){
            updatePosition(increment,movingOnX);
            return true;
        }
        return false;
    }

    private Block getTargetBlock(int increment, boolean movingOnX, Board gameBoard) {
        if (movingOnX) {
            setFacing(increment > 0 ? Direction.Right : Direction.Left);
            return gameBoard.getBlock(x + increment, y);
        } else {
            setFacing(increment > 0 ? Direction.Up : Direction.Down);
            return gameBoard.getBlock(x,y + increment);
        }
    }

    private void updatePosition(int increment, boolean movingOnX){
        if(movingOnX){
            setX(x + increment);
        } else{
            setY(y + increment);
        }
    }

}
