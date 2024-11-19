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



}
