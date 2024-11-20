package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.logging.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import entities.enemy.*;
import entities.Character;
import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import board.Board;
import board.Block;

public class Moving_EnemiesTest {

    private Board mockBoard;
    private Character mockPlayer;
    private Moving_Enemies movingEnemy;
    private TextureRegion mockTextureRegion;
    private Block mockBlock;

    private static Logger log = Logger.getLogger(Moving_EnemiesTest.class.getName());

    /**
     * Set up for all the test cases
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockPlayer = mock(Character.class);
        mockTextureRegion = mock(TextureRegion.class);
        mockBlock = mock(Block.class);
        movingEnemy = new Moving_Enemies(3, 3, mockTextureRegion);

        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);

        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);
        try{
            FileHandler fileHandler = new FileHandler("C:/Users/caleb/CMPT276F24_group18/src/test/java/logging/status.log");
            log.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();  
            fileHandler.setFormatter(formatter);
        } catch (Exception e) {};
    }

    /**
     * moveYDistance < moveXDistance, yDistance < 0, xDistance < 0
     */
    @Test
    public void wantsToMoveUpElseRightTest() {
        when(mockPlayer.getX()).thenReturn(4);
        when(mockPlayer.getY()).thenReturn(6);

        // can move up
        log.info("3 4 enter before = " + mockBoard.getBlock(3,4).enter()); // up
        log.info("4 3 enter before = " + mockBoard.getBlock(4,3).enter()); // right
        log.info("2 3 enter before = " + mockBoard.getBlock(2,3).enter()); // left
        log.info("3 2 enter before = " + mockBoard.getBlock(4,3).enter()); // down
        testUp();
        log.info("3 4 enter after = " + mockBoard.getBlock(3,4).enter()); // up
        log.info("4 3 enter after = " + mockBoard.getBlock(4,3).enter()); // right
        log.info("2 3 enter after = " + mockBoard.getBlock(2,3).enter()); // left
        log.info("3 2 enter after = " + mockBoard.getBlock(4,3).enter()); // down

        // else move right
        testRight();

        // else move left
        testLeft();

        // else move down
        testDown();
    }

    // /**
    //  * moveYDistance < moveXDistance, yDistance < 0, xDistance > 0
    //  */
    // @Test
    // public void wantsToMoveUpElseLeftTest() {
    //     when(mockPlayer.getX()).thenReturn(2);
    //     when(mockPlayer.getY()).thenReturn(5);

    //     // can move up
    //     testUp();

    //     // else move left
    //     testLeft();

    //     // else move right
    //     testRight();

    //     // else move down
    //     testDown();
    // }

    // /**
    //  * moveYDistance < moveXDistance, yDistance < 0, xDistance = 0
    //  */
    // @Test
    // public void wantsToMoveUpElseIdleTest() {
    //     when(mockPlayer.getX()).thenReturn(3);
    //     when(mockPlayer.getY()).thenReturn(5);

    //     // can move up
    //     testUp();

    //     // else idle
    //     testIdle();
    // }

    // /**
    //  * moveYDistance < moveXDistance, yDistance > 0, xDistance < 0
    //  */
    // @Test
    // public void wantsToMoveDownElseRight() {
    //     when(mockPlayer.getX()).thenReturn(4);
    //     when(mockPlayer.getY()).thenReturn(1);

    //     // can move down
    //     testDown();

    //     // else move right
    //     testRight();

    //     // else move left
    //     testLeft();

    //     // else move up
    //     testUp();
    // }

    // /**
    //  * moveYDistance < moveXDistance, yDistance > 0, xDistance > 0
    //  */
    // @Test
    // public void wantsToMoveDownElseLeft() {
    //     when(mockPlayer.getX()).thenReturn(2);
    //     when(mockPlayer.getY()).thenReturn(1);

    //     // can move down
    //     testDown();

    //     // else move left
    //     testLeft();

    //     // else move right
    //     testRight();

    //     // else move up
    //     testUp();
    // }

    // /**
    //  * moveYDistance < moveXDistance, yDistance > 0, xDistance = 0
    //  */
    // @Test
    // public void wantsToMoveDownElseIdle() {
    //     when(mockPlayer.getX()).thenReturn(3);
    //     when(mockPlayer.getY()).thenReturn(1);

