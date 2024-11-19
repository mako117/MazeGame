package board;
import com.badlogic.gdx.graphics.Texture;
import punishments.NormalPunishments;
import punishments.Punishments;
import rewards.Bonus_Reward;
import rewards.Regular_Reward;
import rewards.Reward;
import punishments.BonusPunishments;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A board contain blocks, rewards, and punishments.
 */
public class Board {
    private Block startRoomBlock;
    private Block endRoomBlock;
    private ArrayList<ArrayList<Block>> array;
    private ArrayList<Reward> array_regReward;
    private ArrayList<Reward> array_bonReward;
    private ArrayList<Punishments> array_regPunishment;
    private ArrayList<Punishments> array_bonPunishment;
	private final int height = 23;
	private final int width = 23;
    private float t_last = 0;
    private static int T_PERIOD = 60;

    /**
     * Create a new board.
     */
    public Board(){
        createBoard();
    }

    /**
     * Create a templated board
     */
    protected void createBoard() {

        TextureRegion barrierBlockTexture = new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 16, 32,16,16);
        TextureRegion roomBlockTexture = new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 122, 0,16,16);


        // Create board and fill with room blocks (free tiles), as well as surrounding the board with barrier blocks
		array = new ArrayList<ArrayList<Block>>(width);
		for(int i = 0; i < width; i++){
			array.add(new ArrayList<Block>(height));
		}
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
                if(i == 0 || j == 0  || i == width-1 || j == height-1){
                    array.get(i).add(j, new BarrierBlock(i,j, barrierBlockTexture));
                }
                else{
                    array.get(i).add(j, new RoomBlock(i,j, roomBlockTexture));
                }
			}
		}

        // set up walls
        createLongWall(2, 2, 8, 21);
        createLongWall(8,8,20,21);
        createWallChunk(14,16,20,21);
        createLongWall(20,20,16,21);
        createLongWall(14,14,18,19);
        createLongWall(3,8,18,18);
        createLongWall(10,13,18,18);
        createLongWall(16,16,17,18);

        createLongWall(14,18,16,16);
        createLongWall(16,17,12,12);
        createLongWall(18,18,6,15);
        createLongWall(19,20,10,10);
        createLongWall(20,21,6,6);
        createWallChunk(18,21,1,2);

        createLongWall(1,4,6,6);
        createLongWall(4,4,2,5);
        createWall(2,4);
        createLongWall(8,8,1,2);
        createLongWall(12,12,2,8);
        createLongWall(6,11,4,4);
        createLongWall(6,6,4,8);
        createLongWall(7,8,8,8);
        createLongWall(13,15,6,6);
        createLongWall(16,16,2,6);

        createLongWall(4,5,12,12);
        createLongWall(6,6,10,16);
        createLongWall(7,8,14,14);
        createLongWall(10,10,16,17);
        createLongWall(10,10,8,14);
        createLongWall(11,14,14,14);
        createLongWall(14,14,7,13);

        // Create punishments and rewards on board
        array_regReward = new ArrayList<Reward>();
        array_bonReward = new ArrayList<Reward>();
        array_regPunishment = new ArrayList<Punishments>();
        array_bonPunishment = new ArrayList<Punishments>();

        // add regular rewards
        addRegReward(4,15,10,"bomb.png");
        addRegReward(8,11,10,"bomb.png");
        addRegReward(18,19,10,"bomb.png");

        // add bonus rewards
        addBonReward(3,21,10,"dinosaur_egg.png",10,20);
        addBonReward(20,4,10,"dinosaur_egg.png",30,40);
        addBonReward(21,21,10,"dinosaur_egg.png",50,60);

        // add regular punishments
        addRegPunishment(14,2,10,"baby_dinosaur.png");
        addRegPunishment(9,6,10,"baby_dinosaur.png");
        addRegPunishment(8,17,10,"baby_dinosaur.png");
        addRegPunishment(21,13,10,"baby_dinosaur.png");

        // add bonus punishments
        addBonPunishment(13,21,10,"alien.png",0,10);
        addBonPunishment(6,2,10,"alien.png",20,30);
        addBonPunishment(12,11,10,"alien.png",30,40);
        addBonPunishment(4,10,10,"alien.png",40,50);
        addBonPunishment(16,8,10,"alien.png",50,60);

        endRoomBlock = new RoomBlock(1,21,new TextureRegion(new Texture("green.png")));
        array.get(1).set(endRoomBlock.getYPosition(), endRoomBlock);
        setStart(array.get(1).get(1));
        setEnd(array.get(1).get(21));
        
    }

    /**
     * Returns the block that the player starts at.
     * @return The start block.
     */
    public Block getStart() {
        return this.startRoomBlock;
    }

    /**
     * Returns the block that the player ends on.
     * @return The end block.
     */
    public Block getEnd() {
        return this.endRoomBlock;
    }

    /**
     * Returns the total number of regulars reward currently on the board.
     * @return The regular reward count.
     */
    public int getTotalRegRewardCnt() {
        return array_regReward.size();
    }

    /**
     * Returns the total number of bonus rewards currently on the board.
     */
    public int getTotalBonusRewardCnt(){
        return array_bonReward.size();
    }

    /**
     * Returns the total number of regular punishments currently on the board.
     * @return The regular punishment count.
     */
    public int getTotalRegPunishmentCnt(){
        return array_regPunishment.size();
    }

    /**
     * Returns the total number of bonus punishments currently on the board.
     * @return The bonus punishment count.
     */
    public int getTotalBonusPunishmentCnt(){
        return array_bonPunishment.size();
    }

    /**
     * Return the block that is at the coordinates <br>
     * Invalid coordinates will return null. <br>
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the block or null
     */
    public Block getBlock(int x, int y) {
        if(x < 0 || x >= width){
            return null;
        }
        if(y < 0 || y >= height){
            return null;
        }

		return array.get(x).get(y);
    }

    /**
     * Returns the width of the board.
     * @return The width.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the board.
     * @return The height.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Collect the reward if there is a reward at the given coordinates. <br>
     * The reward will be removed from the rewards array.<br>
     * Return the score of the corresponding reward.<br>
     * If there is no reward, the score returned will be zero. <br>
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the number of points from the reward.
     */
    public int regRewardCollect(int x, int y) {
    	int index = isRewardHere(x, y, array_regReward);
        if(index == -1) {
            return 0;
        }
        int score = array_regReward.get(index).getPoint();
        // System.out.println("reg reward score = " + score);
        array_regReward.remove(index);
        return score;
    }

    /**
     * Collect the bonus reward if there is a bonus reward at the given coordinates and time. <br>
     * The reward will be removed from the bonus rewards array.<br>
     * Return the score of the corresponding bonus reward.<br>
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param currentTime The current time.
     * @return the number of points from the reward.
     */
    public int bonRewardCollect(int x, int y, float currentTime) {
    	int index = isRewardHere(x, y, array_bonReward);
        if(index == -1) {
            return 0;
        }

        Bonus_Reward aBonus = (Bonus_Reward) array_bonReward.get(index);
        int start = aBonus.getStarttime();
        int end = aBonus.getEndtime();
        if(currentTime < start || currentTime > end) {
            return 0;
        }

        int score = array_bonReward.get(index).getPoint();
        // System.out.println("bonus reward score = " + score);
        array_bonReward.remove(index);
        return score;
    }

    /**
     * Deal the punishment if there is a punishment at the given coordinates. <br>
     * The punishment will be removed from the punishments array. <br>
     * Return the points from the punishments. <br>
     * If there is no punishment, the points returned will be zero. <br>
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the points of the punishments
     */
    public int regPunishmentCollect(int x, int y) {
        int index = isPunishmentHere(x, y, array_regPunishment);
        if(index == -1){
            return 0;
        }
        int score = array_regPunishment.get(index).getPunishmentScore();
//         System.out.println("reg punishment score = " + score);
        array_regPunishment.remove(index);
        return score;
    }


    /**
     * Deal the bonus punishment if there is a punishment at the given coordinates and time. <br>
     * The punishment will be removed from the bonus punishments array. <br>
     * Return the points from the bonus punishments. <br>
     * If there is no punishment, the points returned will be zero. <br>
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param currentTime the current time.
     * @return the points of the punishments.
     */
    public int bonPunishmentCollect(int x, int y, float currentTime) {
        int index = isPunishmentHere(x, y, array_bonPunishment);
        if(index == -1){
            return 0;
        }

        BonusPunishments aBonus = (BonusPunishments) array_bonPunishment.get(index);
        int start = aBonus.getStartTime();
        int end = aBonus.getEndTime();
        if(currentTime < start || currentTime > end) {
            return 0;
        }

        int score = array_bonPunishment.get(index).getPunishmentScore();
        // System.out.println("bonus punishment score = " + score);
        array_bonPunishment.remove(index);
        return score;
    }

    /**
     * Draw the board to the screen.
     * @param batch The sprite batch.
     * @param time The current time for bonus rewards and punishments.
     * @param tilesize The size of tile.
     */
    public void draw(Batch batch, float time, int tilesize) {
        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getHeight(); j++) {
                Block blockToDraw = getBlock(i, j);
                blockToDraw.draw(batch,tilesize);
            }
        }
        for(int i = 0; i < array_bonReward.size(); i++) {
            Bonus_Reward rewardToDraw = (Bonus_Reward) array_bonReward.get(i);
            int start = rewardToDraw.getStarttime();
            int end = rewardToDraw.getEndtime();
            if(!(time < start || time > end)) {
                rewardToDraw.draw(batch, tilesize);
            } 
        }
        for(int i = 0; i < array_regReward.size(); i++) {
            Regular_Reward rewardToDraw = (Regular_Reward) array_regReward.get(i);
            rewardToDraw.draw(batch, tilesize);
        }
        for(int i = 0; i < array_bonPunishment.size(); i++) {
            BonusPunishments punishmentsToDraw = (BonusPunishments) array_bonPunishment.get(i);
            int start = punishmentsToDraw.getStartTime();
            int end = punishmentsToDraw.getEndTime();
            if(!(time < start || time > end)) {
                punishmentsToDraw.draw(batch, tilesize);
            }        
        }
        for(int i = 0; i < array_regPunishment.size(); i++) {
            NormalPunishments punishmentsToDraw = (NormalPunishments) array_regPunishment.get(i);
            punishmentsToDraw.draw(batch, tilesize);
        }
    }

    /**
     * Generate new bonus punishments and rewards on the board. <br>
     * New rewards and punishments are generated every 60 seconds.
     * @param t the current time.
     */
    public void genNewBonus(float t) {
        // System.out.println("t_last = " + this.t_last + ", t = " + t);
        if ((int)(t - this.t_last) == T_PERIOD) {
            this.t_last = t;
            // add bonus rewards
            array_bonReward.clear();
            addBonReward(3,21,10,"dinosaur_egg.png",(int)this.t_last + 10,(int)this.t_last + 20);
            addBonReward(20,4,10,"dinosaur_egg.png",(int)this.t_last + 30,(int)this.t_last + 40);
            addBonReward(21,21,10,"dinosaur_egg.png",(int)this.t_last + 50,(int)this.t_last + 60);
            // add bonus punishments
            array_bonPunishment.clear();
            addBonPunishment(13,21,10,"alien.png",(int)this.t_last,(int)this.t_last + 10);
            addBonPunishment(6,2,10,"alien.png",(int)this.t_last + 20,(int)this.t_last + 30);
            addBonPunishment(12,11,10,"alien.png",(int)this.t_last + 30,(int)this.t_last + 40);
            addBonPunishment(4,10,10,"alien.png",(int)this.t_last + 40,(int)this.t_last + 50);
            addBonPunishment(16,8,10,"alien.png",(int)this.t_last + 50,(int)this.t_last + 60);
        }
    }

    /**
     * Set the start block to the given block.
     * @param thisRoomBlock The block to set the start to.
     */
    private void setStart(Block thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }

    /**
     * Set the end block to the given block.
     * @param thisRoomBlock
     */
    private void setEnd(Block thisRoomBlock) {
        this.endRoomBlock = thisRoomBlock;
    }

    /**
     * Create a wall at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private void createWall(int x, int y){
        array.get(x).set(y, new Wall(x, y,new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 0, 128,16,16)));
    }

    /**
     * Create a long straight wall between two points.
     * @param startingX The start x coordinate.
     * @param endingX The end x coordinate.
     * @param startingY The start y coordinate.
     * @param endingY The end y coordinate.
     */
    private void createLongWall(int startingX, int endingX, int startingY, int endingY) {
        if(startingX == endingX) {
            for(int i = startingY; i <= endingY; i++) {
                createWall(startingX, i);
            }
        }
        else if(startingY == endingY) {
            for(int i = startingX; i <= endingX; i++) {
                createWall(i, startingY);
            }
        }
    }

    /**
     * Create a wall chunk within the given bound.<br>
     * The wall is a group of wall blocks.
     * @param leftX The left bound.
     * @param rightX The right bound.
     * @param bottomY The bottom bound.
     * @param topY The top bound
     */
    private void createWallChunk(int leftX, int rightX, int bottomY, int topY) {
        for(int i = leftX; i <= rightX; i++) {
            for(int j = bottomY; j <= topY; j++) {
                createWall(i,j);
            }
        }
    }

    /**
     * Check if the given coordinates has a reward
     * Return the index that the reward is in, in the reward_array, otherwise -1.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the index in the reward array
     */
    private int isRewardHere(int x, int y, ArrayList<Reward> array_reward) {
    	for(int i = 0; i < array_reward.size(); i++){
            if (x == array_reward.get(i).Xposition() && y == array_reward.get(i).Yposition()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Check if the given coordinates have a punishment
     * Return the index the punishment is in, in the punishment array, otherwise -1.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the index in the punishment array
     */
    private int isPunishmentHere(int x, int y, ArrayList<Punishments> array_punishments) {
        for(int i = 0; i < array_punishments.size(); i++){
            if (x == array_punishments.get(i).XPosition() && y == array_punishments.get(i).YPosition()){
                return i;
            }
        }
        return -1;
    }

    /**
     * Add a new regular reward to the board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param score The score of the new reward.
     * @param internalTexturePath The texture.
     */
    private void addRegReward(int x, int y, int score, String internalTexturePath) {
        array_regReward.add(new Regular_Reward(x, y, score, new TextureRegion(new Texture(internalTexturePath))));
    }

    /**
     * Add a new bonus reward to the board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param score The score of the new bonus reward.
     * @param internalTexturePath The texture.
     * @param t1 The start time.
     * @param t2 The end time.
     */
    private void addBonReward(int x, int y, int score, String internalTexturePath, int t1, int t2) {
        array_bonReward.add(new Bonus_Reward(x,y,score,new TextureRegion(new Texture(internalTexturePath)),t1,t2));
    }

    /**
     * Add a new regular punishment to the board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param score The score of the punishment.
     * @param internalTexturePath The texture.
     */
    private void addRegPunishment(int x, int y, int score, String internalTexturePath) {
        array_regPunishment.add(new NormalPunishments(x,y,score,new TextureRegion(new Texture(internalTexturePath))));
    }

    /**
     * Add a new bonus punishment to the board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param score The score of the bonus punishment.
     * @param internalTexturePath The texture.
     * @param t1 The start time.
     * @param t2 The end time.
     */
    private void addBonPunishment(int x, int y, int score, String internalTexturePath, int t1, int t2) {
        array_bonPunishment.add(new BonusPunishments(x,y,score,new TextureRegion(new Texture(internalTexturePath)),t1,t2));
    }
}