package punishments;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/**
 * A punishment is a object on the board which decreases the score of any Character that enters its space.
 */
public class Punishments {
    private int x;
    private int y;
    private int score;
    private TextureRegion PunishmentsTexture;

    /**
     * Initializes position to an invalid value, and the punishment score to 0.
     */
    public Punishments(){
        x = -1;
        y = -1;
        score = 0;
    }
    /**
     * Initializes the punishment's position, the amount it decreases the character's score by, and the texture that represents it on the board.
     * @param inputX    Input for the punishment's x-coordinate.
     * @param inputY    Input for the punishment's y-coordinate.
     * @param inputScore  Input for the punishment's score.  
     * @param textureRegion Input for the punishment's texture.
     */
    public Punishments(int inputX, int inputY, int inputScore, TextureRegion textureRegion) {
        this.setX(inputX);
        this.setY(inputY);
        this.setScore(inputScore);
        this.setTextureRegion(textureRegion);
    }

    /**
     * Gets the punishment's <score>.
     * @return  <score>.
     */
    public int getPunishmentScore() {
        return this.score;
    }
    /**
     * Gets the punishment's <x>-coordinate.
     * @return  <x>.
     */
    public int XPosition() {
        return x;
    }
    /**
     * Gets the punishment's <y>-coordinate.
     * @return  <y>.
     */
    public int YPosition() {
        return y;
    }

    /**
     * Draw the punishment onto the screen.
     * @param batch The sprite batch.
     * @param tilesize  The size of the tile.
     */
    public void draw(Batch batch, int tilesize) {
        batch.draw(new TextureRegion(PunishmentsTexture), tilesize*this.XPosition(), tilesize*this.YPosition(), tilesize, tilesize);
    }

    /**
     * Sets the punishment's <x>-coordinate.
     * @param inputX    Input for <x>.
     */
    protected void setX(int inputX) {
        this.x = inputX;
    }
    /**
     * Sets the punishment's <y>-coordinate.
     * @param inputY    Input for <y>.
     */
    protected void setY(int inputY) {
        this.y = inputY;
    }
    /**
     * Sets the punishment's <score>.
     * @param inputScore    Input for <score>.
     */
    protected void setScore(int inputScore) {
        if(inputScore >= 0) {
            this.score = inputScore;
        }
    }
    /**
     * Sets the punishment's <PunishmentsTexture>.
     * @param textureRegion    Input for <PunishmentsTexture>.
     */
    protected void setTextureRegion(TextureRegion textureRegion) {
        this.PunishmentsTexture = textureRegion;
    }
}
