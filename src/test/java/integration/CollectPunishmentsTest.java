package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import board.Board;
import entities.Character;
import screens.GameLogic;

public class CollectPunishmentsTest {
	private Board mockboard = mock(Board.class);
	private TextureRegion tex = mock(TextureRegion.class);
	private GameLogic logic = new GameLogic();
	
	/**
	 * Test character collect regular punishment how score change
	 */
	@Test
	void CharacterCollectRegularPunishment() {
		when(mockboard.regPunishmentCollect(anyInt(), anyInt())).thenReturn(5);
		when(mockboard.bonPunishmentCollect(anyInt(), anyInt(), anyFloat())).thenReturn(0);
		
		Character c = new Character(tex);
		
		logic.checkPunishment(c, mockboard, 0);
		assertEquals(-5, c.getScore());
	}
	
	/**
	 * Test character collect bonus punishment how score change
	 */
	@Test
	void CharacterCollectBonusPunishment() {
		when(mockboard.regPunishmentCollect(anyInt(), anyInt())).thenReturn(0);
		when(mockboard.bonPunishmentCollect(anyInt(), anyInt(), anyFloat())).thenReturn(10);
		
		Character c = new Character(tex);
		
		logic.checkPunishment(c, mockboard, 0);
		assertEquals(-10, c.getScore());
	}
	
	/**
	 * Test character collect two type punishment how score change
	 */
	@Test
	void CharacterCollectBothPunishments() {
		when(mockboard.regPunishmentCollect(anyInt(), anyInt())).thenReturn(20);
		when(mockboard.bonPunishmentCollect(anyInt(), anyInt(), anyFloat())).thenReturn(10);
		
		Character c = new Character(tex);
		
		logic.checkPunishment(c, mockboard, 0);
		assertEquals(-30, c.getScore());
	}

	/** Empty default constructor to allow creation of Javadocs without errors. */
    public CollectPunishmentsTest() {};
}
