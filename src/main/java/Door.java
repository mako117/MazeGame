public class Door extends Mapsite {
    private RoomBlock r1;
    private RoomBlock r2;
    private int doorNr;
    private static int doorCnt;

    public Door(RoomBlock input1, RoomBlock input2) throws Exception {
        if(input1 == input2) {
            throw new Exception("r1 cannot == r2!\n");
        } else {
            this.r1 = input1;
            this.r2 = input2;
        }
    }
}
