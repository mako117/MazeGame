/**
 * The bonus punishment in the maze game.
 */
public class BonusPunishments extends Punishments{
    private int bonusNr;
    private static int bonusCnt = 1;
    private int starttime;
    private int endtime;

    BonusPunishments(){
        super();
        bonusNr = bonusCnt++;
        starttime = -1;
        endtime = -1;
    }

    BonusPunishments(int inputX, int inputY, int inputScore, int startT, int endT){
        super(inputX, inputY, inputScore);
        this.starttime = startT;
        this.endtime = endT;
    }

}