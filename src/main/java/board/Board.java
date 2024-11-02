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
	private int height = 15;
	private int width = 20;

    public Board(){
        createBoard();
    }

    private void createBoard() {

        // Create board and fill with room blocks (free tiles)
		array = new ArrayList<ArrayList<Block>>(width);
		for(int i = 0; i < width; i++){
			array.add(new ArrayList<Block>(height));

		}
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
                if(i == 0 || j == 0  || i == width-1 || j == height-1){
                    array.get(i).add(j, new BarrierBlock(i,j, new TextureRegion(new Texture("terrain_bottom_left.png"))));
                }
                else{
                    array.get(i).add(j, new RoomBlock(i,j, new TextureRegion(new Texture("terrain_fill_center_center.png"))));
                }

			}
		}

        // TODO: add walls and "space" wall surrounding board


        // Create punishments and rewards on board
        array_regReward = new ArrayList<Reward>();
        array_bonReward = new ArrayList<Reward>();
        array_regPunishment = new ArrayList<Punishments>();
        array_bonPunishment = new ArrayList<Punishments>();

        // we will set the coordinates manually (see below example)
        // NOTE: make sure not to put the reward/punishment on a wall/another item
        array_regReward.add(new Regular_Reward(1, 3, 1));
        array_regPunishment.add(new NormalPunishments(5,5,1));

        setStart(array.get(width/2).get(0));
        setEnd(array.get(width/2).get(height-1));

    }
    public Block getStart() {
        return this.startRoomBlock;
    }
    public Block getEnd() {
        return this.endRoomBlock;
    }
    public int getTotalRegRewardCnt() {
        return this.array_regReward.size();
    }
    public void setStart(Block thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }
    public void setEnd(Block thisRoomBlock) {
        this.endRoomBlock = thisRoomBlock;
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
        array_bonPunishment.remove(index);
        return score;
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
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
        for(int i = 0; i < array_bonReward.size(); i++) {
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
        for(int i = 0; i < array_bonPunishment.size(); i++) {
            NormalPunishments punishmentsToDraw = (NormalPunishments) array_regPunishment.get(i);
            punishmentsToDraw.draw(batch, tilesize);
        }
    }
}
