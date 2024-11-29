package entities.enemy;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;
import entities.Entity;

/**
 * An enemy in the maze game. <br>
 * This enemy can move throughout the maze.<br>
 * Each enemy has a unique identifier and texture associated with it.
 */
 public class Enemies extends Entity {
    private int enemyNr;
    private static int enemyCnt = 1;


    /**
     * Create a new enemy at the given initial location. <br>
     * @param initial_x The x position.
     * @param initial_y The y position.
     * @param texture The texture.
     */
    public Enemies(int initial_x, int initial_y, TextureRegion texture) {
        super(texture, initial_x, initial_y, Direction.Down);
        this.enemyNr = enemyCnt++;
    }

    /**
     * Draw the enemy on the screen.
     * @param batch The spritebatch.
     * @param tileSize The tile size.
     * @param offset The image offset.
     */
    public void draw(Batch batch, int tileSize, float offset) {
        switch (this.getFacing()) {
            case Direction.Up:
                batch.draw(this.getTexture(), tileSize * (this.getX()), tileSize * (this.getY()) - offset, tileSize, tileSize);
                break;
            case Direction.Down:
                batch.draw(this.getTexture(), tileSize * (this.getX()), tileSize * (this.getY()) + offset, tileSize, tileSize);
                break;
            case Direction.Left:
                batch.draw(this.getTexture(), tileSize * (this.getX()) + offset, tileSize * (this.getY()), tileSize, tileSize);
                break;
            case Direction.Right:
                batch.draw(this.getTexture(), tileSize * (this.getX()) - offset, tileSize * (this.getY()), tileSize, tileSize);
                break;
            default:
                batch.draw(this.getTexture(), tileSize * (this.getX()), tileSize * (this.getY()), tileSize, tileSize);
            	break;
        }
    }
}