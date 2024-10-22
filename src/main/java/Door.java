public class Door extends Mapsite {
    private static int INITIAL_COUNT = 0;
    private RoomBlock r1;
    private RoomBlock r2;
    private int doorNr;
    private static int doorCnt = INITIAL_COUNT;

    public Door(RoomBlock input1, RoomBlock input2) /* throws Exception */ {
        /* if(input1 == input2) {
            throw new Exception("r1 cannot == r2!\n");
        } else { */
            this.r1 = input1;
            this.r2 = input2;
        // }
        setDoorNr(doorCnt);
        doorCnt++;
    }
    public void enter() {
        // 
    }

    private void setDoorNr(int number) throws Exception {
        if(number >= 0) {
            this.doorNr = number;
        } else {
            throw new Exception("Door number must be 0 or greater!\n");
        }
    }

}
