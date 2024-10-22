public class Door extends Mapsite {
    private RoomBlock r1;
    private RoomBlock r2;
    private int doorNr;
    private static int doorCnt;

    public Door(RoomBlock input1, RoomBlock input2) {
        this.r1 = input1;
        this.r2 = input2;
    }
}
