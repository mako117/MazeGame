package punishments;

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

    public BonusPunishments(int inputX, int inputY, int inputScore, int startT, int endT){
        super(inputX, inputY, inputScore);
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