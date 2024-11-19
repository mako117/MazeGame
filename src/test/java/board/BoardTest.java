package board;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;




/**
 * Create context for libgdx components in board. T-T <br>
 * By extending this in our test class, we will override the create method, allowing Gdx textures to be initialized. <br>
 * Solution from: <a href="https://www.reddit.com/r/libgdx/comments/1by4jgn/loading_assets_for_tests_in_libgdx/">Loading assets for tests in libgdx?</a>
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class AbstractTestWithHeadlessGdxContext extends ApplicationAdapter {
    HeadlessApplication application;

    AbstractTestWithHeadlessGdxContext() {
        HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
        application = new HeadlessApplication(this, conf);
        Gdx.gl = mock(GL20.class);
    }

    @Override
    public void render() {
        // no-op, prevents exception when trying to render since we are using a headless application
    }

    @AfterAll
    void afterAll() {
        application.exit();
    }
}



public class BoardTest extends AbstractTestWithHeadlessGdxContext{

    Board board;
    Board mockBoard;

    @BeforeAll
    public void setup(){
        board = new Board();
        mockBoard = mock(Board.class);
    }

    // Test block features
    /**
     * Test barrier block enter method.
     */
    @Test
    void barrierBlockEnterTest(){
        BarrierBlock b = new BarrierBlock();
        BarrierBlock b2 = new BarrierBlock(0, 0, null);

        assertFalse(b.enter());
        assertFalse(b2.enter());
    }

    /**
     * Test room block enter method.
     */
    @Test
    void roomBlockEnterTest(){
        RoomBlock b = new RoomBlock();
        RoomBlock b2 = new RoomBlock(0, 0, null);

        assertTrue(b.enter());
        assertTrue(b2.enter());
    }

    /**
     * Test wall enter method.
     */
    @Test
    void wallEnterTest(){
        Wall w = new Wall();
        Wall w2 = new Wall(0, 0, null);

        assertFalse(w.enter());
        assertFalse(w2.enter());
    }

    /**
     * Test block position getter.
     */
    @Test
    void blockPositionTest(){
        Block b = new RoomBlock(0,1, null);

        assertEquals(0, b.getXPosition());
        assertEquals(1, b.getYPosition());

        b.setXPosition(1);
        b.setYPosition(2);

        assertEquals(1, b.getXPosition());
        assertEquals(2, b.getYPosition());

    }


    // Test board features

    /**
     * Test get start block method.
     */
    @Test
    void getStart() {
        Block start = board.getStart();

        assertEquals(start.getXPosition(), 1);
        assertEquals(start.getYPosition(), 1);
    }

    /**
     * Test get end block method.
     */
    @Test
    void getEnd() {
        Block end = board.getEnd();

        assertEquals(end.getXPosition(), 1);
        assertEquals(end.getYPosition(), 21);
    }

    /**
     * Test get total regular rewards count and collection.
     */
    @Test
    void regularRewardCollection() {
        // Initial regular rewards in board.
        int regRewardCnt = 3;
        assertEquals(board.getTotalRegRewardCnt(), regRewardCnt);

        // Collect a reward at where there is a reward.
        board.regRewardCollect(4, 15);
        regRewardCnt = 2;
        assertEquals(board.getTotalRegRewardCnt(), regRewardCnt);

        // Collect reward when there is no reward there.
        board.regRewardCollect(0,0);
        assertEquals(board.getTotalRegRewardCnt(), regRewardCnt);
    }

    /**
     * Test get block at coordinates x y.
     */
    @Test
    void getBlock() {
        int x = 5;
        int y = 5;
        Block block = board.getBlock(x, y);

        assertEquals(block.getXPosition(), x);
        assertEquals(block.getYPosition(), y);

    }


    /**
     * Test get board width method.
     */
    @Test
    void getWidth() {
        assertEquals(board.getWidth(), 23);
    }

    /**
     * Test get board height method.
     */
    @Test
    void getHeight() {
        assertEquals(board.getHeight(), 23);
    }

    /**
     * Test collect regular reward.         // TODO: complete the rest
     */
    @Test
    void regRewardCollect() {
    }

    /**
     * Test collect bonus reward.
     */
    @Test
    void bonRewardCollect() {
    }

    /**
     * Test collect regular punishment.
     */
    @Test
    void regPunishmentCollect() {
    }

    /**
     * Test collect bonus punishment.
     */
    @Test
    void bonPunishmentCollect() {
    }

    /**
     * Test generate new bonus rewards.
     */
    @Test
    void genNewBonus() {
    }
}
