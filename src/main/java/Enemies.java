/**
 * Enemies Class
 */

public class Enemies {

    /**
     * Default Enemies Constructor
     */
    Enemies(){
        enemyNr = enemyCnt++;
        System.out.println("Enemy #" + enemyNr + " created.");

    }

    /**
     * Enemies Constructor with starting position
     * 
     * @param initial_x Starting x position
     * @param initial_y Starting y position
     */
    Enemies(int initial_x, int initial_y) {
        x = initial_x;
        y = initial_y;
        enemyNr = enemyCnt++;
        System.out.println("Enemy #" + enemyNr + " created on x: " + initial_x + " and y: " + initial_y);

    }

    /**
     * Moves the Enemy
     */
    public abstract void Move(MazeGame game, int x, int y) {

    }


    private int enemyNr;
    private static int enemyCnt = 1;
    private int x;
    private int y;


}