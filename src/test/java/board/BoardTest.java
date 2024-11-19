package board;

import static org.junit.jupiter.api.Assertions.*;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.junit.jupiter.api.*;

import static org.mockito.Mockito.mock;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import rewards.Bonus_Reward;


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
    Batch mockBatch;

    @BeforeEach
    public void setup(){
        board = new Board();
        mockBoard = mock(Board.class);
        mockBatch = mock(Batch.class);
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
    void getStartTest() {
        Block start = board.getStart();

        assertEquals(1, start.getXPosition());
        assertEquals(1, start.getYPosition());
    }

    /**
     * Test get end block method.
     */
    @Test
    void getEndTest() {
        Block end = board.getEnd();

        assertEquals(1, end.getXPosition());
        assertEquals(21, end.getYPosition());
    }

    /**
     * Test get block at coordinates x y.
     */
    @Test
    void getBlockTest() {
        int x = 5;
        int y = 5;
        Block block = board.getBlock(x, y);
        assertEquals(x, block.getXPosition());
        assertEquals(y, block.getYPosition());

        // x = F
        assertNull(board.getBlock(-1, 5));
        assertNull(board.getBlock(board.getWidth(), 5));

        // y = F
        assertNull(board.getBlock(5, -1));
        assertNull(board.getBlock(5, board.getHeight()));
    }


    /**
     * Test get board width method.
     */
    @Test
    void getWidthTest() {
        assertEquals(23, board.getWidth());
    }

    /**
     * Test get board height method.
     */
    @Test
    void getHeightTest() {
        assertEquals(23, board.getHeight());
    }

    /**
     * Test get total regular rewards count and collection.
     */
    @Test
    void regularRewardCollectTest() {
        // there is a reward at x:4, y:15, score:10
        // Initial regular rewards in board.
        int regRewardCnt = 3;
        assertEquals(regRewardCnt, board.getTotalRegRewardCnt());

        int expectedScore = 10;
        int score = -1;
        // Try collect reward when there is no reward there.
        score = board.regRewardCollect(4,0); // TF
        assertEquals(0, score);
        assertEquals(regRewardCnt, board.getTotalRegRewardCnt());

        score = board.regRewardCollect(0,15); // FT
        assertEquals(0, score);
        assertEquals(regRewardCnt, board.getTotalRegRewardCnt());

        score = board.regRewardCollect(0,0); // FF
        assertEquals(0, score);
        assertEquals(regRewardCnt, board.getTotalRegRewardCnt());

        // Collect a reward at where there is a reward.
        score = board.regRewardCollect(4, 15); //TT
        assertEquals(expectedScore, score);
        assertEquals(regRewardCnt - 1, board.getTotalRegRewardCnt());
    }

    /**
     * Test collect bonus reward.
     */
    @Test
    void bonRewardCollectTest() {
        // there is a reward at x:3, y:21, startTime:10, endTime: 20, score:10
        // initial bonus rewards in board.
        int bonusRewardCnt = 3;
        assertEquals(bonusRewardCnt, board.getTotalBonusRewardCnt());

        int score = -1;
        int expectedScore = 10;

        // Try collect bonus reward when not there
        score = board.bonRewardCollect(0,0, 0);
        assertEquals(0, score);
        assertEquals(bonusRewardCnt, board.getTotalBonusRewardCnt());

        score = board.bonRewardCollect(3, 21, 9);
        assertEquals(0, score);
        assertEquals(bonusRewardCnt, board.getTotalBonusRewardCnt());

        score = board.bonRewardCollect(3, 21, 21);
        assertEquals(0, score);
        assertEquals(bonusRewardCnt, board.getTotalBonusRewardCnt());

        // Collect a bonus reward
        score = board.bonRewardCollect(3, 21, 10);
        assertEquals(expectedScore, score);
        assertEquals(bonusRewardCnt-1, board.getTotalBonusRewardCnt());
    }

    /**
     * Test collect regular punishment.
     */
    @Test
    void regPunishmentCollectTest() {
        // there is a reg punish at x:14, y:2, score:10
        // initial regular punishment count.
        int regPunishmentCnt = 4;
        assertEquals(regPunishmentCnt, board.getTotalRegPunishmentCnt());

        int score = -1;
        int expectedScore = 10;

        // Try collect punishment when there is no punishment there.
        score = board.regPunishmentCollect(0,0);
        assertEquals(0, score);
        assertEquals(regPunishmentCnt, board.getTotalRegPunishmentCnt());

        score = board.regPunishmentCollect(14,0);
        assertEquals(0, score);
        assertEquals(regPunishmentCnt, board.getTotalRegPunishmentCnt());

        score = board.regPunishmentCollect(0,2);
        assertEquals(0, score);
        assertEquals(regPunishmentCnt, board.getTotalRegPunishmentCnt());

        // Collect a punishment.
        score = board.regPunishmentCollect(14,2);
        assertEquals(expectedScore, score);
        assertEquals(regPunishmentCnt - 1, board.getTotalRegPunishmentCnt());
    }

    /**
     * Test collect bonus punishment.
     */
    @Test
    void bonPunishmentCollectTest() {
        // There is a bonus punish at x:13, y:21, score:10, startTime:0, endTime:10
        // Initial bonus punishments on the board.
        int bonPunishCnt = 5;
        assertEquals(bonPunishCnt, board.getTotalBonusPunishmentCnt());

        int score = -1;
        int expectedScore = 10;

        // Try collecting bonus punishments when not possible.
        score = board.bonPunishmentCollect(0,0,0);
        assertEquals(0, score);
        assertEquals(bonPunishCnt, board.getTotalBonusPunishmentCnt());

        score = board.bonPunishmentCollect(13,21,-1);
        assertEquals(0, score);
        assertEquals(bonPunishCnt, board.getTotalBonusPunishmentCnt());

        score = board.bonPunishmentCollect(13,21,11);
        assertEquals(0, score);
        assertEquals(bonPunishCnt, board.getTotalBonusPunishmentCnt());

        // Collect a bonus punishment.
        score = board.bonPunishmentCollect(13,21,0);
        assertEquals(expectedScore, score);
        assertEquals(bonPunishCnt - 1, board.getTotalBonusPunishmentCnt());
    }

    /**
     * Test generate new bonus punishments. TODO: finish
     */
    @Test
    void genNewBonusTest() {
    }

    @Test
    void drawTest(){

    }
}
