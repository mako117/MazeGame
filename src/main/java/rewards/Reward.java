package rewards;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Reward {
	private int x;
	private int y;
	private int score;
	private TextureRegion RewardTexture;
	
	public Reward(int x, int y, int s){
		this.x = x;
		this.y = y;
		this.score = s;
	}
	public void Set_score(int s) {
		this.score = s;
	}
	
	public void Set_position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int Xposition() {
		return x;
	}
	
	public int Yposition() {
		return y;
	}
	
	public int getPoint() {
		return score;
	}

	protected void setTexture(TextureRegion inputTexture) {
		this.RewardTexture = inputTexture;
	}
	
	public void draw(Batch batch, int tilesize) {
        batch.draw(new TextureRegion(RewardTexture), tilesize*this.Xposition(),  tilesize*this.Yposition(), tilesize, tilesize);
    }
}
