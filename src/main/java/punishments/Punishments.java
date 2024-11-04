package punishments;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Punishments {
    private int x;
    private int y;
    private int score;
    private TextureRegion PunishmentsTexture;

    public Punishments(){
        x = -1;
        y = -1;
        score = 0;
    }

    public Punishments(int inputX, int inputY, int inputScore, TextureRegion textureRegion) {
        this.setX(inputX);
        this.setY(inputY);
        this.setScore(inputScore);
        this.setTextureRegion(textureRegion);
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
    protected void setTextureRegion(TextureRegion textureRegion) {
        this.PunishmentsTexture = textureRegion;
    }

    public int XPosition() {
        return x;
    }

    public int YPosition() {
        return y;
    }

    public int getPoint() {
        return score;
    }
    public void draw(Batch batch, int tilesize) {
        batch.draw(new TextureRegion(PunishmentsTexture), tilesize*this.XPosition(), tilesize*this.YPosition(), tilesize, tilesize);
    }
}
