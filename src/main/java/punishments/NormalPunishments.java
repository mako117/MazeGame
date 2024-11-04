package punishments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * The standard punishment in the maze game.
 */
public class NormalPunishments extends Punishments{
    private static int NpunishmentCnt = 0;
    private int NpunishmentNr;

    public NormalPunishments(int inputX, int inputY, int inputScore, TextureRegion inputTexture){
        super(inputX, inputY, inputScore, inputTexture);
        NpunishmentNr = NpunishmentCnt++;
    }

}
