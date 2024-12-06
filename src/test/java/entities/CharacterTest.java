package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;
import directions.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import entities.Character;
import board.Board;
import board.Block;

/**
 * Unit test for the Character class.
 */
public class CharacterTest {

    private Character character;
    private Board mockBoard;
    private Block mockBlock;
    private Batch mockBatch;
    private TextureRegion mockTextureRegion;
    private Exception exception;


    /**
     * The setup to create the initial character and some mock classes
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockBlock = mock(Block.class);
        mockBatch = mock(Batch.class);
        mockTextureRegion = mock(TextureRegion.class);

        character = new Character(mockTextureRegion, 1, 1);
    }

    /**
     * Test initial values for super
     */
    @Test
    public void initialValuesTest() {
        character = new Character(mockTextureRegion);
        assertEquals(1, character.getSpeed());
        assertEquals(0, character.getScore());
    }
    /**
     * Test to check the initial position of the character
     */
    @Test
    public void initialPositionTest() {
        assertEquals(1, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     * Test to move Character Up
     */
    @Test
    public void moveUpTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        character.direction('W', mockBoard);
        assertEquals(1, character.getX());
        assertEquals(2, character.getY());
    }

    /**
     * Test to move Character Down
     */
    @Test
    public void moveDownTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        character.direction('S', mockBoard);
        assertEquals(1, character.getX());
        assertEquals(0, character.getY());
    }

    /**
     * Test to move character Left
     */
    @Test
    public void moveLeftTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        character.direction('A', mockBoard);
        assertEquals(0, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     * Test to move character Right
     */
    @Test
    public void moveRightTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);
        character.direction('D', mockBoard);
        assertEquals(2, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     * Test to see if Character stays in position if unable to move
     */
    @Test
    public void unableToMoveTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(false);
        assertFalse(character.direction('W', mockBoard));
        assertEquals(1, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     * Test for facing the right direction for the character after moving
     */
    @Test
    public void facingDirectionTest() {
        when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(mockBlock);
        when(mockBlock.enter()).thenReturn(true);

        character.direction('W', mockBoard);
        assertEquals(Direction.Up, character.getFacing());

        character.direction('S', mockBoard);
        assertEquals(Direction.Down, character.getFacing());

        character.direction('A', mockBoard);
        assertEquals(Direction.Left, character.getFacing());

        character.direction('D', mockBoard);
        assertEquals(Direction.Right, character.getFacing());
    }



    /**
     * Test for setting speed
     */
    @Test
    public void speedTest() {
        assertEquals(1, character.getSpeed());
    }

    /**
     * Testing get score
     */
    @Test
    public void getScoreTest() {
        assertEquals(0,character.getScore());
        character.add_score(1);
        assertEquals(1,character.getScore());
        character.minus_score(1);
        assertEquals(0,character.getScore());
        character.setScore(10);
        assertEquals(10, character.getScore());

    }

    /**
     * Test to draw the Character facing Direction.none.
     */
    @Test
    public void testDrawNull() {
        character.setX(2);
        character.setY(1);
        character.setFacing(Direction.None);
        int tileSize = 32;
        float offset = 0.0f;

        character.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * character.getX())), eq((float)(tileSize* character.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing up with offset
     */
    @Test
    public void testDrawUp() {

        character.setX(1);
        character.setY(1);
        character.setFacing(Direction.Up);
        int tileSize = 32;
        float offset = 10.0f;

        character.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * character.getX())), eq((float)(tileSize* character.getY() + offset)), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing down with offset
     */
    @Test
    public void testDrawDown() {

        character.setX(1);
        character.setY(2);
        character.setFacing(Direction.Down);
        int tileSize = 32;
        float offset = 10.0f;

        character.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * character.getX())), eq((float)(tileSize* character.getY() + offset)), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing Right with offset
     */
    @Test
    public void testDrawRight() {

        character.setX(1);
        character.setY(1);
        character.setFacing(Direction.Right);
        int tileSize = 32;
        float offset = 10.0f;

        character.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * character.getX() + offset)), eq((float)(tileSize* character.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test drawing Left with offset
     */
    @Test
    public void testDrawLeft() {

        character.setX(2);
        character.setY(1);
        character.setFacing(Direction.Left);
        int tileSize = 32;
        float offset = 10.0f;

        character.draw(mockBatch, tileSize, offset);

        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * character.getX() + offset)), eq((float)(tileSize* character.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
    }

    /**
     * Test for drawing with no offset
     */
    @Test
    public void testDrawWithoutOffset() {
        // Setting mock position and facing direction
        character.setX(1);
        character.setY(1);
        character.setFacing(Direction.Up);  // Set facing up
        int tileSize = 32;
        float offset = 0.0f;  // Zero offset

        // Call the draw method
        character.draw(mockBatch, tileSize, offset);

        // Verify that batch.draw() was called without offset
        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize* character.getX())), eq((float)(tileSize* character.getY())), eq((float)(tileSize)), eq((float) (tileSize)));
    }

    /** Empty default constructor to allow creation of Javadocs without errors. */
    public CharacterTest() {};
}
