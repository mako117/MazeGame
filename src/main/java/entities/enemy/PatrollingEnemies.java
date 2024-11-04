package entities.enemy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import directions.Direction;
import board.*;

/**
 * A patrolling enemy moves in a set x or y direction.
 */
public class PatrollingEnemies extends Enemies {
    private char moveTo;
    int xMax;
    int yMax;
    int xMin;
    int yMin;

    /**
     * Create a new patrolling enemy at the given coordinates. <br>
     * The enemy will move within the given area.
     * @param init_x The initial x position.
     * @param init_y The initial y position.
     * @param d The direction that the enemy is moving.
     * @param xMin The mininum x value.
     * @param xMax The maximum x value.
     * @param yMin The minimum y value.
     * @param yMax The maximum y value.
     * @param texture The texture.
     */
    public PatrollingEnemies(int init_x, int init_y, Direction d, int xMin, int xMax, int yMin, int yMax, TextureRegion texture) {
        super(init_x, init_y, new TextureRegion(new Texture("ptero.png"),0,0, 31,16));
        setFacing(d);
        setXMin(xMin);
        setXMax(xMax);
        setYMin(yMin);
        setYMax(yMax);
    }

    /**
     * Moves the enemy to the next block on the game board. <br>
     * Patrolling enemies can travel over walls, so the move is always successful (true).
     * @param input Any character.
     * @param gameBoard The game board.
     * @return Whether the move was successful.
     */
    public @Override boolean direction(char input, Board gameBoard) {
        // not going to use char input, but want to prevent anybody from being able to move PatrollingEnemies in a different way
        this.create_path();
        switch (moveTo){
            case 'W':
                this.setFacing(Direction.Up);
                if(gameBoard.getBlock(getX(), getY() + 1) != null) {
                    this.setY(this.getY() + 1);
                }
                break;
            case 'S':
                this.setFacing(Direction.Down);
                if(gameBoard.getBlock(getX(), getY() - 1) != null) {
                    this.setY(this.getY() - 1);
                }
                break;
            case 'A':
                this.setFacing(Direction.Left);
                if(gameBoard.getBlock(getX() - 1, getY()) != null) {
                    this.setX(this.getX() - 1);
                }
                break;
            case 'D':
                this.setFacing(Direction.Right);
                if(gameBoard.getBlock(getX() + 1, getY()) != null) {
                    this.setX(this.getX() + 1);
                }
                break;
        }
        return true;
    }

    /**
     * Change the direction of the enemy if it reaches the x or y bounds.
     */
    private void create_path() {
        switch(this.getFacing()) {
            case Up:
                if(getY() == getYMax()) {
                    setMoveTo('S');
                    setFacing(Direction.Down);
                } else {
                    setMoveTo('W');
                }
                break;
            case Right:
                if(getX() == getXMax()) {
                    setMoveTo('A');
                    setFacing(Direction.Left);
                } else {
                    setMoveTo('D');
                }
                break;
            case Down:
                if(getY() == getYMin()) {
                    setMoveTo('W');
                    setFacing(Direction.Up);
                } else {
                    setMoveTo('S');
                }
                break;
            case Left:
                if(getX() == getXMin()) {
                    setMoveTo('D');
                    setFacing(Direction.Right);
                } else {
                    setMoveTo('A');
                }
                break;
        }
    }

    /**
     * Returns the direction the enemy is moving to next.
     * Will return <i>'W', 'A', 'S', or 'D'</i>
     * @return The direction.
     */
    public char getMoveTo() {
        return this.moveTo;
    }

    /**
     * Returns the minimum x bound of the enemy.
     * @return The minimum x.
     */
    public int getXMin() {
        return this.xMin;
    }

    /**
     * Returns the maximum x bound of the enemy.
     * @return The maximum x.
     */
    public int getXMax() {
        return this.xMax;
    }

    /**
     * Returns the minimum y bound of the enemy.
     * @return The minimum y.
     */
    public int getYMin() {
        return this.yMin;
    }

    /**
     * Returns the maximum y bound of the enemy.
     * @return The maximum y.
     */
    public int getYMax() {
        return this.yMax;
    }

    /**
     * Set the minimum x bound to a new value.
     * @param minX The new minimum x.
     */
    private void setXMin(int minX) {
        if(minX > 0) {
            this.xMin = minX;
        } 
    }

    /**
     * Set the maximum x bound to a new value.
     * @param maxX The new maximum x.
     */
    private void setXMax(int maxX) {
        if(maxX > 0) {
            this.xMax = maxX;
        }
    }

    /**
     * Set the minimum y bound to a new value.
     * @param minY The new minimum y.
     */
    private void setYMin(int minY) {
        if(minY > 0) {
            this.yMin = minY;
        } 
    }

    /**
     * Set the maximum y bound to a new value.
     * @param maxY The new maximum y.
     */
    private void setYMax(int maxY) {
        if(maxY > 0) {
            this.yMax = maxY;
        }
    }

    /**
     * Change the direction the enemy is moving towards. <br>
     * The new value must be <i>'W', 'A', 'S', or 'D'</i>.
     * @param input The new direction.
     */
    private void setMoveTo(char input) {
        this.moveTo = input;
    }
}