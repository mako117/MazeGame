package rewards;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class RewardTest {
	private TextureRegion tex;
	private SpriteBatch bat;
	
	@BeforeEach
	void setup() {
		tex = mock(TextureRegion.class);
		bat = mock(SpriteBatch.class);
	}
	
	/**
	 * Test create the regular reward and change it set 
	 */
	@Test
	void setRegular_Reward() {
		Regular_Reward Rr1 = new Regular_Reward(10,10,5,tex);
		
		assertEquals(10,Rr1.Xposition());
		assertEquals(10,Rr1.Yposition());
		assertEquals(5,Rr1.getPoint());
		
		Rr1.Set_position(20, 20);
		Rr1.Set_score(10);
		
		assertEquals(20,Rr1.Xposition());
		assertEquals(20,Rr1.Yposition());
		assertEquals(10,Rr1.getPoint());
		
		Rr1.Set_score(-10);
		assertEquals(10,Rr1.getPoint());
		
		Regular_Reward Rr2 = new Regular_Reward();
		
		assertEquals(-1,Rr2.Xposition());
		assertEquals(-1,Rr2.Yposition());
		assertEquals(0,Rr2.getPoint());
	}
	
	/**
	 * Test create the bonus reward and change it set
	 */
	@Test
	void setBonus_Reward() {
		Bonus_Reward Br1 = new Bonus_Reward(12,12,6,tex,15,30);
		
		assertEquals(12,Br1.Xposition());
		assertEquals(12,Br1.Yposition());
		assertEquals(6,Br1.getPoint());
		assertEquals(15,Br1.getStarttime());
		assertEquals(30,Br1.getEndtime());
		
		Br1.Set_position(30, 30);
		Br1.Set_score(15);
		Br1.Settime(20, 60);;
		
		assertEquals(30,Br1.Xposition());
		assertEquals(30,Br1.Yposition());
		assertEquals(15,Br1.getPoint());
		assertEquals(20,Br1.getStarttime());
		assertEquals(60,Br1.getEndtime());
		
		Br1.Set_score(-15);
		assertEquals(15,Br1.getPoint());
		
		Bonus_Reward Br2 = new Bonus_Reward();
		
		assertEquals(-1,Br2.Xposition());
		assertEquals(-1,Br2.Yposition());
		assertEquals(0,Br2.getPoint());
		assertEquals(-1,Br2.getStarttime());
		assertEquals(-1,Br2.getEndtime());
	}

	/**
	 * Test the draw method in reward can come out correct information
	 */
	@Test
	void drawReward() {
		TextureRegion tex1 = mock(TextureRegion.class);
		Reward r1 = new Regular_Reward(0, 1, 10, tex1);
        int tileSize = 100;
        r1.draw(bat, tileSize);
        verify(bat).draw(eq(tex1),eq((float)(tileSize * r1.Xposition())), eq((float)(tileSize* r1.Yposition())), eq((float)(tileSize)), eq((float)(tileSize)));
        
        TextureRegion tex2 = mock(TextureRegion.class);
        Reward r2 = new Bonus_Reward(0, 1, 10, tex2, 5, 10);
        r2.draw(bat, tileSize);
        verify(bat).draw(eq(tex2),eq((float)(tileSize * r2.Xposition())), eq((float)(tileSize* r2.Yposition())), eq((float)(tileSize)), eq((float)(tileSize)));
	}
	
}
