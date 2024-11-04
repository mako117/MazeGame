package rewards;

import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;

public class Regular_Reward extends Reward {
	private int regularNr;
	private static int regularCnt = 1;
	
	public Regular_Reward(){
		super(-1,-1,0);
		regularNr = regularCnt;
		regularCnt++;
	}
	
	public Regular_Reward(int x, int y, int s, TextureRegion inputTexture){
		super(x,y,s);
		this.setTexture(inputTexture);
		regularNr = regularCnt;
		regularCnt++;
	}
}
