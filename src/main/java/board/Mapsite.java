package board;

/**
 * The MapSite class is a component of the Maze.
 * It is used by the board.Wall, board.Door, and Room classes.
 */
public abstract class Mapsite {
    public abstract boolean enter();
}