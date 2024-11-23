package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Board;
import board.RoomBlock;
import board.Wall;
import directions.Direction;
import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import screens.GameLogic;
import entities.Character;
import board.BarrierBlock;
import board.Block;

public class EnemiesMoveTest {
	
	private GameLogic logic = new GameLogic();
	private Board mockBoard = mock(Board.class);
	private TextureRegion tex = mock(TextureRegion.class);
	private int tile_size = 0;
	
	/**
	 * Test will two type enemies will move or not when have wall or roomblock
	 */
	@Test
	void EnemiesDirectionTest() {
		ArrayList<Enemies> e = new ArrayList<Enemies>();
		ArrayList<Boolean> Emove = new ArrayList<Boolean>();
		
		Character c = mock(Character.class);
		
		when(c.getX()).thenReturn(5);
		when(c.getY()).thenReturn(5);
		
		Moving_Enemies me1 = new Moving_Enemies(5, 7, tex);
		e.add(me1);
		Emove.add(false);
		
		Moving_Enemies me2 = new Moving_Enemies(5, 3, tex);
		e.add(me2);
		Emove.add(false);
		
		Moving_Enemies me3 = new Moving_Enemies(3, 5, tex);
		e.add(me3);
		Emove.add(false);
		
		Moving_Enemies me4 = new Moving_Enemies(7, 5, tex);
		e.add(me4);
		Emove.add(false);
		
		PatrollingEnemies me5 = new PatrollingEnemies(5, 8, Direction.Down, 2, 2, 2, 2, tex);
		e.add(me5);
		Emove.add(false);
		
		PatrollingEnemies me6 = new PatrollingEnemies(5, 2, Direction.Up, 2, 2, 2, 2, tex);
		e.add(me6);
		Emove.add(false);
		
		PatrollingEnemies me7 = new PatrollingEnemies(2, 5, Direction.Right, 2, 2, 2, 2, tex);
		e.add(me7);
		Emove.add(false);
		
		PatrollingEnemies me8 = new PatrollingEnemies(8, 5, Direction.Left, 2, 2, 2, 2, tex);
		e.add(me8);
		Emove.add(false);
		
		RoomBlock r = new RoomBlock(0,0,tex);
		when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(r);
		
		logic.moveEnemies(e, c, mockBoard, Emove, tile_size);
		
		assertTrue(Emove.get(0));
		assertTrue(Emove.get(1));
		assertTrue(Emove.get(2));
		assertTrue(Emove.get(3));
		assertTrue(Emove.get(4));
		assertTrue(Emove.get(5));
		assertTrue(Emove.get(6));
		assertTrue(Emove.get(7));
		
		Wall w = new Wall(0,0,tex);
		when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(w);
		
		logic.moveEnemies(e, c, mockBoard, Emove, tile_size);
		
		assertFalse(Emove.get(0));
		assertFalse(Emove.get(1));
		assertFalse(Emove.get(2));
		assertFalse(Emove.get(3));
		assertTrue(Emove.get(4));
		assertTrue(Emove.get(5));
		assertTrue(Emove.get(6));
		assertTrue(Emove.get(7));
		
		BarrierBlock b = new BarrierBlock(0,0,tex);
		when(mockBoard.getBlock(anyInt(), anyInt())).thenReturn(b);
		
		logic.moveEnemies(e, c, mockBoard, Emove, tile_size);
		
		assertFalse(Emove.get(0));
		assertFalse(Emove.get(1));
		assertFalse(Emove.get(2));
		assertFalse(Emove.get(3));
		assertTrue(Emove.get(4));
		assertTrue(Emove.get(5));
		assertTrue(Emove.get(6));
		assertTrue(Emove.get(7));
		
		
	}
}
