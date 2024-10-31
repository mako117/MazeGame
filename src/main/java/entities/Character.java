package entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Block;
import board.Board;
import punishments.Punishments;
import rewards.Reward;


/**
 * The entities.Character class creates a main character for the player to move around the maze
 */
public class Character {
    private int x;
    private int y;
    private int score;
    private int speed;
    private int rewardsCollected;
    private TextureRegion playerTexture;

    /**
     * Creates a entities.Character and initializes its <x> and <y> coordinates, <score>, <speed>, and number of <rewardsCollected>
     */
    public Character(TextureRegion playerTexture) {
        setX(0);
        setY(0);
        setScore(0);
        setSpeed(1);
        setRewardsCollected(0);
        this.playerTexture = playerTexture;
    }
    /**
     * Moves the entities.Character one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     */
    public void direction(char input, Board gameBoard) {
        //board.Block currentBlock = gameBoard.getBlock(x, y); // move to MazeGame
        Block toMoveTo;
        System.out.println(x + " " + y);
        switch(input){
            case 'W':
                toMoveTo = gameBoard.getBlock(x, y+1);
                if(toMoveTo != null && toMoveTo.enter()){
                    y++;
                }
                break;
            case 'S':
                toMoveTo = gameBoard.getBlock(x, y-1);
                if(toMoveTo != null && toMoveTo.enter()){
                    y--;
                }
                break;
            case 'A':
                toMoveTo = gameBoard.getBlock(x-1, y);
                if(toMoveTo != null && toMoveTo.enter()){
                    x--;
                }
                break;
            case 'D':
                toMoveTo = gameBoard.getBlock(x+1, y);
                if(toMoveTo != null && toMoveTo.enter()){
                    x++;
                }
                break;
        }

    }
    /**
     * Updates the entities.Character's score.
     * @param change the change in the entities.Character's score.
     */
    public void scorechange(int change) {
        this.score += change;
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
     * Returns the entities.Character's <score> as an integer.
     * @return <score>
     */
    public int getScore() {
        return this.score;
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

    /**
     * Sets the x-coordinate of the entities.Character's position as an integer.
     * @param xCoord Possible new value for <x>.
     */
    private void setX(int xCoord) {
        if(xCoord >= 0) {
            System.out.println("xCoord = " + xCoord);
            this.x = xCoord;
        }
    }
    /**
     * Sets the y-coordinate of the entities.Character's position as an integer.
     * @param yCoord Possible new value for <y>.
     */
    private void setY(int yCoord) {
        if(yCoord >= 0) {
            System.out.println("yCoord = " + yCoord);
            this.y = yCoord;
        }
    }
    /**
     * Sets the entities.Character's score as an integer.
     * @param newScore Possible new <score>.
     */
    private void setScore(int newScore) {
        if(newScore >= 0) {
            this.score = newScore;
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
    
    public void add_score(int s) {
    	this.score += s;
    }
    
    public void minus_score(int s) {
    	this.score -= s;
    }
    
    public void draw(Batch batch, int tileSize) {
        batch.draw(new TextureRegion(playerTexture,0 ,0, 32, 32), tileSize*x, tileSize*y, tileSize, tileSize);
    }
}
