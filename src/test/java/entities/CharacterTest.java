package entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import entities.Character;
import board.Board;
import board.Block;

public class CharacterTest {

    private Character character;
    private Board mockBoard;
    private Block mockBlock;

    /**
     * The setup to create the initial character and some mock classes
     */
    @BeforeEach
    public void setUp() {

        mockBoard = mock(Board.class);
        mockBlock = mock(Block.class);

        character = new Character(mock(TextureRegion.class), 1, 1);
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
     * Test for reward collection count;
     */
    @Test
    public void rewardCollectionTest() {
        int initialCnt = character.getRewardsCollected();
        character.addRegReward();
        assertEquals(initialCnt + 1, character.getRewardsCollected());
    }

}
