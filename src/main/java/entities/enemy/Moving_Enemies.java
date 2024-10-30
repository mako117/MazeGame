package entities.enemy;
import directions.Direction;
import board.*;
import entities.Character;

class Moving_Enemies extends Enemies {
    /**
     * Moving Enemies Constructor
     */
    public Moving_Enemies(int init_x, int init_y) {
        super(init_x,init_y);        
    }
    public char find_player(Character aCharacter) {
        int xDistance = getX() - aCharacter.getX();
        int yDistance = getY() - aCharacter.getY();
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
        }
        
    }
}