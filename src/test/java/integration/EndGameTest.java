package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Block;
import board.Board;
import screens.GameLogic;
import entities.Character;

public class EndGameTest {
	private GameLogic logic = new GameLogic();
	private TextureRegion tex = mock(TextureRegion.class);
	private Board mockboard = mock(Board.class);
	private Block mockblock = mock(Block.class);
	
	/**
	 * Test the score change make game end
	 */
	@Test
	void EndGameScoreTest() {
		Character c = new Character(tex);
		
		assertFalse(logic.checkScore(c));
		
		c.add_score(5);
		assertFalse(logic.checkScore(c));
		
		c.minus_score(10);
		assertTrue(logic.checkScore(c));
	}
	
	/**
	 * Test the condition the fit for end game
	 */
	@Test
	void EndGameConditionTest() {
		Character c = new Character(tex);
		
		when(mockboard.getEnd()).thenReturn(mockblock);

		when(mockboard.getTotalRegRewardCnt()).thenReturn(10);
		when(mockboard.getEnd().getXPosition()).thenReturn(1);
		when(mockboard.getEnd().getYPosition()).thenReturn(1);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(2);
		when(mockboard.getEnd().getYPosition()).thenReturn(2);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(1);
		when(mockboard.getEnd().getYPosition()).thenReturn(2);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(2);
		when(mockboard.getEnd().getYPosition()).thenReturn(1);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getTotalRegRewardCnt()).thenReturn(0);
		when(mockboard.getEnd().getXPosition()).thenReturn(1);
		when(mockboard.getEnd().getYPosition()).thenReturn(1);
		
		assertTrue(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(2);
		when(mockboard.getEnd().getYPosition()).thenReturn(2);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(1);
		when(mockboard.getEnd().getYPosition()).thenReturn(2);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
		
		when(mockboard.getEnd().getXPosition()).thenReturn(2);
		when(mockboard.getEnd().getYPosition()).thenReturn(1);
		
		assertFalse(logic.checkIfExitingMaze(c, mockboard));
	}

	/** Empty default constructor to allow creation of Javadocs without errors. */
    public EndGameTest() {};

}
