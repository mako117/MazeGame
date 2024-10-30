package board;

public class Door extends Mapsite {
    private static int INITIAL_COUNT = 0;
    private Block r1;
    private Block r2;
    private int doorNr;
    private static int doorCnt = INITIAL_COUNT;

    public Door(Block input1, Block input2) /* throws Exception */ {
        /* if(input1 == input2) {
            throw new Exception("r1 cannot == r2!\n");
        } else { */
            this.r1 = input1;
            this.r2 = input2;
        // }
        try {
            setDoorNr(doorCnt);
        } catch (Exception e) {
            // Q: what should we do here?
        }
        doorCnt++;
    }
    public boolean enter() {
        return true; 
    }

    private void setDoorNr(int number) throws Exception {
        if(number >= 0) {
            this.doorNr = number;
        } else {
            throw new Exception("board.Door number must be 0 or greater!\n");
        }
    }

}