    //     // can move down
    //     testDown();

    //     // else idle
    //     testIdle();
    // }

    // /**
    //  * moveYDistance > moveXDistance, yDistance < 0, xDistance < 0
    //  */
    // @Test
    // public void wantsToMoveRightElseUp() {
    //     when(mockPlayer.getX()).thenReturn(5);
    //     when(mockPlayer.getY()).thenReturn(4);

    //     // can move right
    //     testRight();

    //     // else move up
    //     testUp();

    //     // else move down
    //     testDown();

    //     // else move left
    //     testLeft();
    // }

    // /**
    //  * moveYDistance > moveXDistance, yDistance > 0, xDistance < 0
    //  */
    // @Test
    // public void wantsToMoveRightElseDown() {
    //     when(mockPlayer.getX()).thenReturn(5);
    //     when(mockPlayer.getY()).thenReturn(2);

    //     // can move right
    //     testRight();

    //     // else move down
    //     testDown();

    //     // else move up
    //     testUp();

    //     // else move left
    //     testLeft();
    // }

    // /**
    //  * moveYDistance > moveXDistance, yDistance = 0, xDistance < 0
    //  */
    // @Test
    // public void wantsToMoveRightElseIdle() {
    //     when(mockPlayer.getX()).thenReturn(5);
    //     when(mockPlayer.getY()).thenReturn(3);

    //     // can move right
    //     testRight();

    //     // else stay idle
    //     testIdle();
    // }

    // /**
    //  * moveYDistance > moveXDistance, yDistance < 0, xDistance > 0
    //  */
    // @Test
    // public void wantsToMoveLeftElseUp() {
    //     when(mockPlayer.getX()).thenReturn(1);
    //     when(mockPlayer.getY()).thenReturn(4);

    //     // else move left
    //     testLeft();

    //     // else move up
    //     testUp();

    //     // else move down
    //     testDown();

    //     // can move right
    //     testRight();        
    // }

    // @Test
    // public void wantsToMoveLeftElseDown() {
    //     when(mockPlayer.getX()).thenReturn(1);
    //     when(mockPlayer.getY()).thenReturn(2);

    //     // else move left
    //     testLeft();

    //     // else move down
    //     testDown();

    //     // else move up
    //     testUp();

    //     // can move right
    //     testRight();        
    // }

    // @Test
    // public void wantsToMoveLeftElseIdle() {
    //     when(mockPlayer.getX()).thenReturn(1);
    //     when(mockPlayer.getY()).thenReturn(3);

    //     // can move left
    //     testLeft();

    //     // else stay idle
    //     testIdle();
    // }

    // @Test
    // public void greaterXDistanceTest(){
    //     when(mockPlayer.getX()).thenReturn(3);
    //     when(mockPlayer.getY()).thenReturn(5);

    //     char result = movingEnemy.find_player(mockPlayer, mockBoard);
    //     movingEnemy.direction(result,mockBoard);
    //     assertEquals('W', result);
    // }

    // @Test
    // public void checkFindPlayerCalculations() {
    //     // moveYDistance < moveXDistance, xDistance < 0, yDistance < 0
    //     when(mockPlayer.getX()).thenReturn(4);
    //     when(mockPlayer.getY()).thenReturn(6);
    //     int xDistance = movingEnemy.getX() - mockPlayer.getX();
    //     int yDistance = movingEnemy.getY() - mockPlayer.getY();
    //     double[] resultArray = findPlayerCalculations(xDistance, yDistance);
    //     double moveXDistance = resultArray[0];
    //     double moveYDistance = resultArray[1];
    //     assertEquals(true, xDistance < 0);
    //     assertEquals(true, yDistance < 0);
    //     assertEquals(true, moveYDistance < moveXDistance);

