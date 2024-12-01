package punishments;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class PunishmentsTest {
	private SpriteBatch bat = mock(SpriteBatch.class);
	private TextureRegion tex = mock(TextureRegion.class);

	/**
	 * Test create normal punishments and change it set
	 */
	@Test
	void setNormal_Punishments() {
		NormalPunishments Np = new NormalPunishments(10,10,5,tex);
		
		assertEquals(10,Np.getX());
		assertEquals(10,Np.getY());
		assertEquals(5,Np.getPunishmentScore());
		
		Np.setX(20);
		Np.setY(20);
		Np.setScore(10);
		
		assertEquals(20,Np.getX());
		assertEquals(20,Np.getY());
		assertEquals(10,Np.getPunishmentScore());
		
		Np.setScore(-10);
		assertEquals(10,Np.getPunishmentScore());
	}
	
	/**
	 * Test create bonus punishments and change it set
	 */
	@Test
	void setBonus_Punishments() {
		BonusPunishments Bp1 = new BonusPunishments(15,15,10,tex,12,30);
		
		assertEquals(15,Bp1.getX());
		assertEquals(15,Bp1.getY());
		assertEquals(10,Bp1.getPunishmentScore());
		assertEquals(12,Bp1.getStartTime());
		assertEquals(30,Bp1.getEndTime());
		
		Bp1.setX(25);
		Bp1.setY(25);
		Bp1.setScore(20);
		Bp1.setTime(20, 50);;
		
		assertEquals(25,Bp1.getX());
		assertEquals(25,Bp1.getY());
		assertEquals(20,Bp1.getPunishmentScore());
		assertEquals(20,Bp1.getStartTime());
		assertEquals(50,Bp1.getEndTime());
		
		Bp1.setScore(-10);
		assertEquals(20,Bp1.getPunishmentScore());
		
		BonusPunishments Bp2 = new BonusPunishments();
		
		assertEquals(-1,Bp2.getX());
		assertEquals(-1,Bp2.getY());
		assertEquals(0,Bp2.getPunishmentScore());
		assertEquals(-1,Bp2.getStartTime());
		assertEquals(-1,Bp2.getEndTime());
	}

	/**
	 * Test the draw method in punishments can come out correct information
	 */
	@Test
	void drawPunishments() {
		TextureRegion tex1 = mock(TextureRegion.class);
		Punishments p1 = new NormalPunishments(0, 1, 10, tex1);
        int tileSize = 100;
        p1.draw(bat, tileSize);
        verify(bat).draw(eq(tex1),eq((float)(tileSize * p1.getX())), eq((float)(tileSize* p1.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
        
        TextureRegion tex2 = mock(TextureRegion.class);
        Punishments p2 = new BonusPunishments(0, 1, 10, tex2, 5, 10);
        p2.draw(bat, tileSize);
        verify(bat).draw(eq(tex2),eq((float)(tileSize * p2.getX())), eq((float)(tileSize* p2.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
	}
}
