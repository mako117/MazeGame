public class Punishments {
    private int x;
    private int y;
    private int score;

    public Punishments(){
        x = -1;
        y = -1;
        score = 0;
    }

    public Punishments(int inputX, int inputY, int inputScore) {
        this.setX(inputX);
        this.setY(inputY);
        this.setScore(inputScore);
    }
    public int getPunishmentScore() {
        return this.score;
    }

    protected void setX(int inputX) {
        this.x = inputX;
    }
    protected void setY(int inputY) {
        this.y = inputY;
    }
    protected void setScore(int inputScore) {
        if(inputScore < 0) {
            this.score = inputScore;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScore() {
        return score;
    }
}
