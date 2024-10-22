public class Punishments {
    private int x;
    private int y;
    private int score;

    public Punishments(int inputX, int inputY, int inputScore) {
        this.setX(inputX);
        this.setY(inputY);
        this.setScore(inputScore);
    }
    public int getPunishmentScore() {
        return this.score;
    }

    private void setX(int inputX) {
        this.x = inputX;
    }
    private void setY(int inputY) {
        this.y = inputY;
    }
    private void setScore(int inputScore) {
        if(inputScore < 0) {
            this.score = inputScore;
        }
    }
}
