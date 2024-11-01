package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;
import entities.Character;

public class Moving_Enemies extends Enemies {
    /**
     * Moving Enemies Constructor
     */


    public Moving_Enemies(int init_x, int init_y, TextureRegion texture) {
        super(init_x,init_y, new TextureRegion(new Texture("temp_ptero.png")));
    }
    public char find_player(Character aCharacter, Board gameBoard) {
        int xDistance = this.getX() - aCharacter.getX();
        int yDistance = this.getY() - aCharacter.getY();
        double moveXDistance;
        double moveYDistance;

        if(xDistance < 0) {
            moveXDistance = Math.sqrt( ((xDistance+1)^2 + yDistance^2) );
        } else {
            moveXDistance = Math.sqrt( ((xDistance-1)^2 + yDistance^2) );
        }
        if(yDistance < 0) {
            moveYDistance = Math.sqrt( ((yDistance+1)^2 + xDistance^2) );
        } else {
            moveYDistance = Math.sqrt( ((yDistance-1)^2 + xDistance^2) );
        }

        /*
        If moving along the y-axis will get the enemy closer to the player than moving
        along the x-axis will, the code
            1. Tries moving towards the player along the y-axis
            2. Tries moving towards the player along the x-axis
            3. Tries moving away from the player along the x-axis
            4. Moves away from the player along the y-axis
        If the enemy is lined up with the player on an axis, it will not move along that axis; 
        therefore once its x and y coordinates match those of the player, it will not move.
        This code assumes it is impossible for the enemy to have been boxed in on all sides.
        */
        if(moveYDistance < moveXDistance) {
            if(yDistance < 0) { // wants to move up
                if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                    return 'W';
                } else if(xDistance < 0) { // can't move up, so wants to move right
                    if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) { // can't move right, so tries left
                        return 'A';
                    } else { // moves down
                        return 'S';
                    }
                } else if(xDistance > 0) { // can't move up, so wants to move left
                    if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) { // can't move left, so tries right
                        return 'D';
                    } else { // moves down
                        return 'S';
                    }
                } else { // doesn't want to move on x-axis, can't move up, and moving down makes it worse; therefore it doesn't move
                    return 'I';
                }
            }
            else if (yDistance > 0) { // wants to move down
                if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                    return 'S';
                } else if(xDistance < 0) { // can't move down, so wants to move right
                    if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else {
                        return 'W';
                    }
                } else if(xDistance > 0) { // can't move down, so wants to move left
                    if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else {
                        return 'W';
                    }
                } else { // doesn't want to move on x-axis, can't move down, and moving up makes it worse; therefore it doesn't move
                    return 'I';
                }
            } else { // does not want to move on y-axis
                if(xDistance < 0) {
                    if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else {
                        return 'I';
                    }
                } else if (xDistance > 0) {
                    if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else {
                        return 'I';
                    }
                } else {
                    return 'I';
                }
            }
        }
        /*
        If moving along the x-axis will get the enemy closer to the player than moving
        along the y-axis will, the code
            1. Tries moving towards the player along the x-axis
            2. Tries moving towards the player along the y-axis
            3. Tries moving away from the player along the y-axis
            4. Moves away from the player along the x-axis
        If the enemy is lined up with the player on an axis, it will not move along that axis; 
        therefore once its x and y coordinates match those of the player, it will not move.
        This code assumes it is impossible for the enemy to have been boxed in on all sides.
        */
        else {
            if(xDistance < 0) { // wants to move right
                if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                    return 'D';
                } else if(yDistance < 0) { // can't move right, so wants to move up
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else {
                        return 'A';
                    }
                } else if(yDistance > 0) { // can't move right, so wants to move down
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else {
                        return 'A';
                    }
                } else {
                    return 'I';
                }
            }
            else if (xDistance > 0) { // wants to move left
                if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                    return 'A';
                } else if(yDistance < 0) { // can't move left, so wants to move up
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else {
                        return 'D';
                    }
                } else if (yDistance > 0) { // can't move left, so wants to move down
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else {
                        return 'D';
                    }
                } else {
                    return 'I';
                }
            } else { // does not want to move on x-axis
                if(yDistance < 0) {
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else {
                        return 'I';
                    }
                } else if (yDistance > 0) {
                    if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                        return 'S';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    } else {
                        return 'I';
                    }
                } else {
                    return 'I';
                }
            }
        }        
    }

}