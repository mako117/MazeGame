package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Wall;
import board.RoomBlock;
import board.BarrierBlock;
import entities.Character;
import board.Board;

public class CharacterCollisionBlockTest {
	private TextureRegion tex = mock(TextureRegion.class);
	private Board mockboard = mock(Board.class);
	
	//Wall
	/**
	 * Test when character move up have a wall
	 */
	@Test
	void moveupwithwall() {
		Wall w = new Wall(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('W', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
	
	/**
	 * Test when character move left have a wall
	 */
	@Test
	void moveleftwithwall() {
		Wall w = new Wall(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('A', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}

	/**
	 * Test when character move right have a wall
	 */
	@Test
	void moverightwithwall() {
		Wall w = new Wall(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('D', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
	
	/**
	 * Test when character move down have a wall
	 */
	@Test
	void movedownwithwall() {
		Wall w = new Wall(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('S', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
	
	//RoomBlock
	/**
	 * Test when character move up have a roomblock
	 */
	@Test
	void moveupwithroomblock() {
		RoomBlock b = new RoomBlock(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		Character c = new Character(tex);
		
		c.direction('W', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(2,c.getY());
	}
	
	/**
	 * Test when character move left have a roomblock
	 */
	@Test
	void moveleftwithroomblock() {
		RoomBlock b = new RoomBlock(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		Character c = new Character(tex);
		
		c.direction('A', mockboard);
		
		assertEquals(0,c.getX());
		assertEquals(1,c.getY());
	}

	/**
	 * Test when character move right have a roomblock
	 */
	@Test
	void moverightwithroomblock() {
		RoomBlock b = new RoomBlock(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		Character c = new Character(tex);
		
		c.direction('D', mockboard);
		
		assertEquals(2,c.getX());
		assertEquals(1,c.getY());
	}
	
	/**
	 * Test when character move down have a roomblock
	 */
	@Test
	void movedownwithroomblock() {
		RoomBlock b = new RoomBlock(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		Character c = new Character(tex);
		
		c.direction('S', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(0,c.getY());
	}
	
	//BarrierBlock
	/**
	 * Test when character move up have a BarrierBlock
	 */
	@Test
	void moveupwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(1,2,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('W', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
	
	/**
	 * Test when character move left have a BarrierBlock
	 */
	@Test
	void moveleftwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(0,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('A', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}

	/**
	 * Test when character move right have a BarrierBlock
	 */
	@Test
	void moverightwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(2,1,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('D', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
	
	/**
	 * Test when character move down have a BarrierBlock
	 */
	@Test
	void movedownwithBarrierBlock() {
		BarrierBlock w = new BarrierBlock(1,0,tex);
		when(mockboard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		Character c = new Character(tex);
		
		c.direction('S', mockboard);
		
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}
}


