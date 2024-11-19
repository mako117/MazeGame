package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import directions.Direction;
import entities.enemy.Enemies;
import entities.enemy.PatrollingEnemies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import entities.enemy.Enemies;
import board.Board;
import board.Block;

public class PatrollingEnemiesTest {

    private PatrollingEnemies enemy;
    private Board mockBoard;
    private Block mockBlock;
    private Batch mockBatch;
    private TextureRegion mockTextureRegion;

    /**
     * The setup to create the initial enemy and some mock classes
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockBlock = mock(Block.class);
        mockBatch = mock(Batch.class);
        mockTextureRegion = mock(TextureRegion.class);

        enemy = new PatrollingEnemies(1,1,Direction.Up,1,1,1,10,mockTextureRegion);
    }

    /**
     * Test to check the initial position of the enemy
     */
    @Test
    public void initialPositionTest() {
        assertEquals(1,enemy.getXMax());
        assertEquals(10,enemy.getYMax());
        assertEquals(1,enemy.getXMin());
        assertEquals(1,enemy.getYMin());

        //assertEquals('W', enemy.getMoveTo());
    }

    /**
     * Test invalid min and max x and y
     */
    @Test
    public void invalidXYTest() {
        enemy = new PatrollingEnemies(1,1,Direction.Up,-1,-1,-1,-10,mockTextureRegion);
    }

    /**
     * Test Path Creation and Follow
     */
    @Test
    public void createdPathYTest() {
        enemy.direction('W', mockBoard);
        assertEquals('W', enemy.getMoveTo());

        enemy.setY(10);
        enemy.direction('S', mockBoard);
        assertEquals('S', enemy.getMoveTo());

        enemy.setFacing(Direction.Down);
        enemy.direction('S', mockBoard);
        assertEquals('S', enemy.getMoveTo());
        enemy.setY(1);
        enemy.direction('S', mockBoard);
        assertEquals('W', enemy.getMoveTo());

    }
    /**
     * Test Path Creation and Follow
     */
    @Test
    public void createdPathXTest() {
        enemy = new PatrollingEnemies(1,1,Direction.Right,1,10,1,1,mockTextureRegion);

        enemy.direction('D', mockBoard);
        assertEquals('D', enemy.getMoveTo());

        enemy.setX(10);
        enemy.direction('A', mockBoard);
        assertEquals('A', enemy.getMoveTo());

        enemy.setFacing(Direction.Left);
        enemy.direction('A', mockBoard);
        assertEquals('A', enemy.getMoveTo());
        enemy.setX(1);
        enemy.direction('A', mockBoard);
        assertEquals('D', enemy.getMoveTo());

    }

    /**
     * Test Directional Block movement
     */
    @Test
    public void directionBlockTest() {

        enemy = new PatrollingEnemies(2, 2, Direction.Up, 0, 5, 0, 5, mockTextureRegion);

        when(mockBoard.getBlock(2, 2)).thenReturn(mockBlock);

        when(mockBoard.getBlock(2, 3)).thenReturn(mockBlock);

        when(mockBoard.getBlock(2, 1)).thenReturn(mockBlock);


        when(mockBoard.getBlock(1, 2)).thenReturn(mockBlock);


        when(mockBoard.getBlock(3, 2)).thenReturn(mockBlock);


        enemy.setFacing(Direction.Up);
        assertEquals(2, enemy.getY());
        boolean moved = enemy.direction('W', mockBoard);
        assertTrue(moved);
        assertEquals(3, enemy.getY());


        enemy.setFacing(Direction.Down);
        assertEquals(3, enemy.getY());
        moved = enemy.direction('S', mockBoard);
        assertTrue(moved);
        assertEquals(2, enemy.getY());


        enemy.setFacing(Direction.Left);
        assertEquals(2, enemy.getX());
        moved = enemy.direction('A', mockBoard);
        assertTrue(moved);
        assertEquals(1, enemy.getX());

        enemy.setFacing(Direction.Right);
        assertEquals(1, enemy.getX());
        moved = enemy.direction('D', mockBoard);
        assertTrue(moved);
        assertEquals(2, enemy.getX());


        when(mockBoard.getBlock(2, 3)).thenReturn(null);

        when(mockBoard.getBlock(2, 1)).thenReturn(null);


        when(mockBoard.getBlock(1, 2)).thenReturn(null);

        when(mockBoard.getBlock(3, 2)).thenReturn(null);



        moved = enemy.direction('W', mockBoard);
        assertEquals(2, enemy.getY());


        moved = enemy.direction('S', mockBoard);
        assertEquals(2, enemy.getY());

        moved = enemy.direction('A', mockBoard);
        assertEquals(2, enemy.getX());

        moved = enemy.direction('D', mockBoard);
        assertEquals(2, enemy.getX());



    }

}
