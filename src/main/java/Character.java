public class Character {
    private int x;
    private int y;
    private int score;
    private int speed;
    private int rewardsCollect;

    public Character() {

    }
    public void direction(char input) {

    }
    public void scorechange(int change) {

    }

    private Block getCurrentSite(Block [][] array) {
        Block currentSite = array[x][y];
        return currentSite;
    }
}
