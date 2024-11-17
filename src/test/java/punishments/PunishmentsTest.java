package punishments;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

class PunishmentsTest {

	@Test
	void setNormal_Punishments() {
		NormalPunishments Np = new NormalPunishments(10,10,5,mock(TextureRegion.class));
		
		assertEquals(10,Np.XPosition());
		assertEquals(10,Np.YPosition());
		assertEquals(5,Np.getPunishmentScore());
		
		Np.setX(20);
		Np.setY(20);
		Np.setScore(10);
		
		assertEquals(20,Np.XPosition());
		assertEquals(20,Np.YPosition());
		assertEquals(10,Np.getPunishmentScore());
		
		Np.setScore(-10);
		assertEquals(10,Np.getPunishmentScore());
	}
	
	@Test
	void setBonus_Punishments() {
		BonusPunishments Bp1 = new BonusPunishments(15,15,10,mock(TextureRegion.class),12,30);
		
		assertEquals(15,Bp1.XPosition());
		assertEquals(15,Bp1.YPosition());
		assertEquals(10,Bp1.getPunishmentScore());
		assertEquals(12,Bp1.getStartTime());
		assertEquals(30,Bp1.getEndTime());
		
		Bp1.setX(25);
		Bp1.setY(25);
		Bp1.setScore(20);
		Bp1.settime(20, 50);;
		
		assertEquals(25,Bp1.XPosition());
		assertEquals(25,Bp1.YPosition());
		assertEquals(20,Bp1.getPunishmentScore());
		assertEquals(20,Bp1.getStartTime());
		assertEquals(50,Bp1.getEndTime());
		
		Bp1.setScore(-10);
		assertEquals(20,Bp1.getPunishmentScore());
		
		BonusPunishments Bp2 = new BonusPunishments();
		
		assertEquals(-1,Bp2.XPosition());
		assertEquals(-1,Bp2.YPosition());
		assertEquals(0,Bp2.getPunishmentScore());
		assertEquals(-1,Bp2.getStartTime());
		assertEquals(-1,Bp2.getEndTime());
	}

}
