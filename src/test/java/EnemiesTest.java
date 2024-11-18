import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import entities.enemy.Enemies;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import entities.enemy.Enemies;
import board.Board;
import board.Block;

public class EnemiesTest {

    private Enemies enemy;
    private Board mockBoard;
    private Block mockBlock;

    /**
     * The setup to create the initial enemy and some mock classes
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockBlock = mock(Block.class);

        enemy = new Enemies(1,1,mock(TextureRegion.class));
    }

    /**
     * Test to check the initial position of the enemy
     */
    @Test
    public void initialPositionTest() {
        assertEquals(1, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    /**
     * Test to move enemy Up
     */
    @Test
    public void moveUpTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        enemy.direction('W', mockBoard);
        assertEquals(1, enemy.getX());
        assertEquals(2, enemy.getY());
    }

    /**
     * Test to move enemy Down
     */
    @Test
    public void moveDownTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        enemy.direction('S', mockBoard);
        assertEquals(1, enemy.getX());
        assertEquals(0, enemy.getY());
    }

    /**
     * Test to move enemy Left
     */
    @Test
    public void moveLeftTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        enemy.direction('A', mockBoard);
        assertEquals(0, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    /**
     * Test to move enemy Right
     */
    @Test
    public void moveRightTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        enemy.direction('D', mockBoard);
        assertEquals(2, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    /**
     * Test to see if enemy stays in position if unable to move
     */
    @Test
    public void unableToMoveTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(false);
        assertFalse(enemy.direction('W', mockBoard));
        assertFalse(enemy.direction('S', mockBoard));
        assertFalse(enemy.direction('A', mockBoard));
        assertFalse(enemy.direction('D', mockBoard));
        assertEquals(1, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    /**
     * Entering a null block Test
     */
    @Test
    public void nullBlockTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(null);
        when(mockBlock.enter()).thenReturn(true);
        assertFalse(enemy.direction('W', mockBoard));
        assertFalse(enemy.direction('S', mockBoard));
        assertFalse(enemy.direction('A', mockBoard));
        assertFalse(enemy.direction('D', mockBoard));
    }

    /**
     * Test for facing the right direction for the enemy after moving
     */
    @Test
    public void facingDirectionTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);

        enemy.direction('W', mockBoard);
        assertEquals(Direction.Up, enemy.getFacing());

        enemy.direction('S', mockBoard);
        assertEquals(Direction.Down, enemy.getFacing());

        enemy.direction('A', mockBoard);
        assertEquals(Direction.Left, enemy.getFacing());

        enemy.direction('D', mockBoard);
        assertEquals(Direction.Right, enemy.getFacing());
    }

    /**
     * Wrong input test
     */
    @Test
    public void badInputTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        assertFalse(enemy.direction('B', mockBoard));
    }

    /**
     *
     */
    @Test
    public void entityOutOfBoundsTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        enemy.direction('A', mockBoard);
        enemy.direction('A', mockBoard);
        enemy.direction('S', mockBoard);
        enemy.direction('S', mockBoard);

        assertEquals(0, enemy.getX());
        assertEquals(0, enemy.getY());
    }

}
