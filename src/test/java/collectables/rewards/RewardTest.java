package collectables.rewards;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Unit test for the collectables.rewards package.
 */
class RewardTest {
	private TextureRegion tex;
	private SpriteBatch bat;

	/** Empty default constructor to allow creation of Javadocs without errors. */
    public RewardTest() {};

	/**
	 * Setup done before every test.
	 */
	@BeforeEach
	void setup() {
		tex = mock(TextureRegion.class);
		bat = mock(SpriteBatch.class);
	}
	
	/**
	 * Test creation and changing of the regular reward.
	 */
	@Test
	void setRegular_Reward() {
		Regular_Reward Rr1 = new Regular_Reward(10,10,5,tex);
		
		assertEquals(10,Rr1.getX());
		assertEquals(10,Rr1.getY());
		assertEquals(5,Rr1.getCollectableScore());
		
		Rr1.setX(20); Rr1.setY(20);
		Rr1.setScore(10);
		
		assertEquals(20,Rr1.getX());
		assertEquals(20,Rr1.getY());
		assertEquals(10,Rr1.getCollectableScore());
		
		Rr1.setScore(-10);
		assertEquals(10,Rr1.getCollectableScore());
		
		Regular_Reward Rr2 = new Regular_Reward();
		
		assertEquals(-1,Rr2.getX());
		assertEquals(-1,Rr2.getY());
		assertEquals(0,Rr2.getCollectableScore());
	}
	
	/**
	 * Test creation and changing of bonus reward.
	 */
	@Test
	void setBonus_Reward() {
		Bonus_Reward Br1 = new Bonus_Reward(12,12,6,tex,15,30);
		
		assertEquals(12,Br1.getX());
		assertEquals(12,Br1.getY());
		assertEquals(6,Br1.getCollectableScore());
		assertEquals(15,Br1.getStartTime());
		assertEquals(30,Br1.getEndTime());
		
		Br1.setX(30); Br1.setY(30);
		Br1.setScore(15);
		Br1.setTime(20, 60);;
		
		assertEquals(30,Br1.getX());
		assertEquals(30,Br1.getY());
		assertEquals(15,Br1.getCollectableScore());
		assertEquals(20,Br1.getStartTime());
		assertEquals(60,Br1.getEndTime());
		
		Br1.setScore(-15);
		assertEquals(15,Br1.getCollectableScore());
		
		Bonus_Reward Br2 = new Bonus_Reward();
		
		assertEquals(-1,Br2.getX());
		assertEquals(-1,Br2.getY());
		assertEquals(0,Br2.getCollectableScore());
		assertEquals(-1,Br2.getStartTime());
		assertEquals(-1,Br2.getEndTime());
	}

	/**
	 * Test reward's draw method.
	 */
	@Test
	void drawReward() {
		TextureRegion tex1 = mock(TextureRegion.class);
		Reward r1 = new Regular_Reward(0, 1, 10, tex1);
        int tileSize = 100;
        r1.draw(bat, tileSize);
        verify(bat).draw(eq(tex1),eq((float)(tileSize * r1.getX())), eq((float)(tileSize* r1.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
        
        TextureRegion tex2 = mock(TextureRegion.class);
        Reward r2 = new Bonus_Reward(0, 1, 10, tex2, 5, 10);
        r2.draw(bat, tileSize);
        verify(bat).draw(eq(tex2),eq((float)(tileSize * r2.getX())), eq((float)(tileSize* r2.getY())), eq((float)(tileSize)), eq((float)(tileSize)));
	}
	
}