    //     // moveYDistance < moveXDistance, xDistance > 0, yDistance < 0
    //     when(mockPlayer.getX()).thenReturn(2);
    //     when(mockPlayer.getY()).thenReturn(5);
    //     xDistance = movingEnemy.getX() - mockPlayer.getX();
    //     yDistance = movingEnemy.getY() - mockPlayer.getY();
    //     resultArray = findPlayerCalculations(xDistance, yDistance);
    //     moveXDistance = resultArray[0];
    //     moveYDistance = resultArray[1];
    //     assertEquals(true, xDistance > 0);
    //     assertEquals(true, yDistance < 0);
    //     assertEquals(true, moveYDistance < moveXDistance);

    // }


    //*** Utility functions ***//
    private void testUp() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('W', result);

        movingEnemy.direction(result,mockBoard);
        // checkPosition(result, 3, 3);

        movingEnemy.direction('S',mockBoard); // reset position
        // checkPosition(result, 3, 4);
        when((mockBoard.getBlock(3,4)).enter()).thenReturn(false); // block up down
    }
    private void testDown() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('S', result);

        movingEnemy.direction(result,mockBoard);
        // checkPosition(result, 3, 3);

        movingEnemy.direction('W',mockBoard); // reset position
        // checkPosition(result, 3, 2);
        when((mockBoard.getBlock(3,2)).enter()).thenReturn(false); // block off down
    }
    private void testRight() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('D', result);

        movingEnemy.direction(result,mockBoard);
        // checkPosition(result, 3, 3);

        movingEnemy.direction('A',mockBoard); // reset position
        // checkPosition(result, 4, 3);
        when((mockBoard.getBlock(4,3)).enter()).thenReturn(false); // block off right
    }
    private void testLeft() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('A', result);

        movingEnemy.direction(result,mockBoard);
        // checkPosition(result, 3, 3);

        movingEnemy.direction('D',mockBoard); // reset position
        // checkPosition(result, 2, 3);
        when((mockBoard.getBlock(2,3).enter())).thenReturn(false); // block off down
    }
    private void testIdle() {
        char result;

        result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('I', result);

        movingEnemy.direction(result, mockBoard);
        // checkPosition(result, 3, 3);
    }
    private void checkPosition(char input, int startX, int startY) {
        switch(input) {
            case 'W':
                System.out.println();
                assertEquals(startX, movingEnemy.getX());
                assertEquals(startY+1, movingEnemy.getY());
            break;

            case 'A':
                assertEquals(startX-1, movingEnemy.getX());
                assertEquals(startY, movingEnemy.getY());
            break;

            case 'S':
                assertEquals(startX, movingEnemy.getX());
                assertEquals(startY-1, movingEnemy.getY());
            break;

            case 'D':
                assertEquals(startX+1, movingEnemy.getX());
                assertEquals(startY, movingEnemy.getY());
            break;

            case 'I':
                assertEquals(startX, movingEnemy.getX());
                assertEquals(startY, movingEnemy.getY());
            break;
        }
    }

    private double[] findPlayerCalculations(int xDistance, int yDistance) {
        double POSITIVE_INFINITY = 1.0 / 0.0;
        double moveXDistance;
        double moveYDistance;
        if(xDistance < 0) {
            moveXDistance = Math.sqrt( Math.pow(xDistance + 1, 2) + Math.pow(yDistance, 2) );
        } else if (xDistance > 0) {
            moveXDistance = Math.sqrt( Math.pow(xDistance - 1, 2) + Math.pow(yDistance, 2) );
        } else {
            moveXDistance = POSITIVE_INFINITY;
        }
        if(yDistance < 0) {
            moveYDistance = Math.sqrt( Math.pow(xDistance, 2) + Math.pow(yDistance + 1, 2) );
        } else if (yDistance > 0) {
            moveYDistance = Math.sqrt( Math.pow(xDistance, 2) + Math.pow(yDistance - 1, 2) );
        } else {
            moveYDistance = POSITIVE_INFINITY;
        }

        log.info("\nplayerX = " + mockPlayer.getX() + ", playerY = " + mockPlayer.getY() + "\nxDistance = " + xDistance + ", yDistance = " + yDistance + "\nmoveXDistance = " + moveXDistance +", moveYDistance = " + moveYDistance);

        double [] array = {moveXDistance, moveYDistance};
        return array;
    }
}