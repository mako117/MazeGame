import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import entities.Character;
import board.Board;
import board.Block;

public class CharacterTest {

    private Character character;
    private Board board;
    private Block block;

    /**
     *
     */
    @BeforeEach
    public void setUp() {

        TextureRegion mockTextureRegion = mock(TextureRegion.class);

        board = mock(Board.class);
        block = mock(Block.class);

        character = new Character(mockTextureRegion, 1, 1);
    }

    /**
     *
     */
    @Test
    public void initialPositionTest() {
        assertEquals(1, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     *
     */
    @Test
    public void moveUpTest() {
        when(board.getBlock(anyInt(), anyInt())).thenReturn(block);
        when(block.enter()).thenReturn(true);
        character.direction('W', board);
        assertEquals(1, character.getX());
        assertEquals(2, character.getY());
    }

    /**
     *
     */
    @Test
    public void moveDownTest() {
        when(board.getBlock(anyInt(), anyInt())).thenReturn(block);
        when(block.enter()).thenReturn(true);
        character.direction('S', board);
        assertEquals(1, character.getX());
        assertEquals(0, character.getY());
    }

    /**
     *
     */
    @Test
    public void moveLeftTest() {
        when(board.getBlock(anyInt(), anyInt())).thenReturn(block);
        when(block.enter()).thenReturn(true);
        character.direction('A', board);
        assertEquals(0, character.getX());
        assertEquals(1, character.getY());
    }

    /**
     *
     */
    @Test
    public void moveRightTest() {
        when(board.getBlock(anyInt(), anyInt())).thenReturn(block);
        when(block.enter()).thenReturn(true);
        character.direction('D', board);
        assertEquals(2, character.getX());
        assertEquals(1, character.getY());
    }
}
