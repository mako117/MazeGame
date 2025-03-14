package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import board.*;
import entities.Character;
import directions.Direction;

/**
 * A moving enemy that moves in the direction of the player at all times.
 */
public class Moving_Enemies extends Enemies {

    /**
     * Create a new moving enemy that moves towards the player.
     * @param init_x The initial x position.
     * @param init_y The initial y position.
     * @param texture The texture.
     */
    public Moving_Enemies(int init_x, int init_y, TextureRegion texture) {
        super(init_x,init_y, texture);
    }

    /**
     * Return the direction that the enemy has to take to get closer to the player.
     * @param aCharacter The player.
     * @param gameBoard The game board.
     * @return The direction as a char.
     */
    public char find_player(Character aCharacter, Board gameBoard) {
        int xDistance = this.getX() - aCharacter.getX();
        int yDistance = this.getY() - aCharacter.getY();
        double[] results = findPlayerCalculations(xDistance, yDistance);
        double moveXDistance = results[0];
        double moveYDistance = results[1];

        if(moveYDistance < moveXDistance) {
            return wantsToMoveAlongY(xDistance, yDistance, gameBoard);
        } else {
            return wantsToMoveAlongX(xDistance, yDistance, gameBoard);
        }
    }
    

    /**
     * Calculates the distance from the moving enemy to the character if the enemy moves along the x-axis and if it moves along the y-axis.
     * @param xDistance The enemy's <code>x</code>-coordinate - the character's <code>x</code>-coordinate.
     * @param yDistance The enemy's <code>y</code>-coordinate - the character's <code>y</code>-coordinate.
     * @return  A length 2 double array with the distance after moving along the x-axis stored first, and the y-axis distance stored second.
     */
    private double[] findPlayerCalculations(int xDistance, int yDistance) {
        double POSITIVE_INFINITY = 1.0 / 0.0;
        double moveXDistance = (xDistance == 0) ? POSITIVE_INFINITY : calculateMovementDistance(xDistance, yDistance, true, xDistance < 0);
        double moveYDistance = (yDistance == 0) ? POSITIVE_INFINITY : calculateMovementDistance(xDistance, yDistance, false, yDistance < 0);
        return new double[]{moveXDistance, moveYDistance};
    }

    /**
     * Calculates the distance from the moving enemy to the character
     * @param xDistance The enemy's <code>x</code>-coordinate - the character's <code>x</code>-coordinate.
     * @param yDistance The enemy's <code>y</code>-coordinate - the character's <code>y</code>-coordinate.
     * @param moveX Determines if enemy moves along x axis or y axis
     * @param increase Determines the direction of the movement
     * @return The distance after simulating a movement.
     */
    private double calculateMovementDistance(int xDistance, int yDistance,boolean moveX, boolean increase ){
        int xDelta = moveX ? (increase ? 1 : -1) : 0;
        int yDelta = moveX ? 0 : (increase ? 1 : -1);
        return Math.sqrt(Math.pow(xDistance + xDelta, 2) + Math.pow(yDistance + yDelta, 2));
    }

    /**
     * If moving along the x-axis will get the enemy closer to the player than moving
     * along the y-axis will, the code
     *      1. Tries moving towards the player along the x-axis
     *      2. Tries moving towards the player along the y-axis
     *      3. Tries moving away from the player along the y-axis
     *      4. Moves away from the player along the x-axis
     * If the enemy is lined up with the player on an axis, it will not move along that axis;
     * therefore once its x and y coordinates match those of the player, it will not move.
     * This code assumes it is impossible for the enemy to have been boxed in on all sides.
     * @param xDistance The enemy's <code>x</code>-coordinate - the character's <code>x</code>-coordinate.
     * @param yDistance The enemy's <code>y</code>-coordinate - the character's <code>y</code>-coordinate.
     * @param gameBoard The game board.
     * @return  One of the input characters 'W', 'A', 'S', 'D', or 'I', corresponding to the directions 
     * North, West, South, East, and None.
     */
    private char wantsToMoveAlongX(int xDistance, int yDistance, Board gameBoard) {
        if(xDistance < 0) { // wants to move right
            if(gameBoard.getBlock(this.getX() + 1, this.getY()).enter() == true) {
                return 'D';
            } else if(yDistance < 0) { // can't move right, so wants to move up
                if(gameBoard.getBlock(this.getX(), this.getY() + 1).enter() == true) {
                    return 'W';
                } else if(gameBoard.getBlock(this.getX(), this.getY() - 1).enter() == true) {
                    return 'S';
                } else {
                    return 'A';
                }
            } else if(yDistance > 0) { // can't move right, so wants to move down
                if(gameBoard.getBlock(this.getX(), this.getY() - 1).enter() == true) {
                    return 'S';
                } else if(gameBoard.getBlock(this.getX(), this.getY() + 1).enter() == true) {
                    return 'W';
                } else {
                    return 'A';
                }
            } else {
                return 'I';
            }
        }
        else /* if (xDistance > 0) */ { // wants to move left
            if(gameBoard.getBlock(this.getX() - 1, this.getY()).enter() == true) {
                return 'A';
            } else if(yDistance < 0) { // can't move left, so wants to move up
                if(gameBoard.getBlock(this.getX(), this.getY() + 1).enter() == true) {
                    return 'W';
                } else if(gameBoard.getBlock(this.getX(), this.getY() - 1).enter() == true) {
                    return 'S';
                } else {
                    return 'D';
                }
            } else if (yDistance > 0) { // can't move left, so wants to move down
                if(gameBoard.getBlock(this.getX(), this.getY() - 1).enter() == true) {
                    return 'S';
                } else if(gameBoard.getBlock(this.getX(), this.getY() + 1).enter() == true) {
                    return 'W';
                } else {
                    return 'D';
                }
            } else {
                return 'I';
            }
        }
    }

