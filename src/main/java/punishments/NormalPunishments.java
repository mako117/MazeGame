package punishments;

/**
 * The standard punishment in the maze game.
 */
public class NormalPunishments extends Punishments{
    private static int NpunishmentCnt = 0;
    private int NpunishmentNr;

    NormalPunishments(int inputX, int inputY, int inputScore){
        super(inputX, inputY, inputScore);
        NpunishmentNr = NpunishmentCnt++;
    }

}
