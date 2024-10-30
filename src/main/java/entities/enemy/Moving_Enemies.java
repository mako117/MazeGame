package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;
import entities.Character;

class Moving_Enemies extends Enemies {
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

        if(moveYDistance < moveXDistance) {
            if(yDistance < 0) { // wants to move up
                if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                    return 'W';
                } else if(xDistance < 0) { // can't move up, so wants to move right
                    if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) { // can't move right, so tries left
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) { // tries down
                        return 'S';
                    }
                } else { // can't move up, so wants to move left
                    if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) { // can't move left, so tries right
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) { // tries down
                        return 'S';
                    }
                }
            }
            else { // wants to move down
                if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() - 1).enter() == true) {
                    return 'S';
                } else if(xDistance < 0) { // can't move down, so wants to move right
                    if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    }
                } else { // can't move down, so wants to move left
                    if(gameBoard.getBlock(aCharacter.getX() - 1, aCharacter.getY()).enter() == true) {
                        return 'A';
                    } else if(gameBoard.getBlock(aCharacter.getX() + 1, aCharacter.getY()).enter() == true) {
                        return 'D';
                    } else if(gameBoard.getBlock(aCharacter.getX(), aCharacter.getY() + 1).enter() == true) {
                        return 'W';
                    }
                }
            }
        }



/*
        if(moveYDistance < moveXDistance) {
            if((yDistance < 0)) {
                return 'W';
            } else {
                return 'S';
            }
        } else {
            if((xDistance < 0)) {
                return 'D';
            } else {
                return 'A';
            }
        } */
        
    }

}