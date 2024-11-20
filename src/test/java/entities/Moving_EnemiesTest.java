package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import entities.enemy.*;
import entities.Character;
import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;
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
    }

    /**
     * moveYDistance < moveXDistance, yDistance < 0, xDistance < 0
     */
    @Test
    public void wantsToMoveUpElseRightTest() {
        when(mockPlayer.getX()).thenReturn(4);
        when(mockPlayer.getY()).thenReturn(6);

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
     * moveYDistance < moveXDistance, yDistance < 0, xDistance > 0
     */
    @Test
    public void wantsToMoveUpElseLeftTest() {
        when(mockPlayer.getX()).thenReturn(2);
        when(mockPlayer.getY()).thenReturn(5);

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
     * moveYDistance < moveXDistance, yDistance < 0, xDistance = 0
     */
    @Test
    public void wantsToMoveUpElseIdleTest() {
        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(5);

        // can move up
        testUp();

        // else idle
        testIdle();
    }

    /**
     * moveYDistance < moveXDistance, yDistance > 0, xDistance < 0
     */
    @Test
    public void wantsToMoveDownElseRight() {
        when(mockPlayer.getX()).thenReturn(4);
        when(mockPlayer.getY()).thenReturn(1);

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
     * moveYDistance < moveXDistance, yDistance > 0, xDistance > 0
     */
    @Test
    public void wantsToMoveDownElseLeft() {
        when(mockPlayer.getX()).thenReturn(2);
        when(mockPlayer.getY()).thenReturn(1);

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
     * moveYDistance < moveXDistance, yDistance > 0, xDistance = 0
     */
    @Test
    public void wantsToMoveDownElseIdle() {
        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(1);

        // can move down
        testDown();

        // else idle
        testIdle();
    }

    /**
     * moveYDistance > moveXDistance, yDistance < 0, xDistance < 0
     */
    @Test
    public void wantsToMoveRightElseUp() {
        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(4);

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
     * moveYDistance > moveXDistance, yDistance > 0, xDistance < 0
     */
    @Test
    public void wantsToMoveRightElseDown() {
        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(2);

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
     * moveYDistance > moveXDistance, yDistance = 0, xDistance < 0
     */
    @Test
    public void wantsToMoveRightElseIdle() {
        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(3);

        // can move right
        testRight();

        // else stay idle
        testIdle();
    }

    /**
     * moveYDistance > moveXDistance, yDistance < 0, xDistance > 0
     */
    @Test
    public void wantsToMoveLeftElseUp() {
        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(4);

        // else move left
        testLeft();

        // else move up
        testUp();

        // else move down
        testDown();

        // can move right
        testRight();        
    }

    @Test
    public void wantsToMoveLeftElseDown() {
        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(2);

        // else move left
        testLeft();

        // else move down
        testDown();

        // else move up
        testUp();

        // can move right
        testRight();        
    }

    @Test
    public void wantsToMoveLeftElseIdle() {
        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(3);

        // can move left
        testLeft();

        // else stay idle
        testIdle();
    }


    //*** Utility functions ***//
    private void testUp() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('W', result);
        movingEnemy.direction('S',mockBoard); // reset position
        when((mockBoard.getBlock(3,4)).enter()).thenReturn(false); // block off up
    }
    private void testDown() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('S', result);
        movingEnemy.direction('W',mockBoard); // reset position
        when((mockBoard.getBlock(3,2)).enter()).thenReturn(false); // block off down
    }
    private void testRight() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('D', result);
        movingEnemy.direction('A',mockBoard); // reset position
        when((mockBoard.getBlock(4,3)).enter()).thenReturn(false); // block off right
    }
    private void testLeft() {
        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('A', result);
        movingEnemy.direction('D',mockBoard); // reset position
        when((mockBoard.getBlock(2,3).enter())).thenReturn(false); // block off down
    }
    private void testIdle() {
        char result;
        when((mockBoard.getBlock(3,2)).enter()).thenReturn(false);
        result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result, mockBoard);
        assertEquals('I', result);
    }

    @Test
    public void greaterXDistanceTest(){
        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(5);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('W', result);
    }
}