package entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import directions.Direction;
import board.Block;
import board.Board;

/**
 * The entities.Character class creates a main character for the player to move around the maze
 */
public class Character extends Entity {
    private int speed;
    private int score;

    /**
     * Creates a entities.Character and initializes its <x> and <y> coordinates, <score>, <speed>, and number of <rewardsCollected>
     */
    public Character(TextureRegion playerTexture) {
        super(playerTexture,1,1, Direction.None);
        setSpeed(1);
        setScore(0);
        this.getTexture().setRegion(0 ,0, 32, 32);
    }
    public Character(TextureRegion playerTexture, int startX, int startY) {
        super(playerTexture,startX,startY,Direction.None);
        setSpeed(1);
        this.getTexture().setRegion(0 ,0, 32, 32);
    }
    /**
     * Moves the entities.Character one cell north, east, south, or west if there is nothing in their way.
     * @param input The keyboard input indicating which direction the player wants the character to go.
     * @param gameBoard A reference to the game board.
     * @return Whether the movement was successful.
     */
    public boolean direction(char input, Board gameBoard) {
        return super.direction(input,gameBoard);
    }
    
    /**
     * Returns the entities.Character's <speed> as an integer.
     * @return <speed>
     */
    public int getSpeed() {
        return this.speed;
    }
    /**
     * Gets the entities.Character's <score> as an integer.
     * @return  <score>.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets the entities.Character's speed as an integer.
     * @param newSpeed Possible new <speed>.
     */
    private void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
    /**
     * Sets the entities.Character's <score> as an integer.
     * @param s Possible new <score>.
     */
    private void setScore(int s) {
        this.score = s;
    }

    /**
     * Attempts to increase the entities.Character's score.
     * @param s Amount to increase the <score> by.
     */
    public void add_score(int s) {
        this.setScore(this.getScore() + s);
    }
    /**
     * Attempts to decrease the entities.Character's score.
     * @param s Amount to decrease the <score> by.
     */
    public void minus_score(int s) {
        this.setScore(this.getScore() - s);
    }

    /**
     *
     * Draw the character.
     * @param batch
     * @param tileSize
     * @param offset
     */
    public void draw(Batch batch, int tileSize, float offset) {

        // this will allow for smooth transition between tiles
        // TODO: replace the entityTexture with walking sprite when offset != 0
        switch(this.getFacing()){
            case Up:
                batch.draw(this.getTexture(), tileSize*(this.getX()), tileSize*(this.getY())+offset, tileSize, tileSize);
                break;
            case Down:
                batch.draw(this.getTexture(), tileSize*(this.getX()), tileSize*(this.getY())+offset, tileSize, tileSize);
                break;
            case Left:
                batch.draw(this.getTexture(), tileSize*(this.getX())+offset, tileSize*(this.getY()), tileSize, tileSize);
                break;
            case Right:
                batch.draw(this.getTexture(), tileSize*(this.getX())+offset, tileSize*(this.getY()), tileSize, tileSize);
                break;
            default:
            	batch.draw(this.getTexture(), tileSize*(this.getX()), tileSize*(this.getY()), tileSize, tileSize);
            	break;
        }

    }
}