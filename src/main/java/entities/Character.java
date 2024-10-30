package entities;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;


/**
 * The entities.Character class creates a main character for the player to move around the maze
 */
public class Character {
    private int x;
    private int y;
    private int score;
    private int speed;
    private int rewardsCollected;
    private TextureRegion[] playerTexture;

    /**
     * Creates a entities.Character and initializes its <x> and <y> coordinates, <score>, <speed>, and number of <rewardsCollected>
     */
    public Character(TextureRegion[] playerTexture) {
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
     * @param currentBlock The block which the character is occupying when this function is called.
     */
    public void direction(char input, Block currentBlock) {
        //board.Block currentBlock = gameBoard.getBlock(x, y); // move to MazeGame
        Mapsite toMoveTo;
        switch(input) {
            case 'W':
            toMoveTo = currentBlock.getSide(Direction.North);
            if(toMoveTo.enter() == true) {
                setY(getY() + 1);
                // this.y += this.speed;
            }
            case 'S':
            toMoveTo = currentBlock.getSide(Direction.South);
            if(toMoveTo.enter() == true) {
                setY(getY() - 1);
                // this.y -= this.speed;
            }
            case 'A':
            toMoveTo = currentBlock.getSide(Direction.West);
            if(toMoveTo.enter() == true) {
                setX(getX() - 1);
                // this.x -= this.speed;
            }
            case 'D':
            toMoveTo = currentBlock.getSide(Direction.East);
            if(toMoveTo.enter() == true) {
                setX(getX() + 1);
                // this.x += this.speed;
            }
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


    public void draw(Batch batch) {
        batch.draw(playerTexture[0], x, y, 32, 32);
    }
}
