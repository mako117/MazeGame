package board;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BlockTest {


    private SpriteBatch mockBatch;
    private TextureRegion mockTextureRegion;

    @BeforeEach
    public void setup(){
        mockTextureRegion = mock(TextureRegion.class);
        mockBatch = mock(SpriteBatch.class);
    }

    // Test block features
    /**
     * Test barrier block enter method.
     */
    @Test
    void barrierBlockEnterTest(){
        BarrierBlock b = new BarrierBlock();
        BarrierBlock b2 = new BarrierBlock(0, 0, mockTextureRegion);

        assertFalse(b.enter());
        assertFalse(b2.enter());
    }

    /**
     * Test room block enter method.
     */
    @Test
    void roomBlockEnterTest(){
        RoomBlock b = new RoomBlock();
        RoomBlock b2 = new RoomBlock(0, 0, mockTextureRegion);

        assertTrue(b.enter());
        assertTrue(b2.enter());
    }

    /**
     * Test wall enter method.
     */
    @Test
    void wallEnterTest(){
        Wall w = new Wall();
        Wall w2 = new Wall(0, 0, mockTextureRegion);

        assertFalse(w.enter());
        assertFalse(w2.enter());
    }

    /**
     * Test block position getter.
     */
    @Test
    void blockPositionTest(){
        Block b = new RoomBlock(0,1, mockTextureRegion);

        assertEquals(0, b.getXPosition());
        assertEquals(1, b.getYPosition());

        b.setXPosition(1);
        b.setYPosition(2);

        assertEquals(1, b.getXPosition());
        assertEquals(2, b.getYPosition());

    }

    @Test
    void drawBlockTest(){
        Block b = new RoomBlock(0,1, mockTextureRegion);
        int tileSize = 100;
        b.draw(mockBatch, tileSize);
        verify(mockBatch).draw(eq(mockTextureRegion),eq((float)(tileSize * b.getXPosition())), eq((float)(tileSize* b.getYPosition())), eq((float)(tileSize)), eq((float)(tileSize)));
    }

}
