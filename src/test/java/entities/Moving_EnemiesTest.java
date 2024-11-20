package entities;

import static org.junit.jupiter.api.Assertions.*;
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
     * Move towards player right
     */
    @Test
    public void MoveTorwardsPlayerRightTest() {

        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(5);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('D', result);
    }

    /**
     *
     */
    @Test
    public void MoveTowardsPlayerLeftTest() {

        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(1);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('A', result);


    }

    /**
     *
     */
    @Test
    public void MoveTowardsPlayerUpTest() {

        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(5);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('W', result);


    }

    /**
     *
     */
    @Test
    public void MoveTowardsPlayerDownTest() {

        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(1);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('S', result);


    }

    /**
     *
     */
    @Test
    public void IdleTest() {

        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(3);
        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(4,3).enter()).thenReturn(false);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('I', result);


    }

    /**
     *
     */
    @Test
    public void AvoidObstacleUpTest() {

        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(5);
        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);
        when(mockBoard.getBlock(4,3).enter()).thenReturn(false);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('A', result);

    }

    /**
     *
     */
    @Test
    public void AvoidObstacleDownTest() {

        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(5);
        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);
        when(mockBoard.getBlock(3,4).enter()).thenReturn(true);
        when(mockBoard.getBlock(2,3).enter()).thenReturn(false);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('D', result);


    }

    /**
     *
     */
    @Test
    public void ObstacleBelowTest() {

        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(1);
        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);
        when(mockBoard.getBlock(3,2).enter()).thenReturn(false);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('D', result);

    }

    /**
     *
     */
    @Test
    public void ObstacleBelowAndRightTest() {

        when(mockPlayer.getX()).thenReturn(1);
        when(mockPlayer.getY()).thenReturn(1);
        when(mockBoard.getBlock(anyInt(),anyInt())).thenReturn(mockBlock);
        when(mockBoard.getBlock(anyInt(),anyInt()).enter()).thenReturn(true);
        when(mockBoard.getBlock(3,2).enter()).thenReturn(false);
        when(mockBoard.getBlock(4,3).enter()).thenReturn(false);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('D', result);

    }

    /**
     *
     */
    @Test
    public void greaterXDistanceTest(){
        when(mockPlayer.getX()).thenReturn(3);
        when(mockPlayer.getY()).thenReturn(5);

        char result = movingEnemy.find_player(mockPlayer, mockBoard);
        movingEnemy.direction(result,mockBoard);
        assertEquals('W', result);
    }


}