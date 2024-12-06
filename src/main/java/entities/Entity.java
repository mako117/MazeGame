package entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Block;
import board.Board;
import directions.Direction;

/**
 * The Entity class represents moving objects that interact with other objects in various ways.
 */
public class Entity {
    private int x;
    private int y;
    private Direction facing = Direction.None;
    private TextureRegion entityTexture;


    /**
     * Creates an Entity and initializes its starting <code>x</code> and <code>y</code> coordinates, and the starting direction it is facing.
     * @param entityTexture Input for <code>entityTexture</code>.
     * @param startX    Input for <code>x</code>.
     * @param startY    Input for <code>y</code>.
     * @param startFacing   Input for <code>facing</code>.
     */
    public Entity(TextureRegion entityTexture, int startX, int startY, Direction startFacing) {
        setX(startX);
        setY(startY);
        setFacing(startFacing);
        setTextureRegion(entityTexture);
    }

    /**
     * Moves the Entity one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     * @return True or false for if the movement was successful.
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
     * Returns the <code>x</code>-coordinate of the Entity's position as an integer.
     * @return  <code>x</code>.
     */
    public int getX() {
        return this.x;
    }
    /**
     * Returns the <code>y</code>-coordinate of the Entity's position as an integer.
     * @return <code>y</code>.
     */
    public int getY() {
        return this.y;
    }
    /**
     * Returns the direction in which the Entity is facing.
     * @return  <code>facing</code>.
     */
    public Direction getFacing() {
		return this.facing;
    }
    /**
     * Returns the texture of the entity.
     * @return  <code>entityTexture</code>.
     */
    public TextureRegion getTexture() {
        return this.entityTexture;
    }

    /**
     * Sets the <code>x</code>-coordinate of the Entity's position as an integer.
     * @param xCoord Possible new value for <code>x</code>.
     */
    protected void setX(int xCoord) {
        if(xCoord >= 0) {
            this.x = xCoord;
        }
    }
    /**
     * Sets the <code>y</code>-coordinate of the Entity's position as an integer.
     * @param yCoord Possible new value for <code>y</code>.
     */
    protected void setY(int yCoord) {
        if(yCoord >= 0) {
            this.y = yCoord;
        }
    }
    /**
     * Sets the direction in which the Entity is facing as a Direction.
     * @param d Possible new value for <code>facing</code>.
     */
    protected void setFacing(Direction d) {
        this.facing = d;
    }
    /**
     * Sets the texture that represents the Entity in the UI.
     * @param textureInput  Possible new value for <code>entityTexture</code>.
     */
    protected void setTextureRegion(TextureRegion textureInput) {
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

    /**
     * Get the block which the entity wants to try to enter.
     * @param increment The amount the entity wants to increase its position on one of the axis by; if they want to decrease their position, this number is negative.
     * @param movingOnX Indicates whether the entity wants to move along the X axis; if not, then they want to move along the Y axis.
     * @param gameBoard The Board object on which the entity is moving.
     * @return  The Block which the entity wants to try to enter.
     */
    private Block getTargetBlock(int increment, boolean movingOnX, Board gameBoard) {
        if (movingOnX) {
            setFacing(increment > 0 ? Direction.Right : Direction.Left);
            return gameBoard.getBlock(x + increment, y);
        } else {
            setFacing(increment > 0 ? Direction.Up : Direction.Down);
            return gameBoard.getBlock(x,y + increment);
        }
    }

    /**
     * Updates the entity's position variables.
     * @param increment The amount the entity wants to increase its position on one of the axis by; if they want to decrease their position, this number is negative.
     * @param movingOnX Indicates whether the entity wants to move along the X axis; if not, then they want to move along the Y axis.
     */
    private void updatePosition(int increment, boolean movingOnX){
        if(movingOnX){
            setX(x + increment);
        } else{
            setY(y + increment);
        }
    }

}
