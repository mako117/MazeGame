package punishments;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The bonus punishment in the maze game.
 */
public class BonusPunishments extends Punishments{
    private int starttime;
    private int endtime;

    public BonusPunishments(){
        super();
        starttime = -1;
        endtime = -1;
    }

    public BonusPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture, int startT, int endT){
        super(inputX, inputY, inputScore, inputTexture);
        this.starttime = startT;
        this.endtime = endT;
    }

    public int getStartTime() {
        return starttime;
    }

    public int  getEndTime(){
        return endtime;
    }
}