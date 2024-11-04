package rewards;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Regular_Reward extends Reward {
	
	public Regular_Reward(){
		super(-1,-1,0);
	}
	
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s);
		this.setTexture(inputTexture);
	}
}
