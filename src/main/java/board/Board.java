package board;
import directions.Direction;
import punishments.NormalPunishments;
import punishments.Punishments;
import rewards.Regular_Reward;
import rewards.Reward;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Board {
    private RoomBlock startRoomBlock;
    private RoomBlock endRoomBlock;
    private ArrayList<ArrayList<Block>> array;
    private ArrayList<Reward> array_reward;
    private ArrayList<Punishments> array_punishment;
	private int height = 10;
	private int width = 15;

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
				array.get(i).add(j, new RoomBlock(i,j));
			}
		}

        // TODO: add walls and "space" wall surrounding board


        // Create punishments and rewards on board
        array_reward = new ArrayList<Reward>();
        array_punishment = new ArrayList<Punishments>();

        // we will set the coordinates manually (see below example)
        // NOTE: make sure not to put the reward/punishment on a wall/another item
        array_reward.add(new Regular_Reward(1, 3, 1));
        array_punishment.add(new NormalPunishments(5,5,1));


    }
    public void setStart(RoomBlock thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }
    public void setEnd(RoomBlock thisRoomBlock) {
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
    private int isRewardHere(int x, int y) {
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
    private int isPunishmentHere(int x, int y) {
        for(int i = 0; i < array_punishment.size(); i++){
            if (x == array_punishment.get(i).XPosition() && y == array_punishment.get(i).YPosition()){
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
    public int rewardCollect(int x, int y) {
    	int index = isRewardHere(x, y);
        if(index == -1){
            return 0;
        }
        int score = array_reward.get(index).getPoint();
        array_reward.remove(index);
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
    public int punishmentCollect(int x, int y) {
        int index = isPunishmentHere(x, y);
        if(index == -1){
            return 0;
        }
        int score = array_punishment.get(index).getPoint();
        array_punishment.remove(index);
        return score;
    }

    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
    
    
    public void draw(Batch batch) {
        for(int i = 0; i < getWidth(); i++) {
            for(int j = 0; j < getHeight(); j++) {
                Block blockToDraw = getBlock(i, j);
                blockToDraw.draw(batch);
            }
        }
    }
}
