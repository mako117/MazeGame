package board;
import com.badlogic.gdx.graphics.Texture;
import directions.Direction;
import punishments.NormalPunishments;
import punishments.Punishments;
import rewards.Bonus_Reward;
import rewards.Regular_Reward;
import rewards.Reward;
import punishments.BonusPunishments;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
    private Block startRoomBlock;
    private Block endRoomBlock;
    private ArrayList<ArrayList<Block>> array;
    private ArrayList<Reward> array_regReward;
    private ArrayList<Reward> array_bonReward;
    private ArrayList<Punishments> array_regPunishment;
    private ArrayList<Punishments> array_bonPunishment;
	private int height = 23;
	private int width = 23;
    private int totalRegRewardCnt = 0;

    public Board(){
        createBoard();
    }

    private void createBoard() {

        // Create board and fill with room blocks (free tiles), as well as surrounding the board with barrier blocks
		array = new ArrayList<ArrayList<Block>>(width);
		for(int i = 0; i < width; i++){
			array.add(new ArrayList<Block>(height));
		}
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
                if(i == 0 || j == 0  || i == width-1 || j == height-1){
                    array.get(i).add(j, new BarrierBlock(i,j, new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 16, 32,16,16)));
                }
                else{
                    array.get(i).add(j, new RoomBlock(i,j, new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 122, 0,16,16)));
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
        createLongWall(10,10,9,15);
        createLongWall(11,14,15,15);
        createLongWall(14,14,7,13);

        // Create punishments and rewards on board
        array_regReward = new ArrayList<Reward>();
        array_bonReward = new ArrayList<Reward>();
        array_regPunishment = new ArrayList<Punishments>();
        array_bonPunishment = new ArrayList<Punishments>();

        addRegReward(1,3,1,"bomb.png");
        addBonReward(10,1,5,"dinosaur_egg.png",1,20);
        addBonReward(9,2,5,"dinosaur_egg.png",21,40);
        addRegPunishment(5,5,1,"baby_dinosaur.png");
        addBonPunishment(9,2,5,"alien.png",1,20);
        addBonPunishment(10,1,5,"alien.png",21,40);

        setStart(array.get(width/2).get(1));
        setEnd(array.get(width-2).get(1));
        
    }

    public Block getStart() {
        return this.startRoomBlock;
    }

    public Block getEnd() {
        return this.endRoomBlock;
    }

    public int getTotalRegRewardCnt() {
        return this.totalRegRewardCnt;
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

    public int getWidth() {
        return this.width;
    }
    
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
     * @return the score
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
        int score = array_regPunishment.get(index).getPoint();
        // System.out.println("reg punishment score = " + score);
        array_regPunishment.remove(index);
        return score;
    }

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

        int score = array_bonPunishment.get(index).getPoint();
        // System.out.println("bonus punishment score = " + score);
        array_bonPunishment.remove(index);
        return score;
    }
    
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


    private void setStart(Block thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }

    private void setEnd(Block thisRoomBlock) {
        this.endRoomBlock = thisRoomBlock;
    }

    private void createWall(int x, int y){
        array.get(x).set(y, new Wall(x, y,new TextureRegion(new Texture("cave-platformer-tileset-16x16.png"), 0, 128,16,16)));
    }

    private void createLongWall(int startingX, int endingX, int startingY, int endingY) {
        if(startingX == endingX) {
            for(int i = startingY; i <= endingY; i++) {
                createWall(startingX, i);
            }
        } else if(startingY == endingY) {
            for(int i = startingX; i <= endingX; i++) {
                createWall(i, startingY);
            }
        } else {
            // we don't make a long wall if it isn't a straight line
        }
    }

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

    private void addRegReward(int x, int y, int score, String internalTexturePath) {
        array_regReward.add(new Regular_Reward(x, y, score, new TextureRegion(new Texture(internalTexturePath))));
        totalRegRewardCnt++;
    }

    private void addBonReward(int x, int y, int score, String internalTexturePath, int t1, int t2) {
        array_bonReward.add(new Bonus_Reward(x,y,score,new TextureRegion(new Texture(internalTexturePath)),t1,t2));
    }

    private void addRegPunishment(int x, int y, int score, String internalTexturePath) {
        array_regPunishment.add(new NormalPunishments(x,y,1,new TextureRegion(new Texture(internalTexturePath))));
    }

    private void addBonPunishment(int x, int y, int score, String internalTexturePath, int t1, int t2) {
        array_bonPunishment.add(new BonusPunishments(x,y,score,new TextureRegion(new Texture(internalTexturePath)),t1,t2));
    }
}
