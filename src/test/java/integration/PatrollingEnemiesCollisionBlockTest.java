package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Board;
import board.RoomBlock;
import board.Wall;
import board.BarrierBlock;
import entities.enemy.PatrollingEnemies;
import directions.Direction;

/**
 * Integration tests for interactions between the PatrollingEnemies and Block classes.
 */
public class PatrollingEnemiesCollisionBlockTest {
	private TextureRegion tex = mock(TextureRegion.class);
	private Board mockboard = mock(Board.class);
	
	//Wall
	/**
	 * Test if patrolling enemies can move up into a wall.
	 */
	@Test
	void moveupwithwall() {
		Wall w = new Wall(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Up,2,2,2,2,tex);
		
		e.direction('W', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(2,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move left into a wall.
	 */
	@Test
	void moveleftwithwall() {
		Wall w = new Wall(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Left,2,2,2,2,tex);
		
		e.direction('A', mockboard);
		
		assertEquals(0,e.getX());
		assertEquals(1,e.getY());
	}

	/**
	 * Test if patrolling enemies can move right into a wall.
	 */
	@Test
	void moverightwithwall() {
		Wall w = new Wall(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Right,2,2,2,2,tex);
		
		e.direction('D', mockboard);
		
		assertEquals(2,e.getX());
		assertEquals(1,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move down into a wall.
	 */
	@Test
	void movedownwithwall() {
		Wall w = new Wall(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Down,2,2,2,2,tex);
		
		e.direction('S', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(0,e.getY());
	}
	
	//RoomBlock
	/**
	 * Test if patrolling enemies can move up into a RoomBlock.
	 */
	@Test
	void moveupwithroomblock() {
		RoomBlock b = new RoomBlock(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Up,2,2,2,2,tex);
		
		e.direction('W', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(2,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move left into a RoomBlock.
	 */
	@Test
	void moveleftwithroomblock() {
		RoomBlock b = new RoomBlock(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Left,2,2,2,2,tex);
		
		e.direction('A', mockboard);
		
		assertEquals(0,e.getX());
		assertEquals(1,e.getY());
	}

	/**
	 * Test if patrolling enemies can move right into a RoomBlock.
	 */
	@Test
	void moverightwithroomblock() {
		RoomBlock b = new RoomBlock(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Right,2,2,2,2,tex);
		
		e.direction('D', mockboard);
		
		assertEquals(2,e.getX());
		assertEquals(1,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move down into a RoomBlock.
	 */
	@Test
	void movedownwithroomblock() {
		RoomBlock b = new RoomBlock(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Down,2,2,2,2,tex);
		
		e.direction('S', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(0,e.getY());
	}

	//BarrierBlock
	/**
	 * Test if patrolling enemies can move up into a BarrierBlock.
	 */
	@Test
	void moveupwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Up,2,2,2,2,tex);
		
		e.direction('W', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(2,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move left into a BarrierBlock.
	 */
	@Test
	void moveleftwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Left,2,2,2,2,tex);
		
		e.direction('A', mockboard);
		
		assertEquals(0,e.getX());
		assertEquals(1,e.getY());
	}

	/**
	 * Test if patrolling enemies can move right into a BarrierBlock.
	 */
	@Test
	void moverightwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Right,2,2,2,2,tex);
		
		e.direction('D', mockboard);
		
		assertEquals(2,e.getX());
		assertEquals(1,e.getY());
	}
	
	/**
	 * Test if patrolling enemies can move down into a BarrierBlock.
	 */
	@Test
	void movedownwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		PatrollingEnemies e = new PatrollingEnemies(1,1,Direction.Down,2,2,2,2,tex);
		
		e.direction('S', mockboard);
		
		assertEquals(1,e.getX());
		assertEquals(0,e.getY());
	}

	/** Empty default constructor to allow creation of Javadocs without errors. */
    public PatrollingEnemiesCollisionBlockTest() {};
}
