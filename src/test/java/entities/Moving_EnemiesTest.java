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

/**
 * Unit test for the Moving_Enemies class.
 */
public class Moving_EnemiesTest {

    private Board mockBoard;
    private Character mockPlayer;
    private Moving_Enemies movingEnemy;
    private TextureRegion mockTextureRegion;
    private Block mockBlock;
    Block upBlock;
    Block downBlock;
    Block leftBlock;
    Block rightBlock;

    // private static Logger log = Logger.getLogger(Moving_EnemiesTest.class.getName());

    private static int INIT_X = 6;
    private static int INIT_Y = 6;

    /**
     * Set up for all the test cases
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockPlayer = mock(Character.class);
        mockTextureRegion = mock(TextureRegion.class);
        mockBlock = mock(Block.class);
        movingEnemy = new Moving_Enemies(INIT_X, INIT_Y, mockTextureRegion);

        upBlock = mock(Block.class);
        downBlock = mock(Block.class);
        leftBlock = mock(Block.class);
        rightBlock = mock(Block.class);

        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);

        when(mockBoard.getBlock(INIT_X,INIT_Y + 1)).thenReturn(upBlock);
        when(mockBoard.getBlock(INIT_X,INIT_Y - 1)).thenReturn(downBlock);
        when(mockBoard.getBlock(INIT_X + 1,INIT_Y)).thenReturn(rightBlock);
        when(mockBoard.getBlock(INIT_X - 1,INIT_Y)).thenReturn(leftBlock);

        when(upBlock.enter()).thenReturn(true);
        when(downBlock.enter()).thenReturn(true);
        when(leftBlock.enter()).thenReturn(true);
        when(rightBlock.enter()).thenReturn(true);

        // try{
        //     FileHandler fileHandler = new FileHandler("C:/Users/caleb/CMPT276F24_group18/src/test/java/logging/status.log");
        //     log.addHandler(fileHandler);

        //     SimpleFormatter formatter = new SimpleFormatter();  

        //     fileHandler.setFormatter(formatter);
        // } catch (Exception e) {};
    }

    /**
     * moveYDistance is less than moveXDistance, yDistance is less than 0, and xDistance is less than 0.
     */
    @Test
    public void wantsToMoveUpElseRightTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X + 1);
        when(mockPlayer.getY()).thenReturn(INIT_Y + 3);

        // can move up
        testUp();

        // else move right
        testRight();

        // else move left
        testLeft();

        // else move down
        testDown();
    }

    /**
     * Wants to move up and to the right, with up being of greater priority.
     * moveYDistance is less than moveXDistance, yDistance is less than 0, and xDistance is greater than 0.
     */
    @Test
    public void wantsToMoveUpElseLeftTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X - 1);
        when(mockPlayer.getY()).thenReturn(INIT_Y + 3);
        
        // can move up
        testUp();
        
        // else move left
        testLeft();
        
        // else move right
        testRight();

        // else move down
        testDown();
    }

    /**
     * Wants to move up.
     * moveYDistance is less than moveXDistance, yDistance is less than 0, and xDistance = 0.
     */
    @Test
    public void wantsToMoveUpElseIdleTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X);
        when(mockPlayer.getY()).thenReturn(INIT_Y + 2);

        // can move up
        testUp();

        // else idle
        testIdle();
    }

    /**
     * Wants to move down and to the right, with down taking priority.
     * moveYDistance is less than moveXDistance, xDistance is less than 0, and yDistance is greater than 0.
     */
    @Test
    public void wantsToMoveDownElseRightTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X + 1);
        when(mockPlayer.getY()).thenReturn(INIT_Y - 4);

        // can move down
        testDown();

        // else move right
        testRight();

        // else move left
        testLeft();

        // else move up
        testUp();
    }

    /**
     * Wants to move down and left, with down taking priority.
     * moveYDistance is less than moveXDistance, yDistance is greater than 0, and xDistance is greater than 0.
     */
    @Test
    public void wantsToMoveDownElseLeftTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X - 1);
        when(mockPlayer.getY()).thenReturn(INIT_Y - 2);

        // can move down
        testDown();

        // else move left
        testLeft();

        // else move right
        testRight();

        // else move up
        testUp();
    }

    /**
     * Wants to move down.
     * moveYDistance is less than moveXDistance, yDistance is greater than 0, and xDistance = 0.
     */
    @Test
    public void wantsToMoveDownElseIdleTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X);
        when(mockPlayer.getY()).thenReturn(INIT_Y - 2);

        // can move down
        testDown();

        // else idle
        testIdle();
    }

    /**
     * Wants to move right and up, with right taking priority.
     * moveYDistance is greater than moveXDistance, yDistance is less than 0, and xDistance is less than 0.
     */
    @Test
    public void wantsToMoveRightElseUpTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X + 2);
        when(mockPlayer.getY()).thenReturn(INIT_Y + 1);

        // can move right
        testRight();

        // else move up
        testUp();

        // else move down
        testDown();

        // else move left
        testLeft();
    }

    /**
     * Wants to move right and down, with right taking priority.
     * moveYDistance is greater than moveXDistance, yDistance is greater than 0, and xDistance is less than 0.
     */
    @Test
    public void wantsToMoveRightElseDownTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X + 3);
        when(mockPlayer.getY()).thenReturn(INIT_Y - 1);

        // can move right
        testRight();

        // else move down
        testDown();

        // else move up
        testUp();

        // else move left
        testLeft();
    }

    /**
     * Wants to move right.
     * moveYDistance is greater than moveXDistance, yDistance = 0, and xDistance is less than 0.
     */
    @Test
    public void wantsToMoveRightElseIdleTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X + 2);
        when(mockPlayer.getY()).thenReturn(INIT_Y);

        // can move right
        testRight();

        // else stay idle
        testIdle();
    }

    /**
     * Wants to move left and up, with left taking priority.
     * moveYDistance is greater than moveXDistance, yDistance is less than 0, and xDistance is greater than 0.
     */
    @Test
    public void wantsToMoveLeftElseUpTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X - 2);
        when(mockPlayer.getY()).thenReturn(INIT_Y + 1);

        // else move left
        testLeft();

        // else move up
        testUp();

        // else move down
        testDown();

        // can move right

        testRight();        

    }

    /**
     * Wants to move left and down, with left taking priority.
     * moveYDistance is greater than moveXDistance, xDistance is greater than 0, and yDistance is greater than 0.
     */
    @Test
    public void wantsToMoveLeftElseDownTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X - 2);
        when(mockPlayer.getY()).thenReturn(INIT_Y - 1);

        // else move left
        testLeft();

        // else move down
        testDown();

        // else move up
        testUp();

        // can move right
        testRight();
    }

    /**
     * Wants to move left.
     * moveYDistance is greater than moveXDistance, xDistance is greater than 0, and yDistance = 0.
     */
    @Test
    public void wantsToMoveLeftElseIdleTest() {
        when(mockPlayer.getX()).thenReturn(INIT_X - 2);
        when(mockPlayer.getY()).thenReturn(INIT_Y);

        // can move left
        testLeft();

        // else stay idle
        testIdle();
    }

    //*** Utility functions ***//

    /**
     * Utility function for testing the enemy trying to move up.
     */
    private void testUp() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('W', result);

        movingEnemy.direction(result,mockBoard);

        movingEnemy.direction('S',mockBoard); // reset position
        when(upBlock.enter()).thenReturn(false); // block off up
    }
    /**
     * Utility function for testing the enemy trying to move down.
     */
    private void testDown() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('S', result);

        movingEnemy.direction(result,mockBoard);

        movingEnemy.direction('W',mockBoard); // reset position
        when(downBlock.enter()).thenReturn(false); // block off down
    }
    /**
     * Utility function for testing the enemy trying to move right.
     */
    private void testRight() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('D', result);

        movingEnemy.direction(result,mockBoard);

        movingEnemy.direction('A',mockBoard); // reset position
        when(rightBlock.enter()).thenReturn(false); // block off right
    }
    /**
     * Utility function for testing the enemy trying to move left.
     */
    private void testLeft() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('A', result);

        movingEnemy.direction(result,mockBoard);

        movingEnemy.direction('D',mockBoard); // reset position
        when(leftBlock.enter()).thenReturn(false); // block off left
    }
    /**
     * Utility function for testing the enemy not trying to move anywhere.
     */
    private void testIdle() {
        char result;

        result = movingEnemy.find_player(mockPlayer, mockBoard);
        assertEquals('I', result);

        movingEnemy.direction(result, mockBoard);
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public Moving_EnemiesTest() {};
}