    /**
     * If moving along the y-axis will get the enemy closer to the player than moving
     * along the x-axis will, the code
     *      1. Tries moving towards the player along the y-axis
     *      2. Tries moving towards the player along the x-axis
     *      3. Tries moving away from the player along the x-axis
     *      4. Moves away from the player along the y-axis
     * If the enemy is lined up with the player on an axis, it will not move along that axis; 
     * therefore once its x and y coordinates match those of the player, it will not move.
     * This code assumes it is impossible for the enemy to have been boxed in on all sides.
     * @param xDistance The enemy's <code>x</code>-coordinate - the character's <code>x</code>-coordinate.
     * @param yDistance The enemy's <code>y</code>-coordinate - the character's <code>y</code>-coordinate.
     * @param gameBoard The game board.
     * @return  One of the input characters 'W', 'A', 'S', 'D', or 'I', corresponding to the directions
     * North, West, South, East, and None.
     */
    private char wantsToMoveAlongY(int xDistance, int yDistance, Board gameBoard) {
        if(yDistance < 0) { // wants to move up
            if(gameBoard.getBlock(this.getX(), this.getY() + 1).enter() == true) {
                return 'W';
            } else if(xDistance < 0) { // can't move up, so wants to move right
                if(gameBoard.getBlock(this.getX() + 1, this.getY()).enter() == true) {
                    return 'D';
                } else if(gameBoard.getBlock(this.getX() - 1, this.getY()).enter() == true) { // can't move right, so tries left
                    return 'A';
                } else { // moves down
                    return 'S';
                }
            } else if(xDistance > 0) { // can't move up, so wants to move left
                if(gameBoard.getBlock(this.getX() - 1, this.getY()).enter() == true) {
                    return 'A';
                } else if(gameBoard.getBlock(this.getX() + 1, this.getY()).enter() == true) { // can't move left, so tries right
                    return 'D';
                } else { // moves down
                    return 'S';
                }
            } else { // doesn't want to move on x-axis, can't move up, and moving down makes it worse; therefore it doesn't move
                return 'I';
            }
        }
        else /*if (yDistance > 0) */ { // wants to move down
            if(gameBoard.getBlock(this.getX(), this.getY() - 1).enter() == true) {
                return 'S';
            } else if(xDistance < 0) { // can't move down, so wants to move right
                if(gameBoard.getBlock(this.getX() + 1, this.getY()).enter() == true) {
                    return 'D';
                } else if(gameBoard.getBlock(this.getX() - 1, this.getY()).enter() == true) {
                    return 'A';
                } else {
                    return 'W';
                }
            } else if(xDistance > 0) { // can't move down, so wants to move left
                if(gameBoard.getBlock(this.getX() - 1, this.getY()).enter() == true) {
                    return 'A';
                } else if(gameBoard.getBlock(this.getX() + 1, this.getY()).enter() == true) {
                    return 'D';
                } else {
                    return 'W';
                }
            } else { // doesn't want to move on x-axis, can't move down, and moving up makes it worse; therefore it doesn't move
                return 'I';
            }
        }
    }

}