import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class MazeGame {
    public static void main(String args[]) {
        Character Rock = new Character();
        Board gameBoard = new Board();
        gameBoard.createBoard();
    }
}