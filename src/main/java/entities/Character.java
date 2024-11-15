package entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import directions.Direction;
import board.Block;
import board.Board;

/**
 * The entities.Character class creates a main character for the player to move around the maze
 */
public class Character {
    private int x;
    private int y;
    private int speed;
    private int score;
    private int rewardsCollected;
    private TextureRegion playerTexture;
    private Direction facing;

    /**
     * Creates a entities.Character and initializes its <x> and <y> coordinates, <score>, <speed>, and number of <rewardsCollected>
     */
    public Character(TextureRegion playerTexture) {
        setX(1);
        setY(1);
        setFacing(Direction.Down);
        setSpeed(1);
        setScore(0);
        setRewardsCollected(0);
        this.playerTexture = playerTexture;
        this.playerTexture.setRegion(0 ,0, 32, 32);
    }
    public Character(TextureRegion playerTexture, int startX, int startY) {
        setX(startX);
        setY(startY);
        setFacing(Direction.Down);
        setSpeed(1);
        setRewardsCollected(0);
        this.playerTexture = playerTexture;
        this.playerTexture.setRegion(0 ,0, 32, 32);
    }
    /**
     * Moves the entities.Character one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     * @return Whether the movement was successful.
     */
    public boolean direction(char input, Board gameBoard) {
        //board.Block currentBlock = gameBoard.getBlock(x, y); // move to MazeGame
        Block toMoveTo;
        System.out.println(x + " " + y);
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
     * Returns the x-coordinate of the entities.Character's position as an integer.
     * @return  <x>
     */
    public int getX() {
        return this.x;
    }
    /**
     * Returns the y-coordinate of the entities.Character's position as an integer.
     * @return <y>
     */
    public int getY() {
        return this.y;
    }
    /**
     * Returns the entities.Character's <speed> as an integer.
     * @return <speed>
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * Returns the entities.Character's number of <rewardsCollected> as an integer.
     * @return <rewardsCollected>
     */
    public int getRewardsCollected() {
        return this.rewardsCollected;
    }
    public Direction getFacing() {
        return this.facing;
    }
    public void addRegReward() {
        this.setRewardsCollected(this.rewardsCollected + 1);
    }

    /**
     * Sets the x-coordinate of the entities.Character's position as an integer.
     * @param xCoord Possible new value for <x>.
     */
    private void setX(int xCoord) {
        if(xCoord >= 0) {
            this.x = xCoord;
        }
    }
    /**
     * Sets the y-coordinate of the entities.Character's position as an integer.
     * @param yCoord Possible new value for <y>.
     */
    private void setY(int yCoord) {
        if(yCoord >= 0) {
            this.y = yCoord;
        }
    }
    /**
     * Sets the entities.Character's speed as an integer.
     * @param newSpeed Possible new <speed>.
     */
    private void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
    /**
     * Sets the entities.Character's number of <rewardsCollected> as an integer.
     * @param newTally Possible new number of <rewardsCollected>.
     */
    private void setRewardsCollected(int newTally) {
        if(newTally >= 0) {
            this.rewardsCollected = newTally;
        }
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(int s) {this.score = s;}
    public void add_score(int s) {
        this.score += s;
    }
    public void minus_score(int s) {
        this.score -= s;
    }

    private void setFacing(Direction d) {
        this.facing = d;
    }

    /**
     *
     * Draw the player.
     * @param batch
     * @param tileSize
     * @param offset
     */
    public void draw(Batch batch, int tileSize, float offset) {

        // this will allow for smooth transition between tiles
        // TODO: replace the playerTexture with walking sprite when offset != 0
        if(offset != 0){
            switch(facing){
                case Up:
                    batch.draw(playerTexture, tileSize*x, tileSize*y+offset, tileSize, tileSize);
                    break;
                case Down:
                    batch.draw(playerTexture, tileSize*x, tileSize*y+offset, tileSize, tileSize);
                    break;
                case Left:
                    batch.draw(playerTexture, tileSize*x+offset, tileSize*y, tileSize, tileSize);
                    break;
                case Right:
                    batch.draw(playerTexture, tileSize*x+offset, tileSize*y, tileSize, tileSize);
                    break;
            }
        }
        else {
            // player is standing still
            batch.draw(playerTexture, tileSize*x, tileSize*y, tileSize, tileSize);
        }

    }
}
