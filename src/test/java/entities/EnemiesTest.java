package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
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

        enemy = new Enemies(1,1,mockTextureRegion);
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

    @Test
    public void testDrawNone() {

        enemy.setX(1);
        enemy.setY(1);
        enemy.setFacing(Direction.None);
        int tileSize = 32;
        float offset = 0.0f;

        enemy.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * enemy.getX())), eq((float)(tileSize* enemy.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }
    
    /**
     * Test drawing up with offset
     */
    @Test
    public void testDrawUp() {

        enemy.setX(1);
        enemy.setY(1);
        enemy.setFacing(Direction.Up);
        int tileSize = 32;
        float offset = 10.0f;

        enemy.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * enemy.getX())), eq((float)(tileSize* enemy.getY() - offset)), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing down with offset
     */
    @Test
    public void testDrawDown() {

        enemy.setX(1);
        enemy.setY(2);
        enemy.setFacing(Direction.Down);
        int tileSize = 32;
        float offset = 10.0f;

        enemy.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * enemy.getX())), eq((float)(tileSize* enemy.getY() + offset)), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing Right with offset
     */
    @Test
    public void testDrawRight() {

        enemy.setX(1);
        enemy.setY(1);
        enemy.setFacing(Direction.Right);
        int tileSize = 32;
        float offset = 10.0f;

        enemy.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * enemy.getX() - offset)), eq((float)(tileSize* enemy.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing Left with offset
     */
    @Test
    public void testDrawLeft() {

        enemy.setX(2);
        enemy.setY(1);
        enemy.setFacing(Direction.Left);
        int tileSize = 32;
        float offset = 10.0f;

        enemy.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * enemy.getX() + offset)), eq((float)(tileSize* enemy.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }


    /**
     * Test for drawing with no offset
     */
    @Test
    public void testDrawWithoutOffset() {
        // Setting mock position and facing direction
        enemy.setX(1);
        enemy.setY(1);
        enemy.setFacing(Direction.Up);  // Set facing up
        int tileSize = 32;
        float offset = 0.0f;  // Zero offset

        // Call the draw method
        enemy.draw(mockBatch, tileSize, offset);

        // Verify that batch.draw() was called without offset
        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize* enemy.getX())), eq((float)(tileSize* enemy.getY())), eq((float)(tileSize)), eq((float) (tileSize)));
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public EnemiesTest() {};

}
