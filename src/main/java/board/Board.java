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
    private ArrayList<ArrayList<Reward>> array_reward;
    private ArrayList<ArrayList<Punishments>> array_punishment;
	private int height = 10;
	private int width = 15;

    public Board(){
        createBoard();
    }

    private void createBoard() {
		array = new ArrayList<ArrayList<Block>>(width);
		for(int i = 0; i < width; i++){
			array.add(new ArrayList<Block>(height));
			array_reward.add(new ArrayList<Reward>(height));
			array_punishment.add(new ArrayList<Punishments>(height));
		}
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				array.get(i).add(j, new RoomBlock(i,j));
				array_reward.get(i).add(j,null);
				array_punishment.get(i).add(j,null);
			}
		}
		
		array_reward.get(2).add(2, new Regular_Reward(2,2,10));
		array_reward.get(3).add(3, new Regular_Reward(3,3,10));
		array_reward.get(4).add(4, new Regular_Reward(4,4,10));
		array_reward.get(5).add(5, new Regular_Reward(5,5,10));
		
		array_punishment.get(2).add(3, new NormalPunishments(2,3,10));
		array_punishment.get(3).add(4, new NormalPunishments(3,4,10));
		array_punishment.get(4).add(5, new NormalPunishments(4,5,10));
		array_punishment.get(5).add(6, new NormalPunishments(5,6,10));
		
		
		
		// TODO: add walls and "space" wall surrounding board


//    	array = new Block[3][3];
//    	array[0][0] = new RoomBlock(0,0);
//    	array[0][1] = new RoomBlock(0,1);
//    	array[0][2] = new RoomBlock(0,2);
//    	array[1][0] = new RoomBlock(1,0);
//    	array[1][1] = new BarrierBlock(1,1);
//    	array[1][2] = new RoomBlock(1,2);
//    	array[2][0] = new RoomBlock(2,0);
//    	array[2][1] = new RoomBlock(2,1);
//    	array[2][1] = new RoomBlock(2,2);
//
//    	Door d1 = new Door(array[0][0],array[0][1]);
//    	Door d2 = new Door(array[0][1],array[0][2]);
//    	Door d3 = new Door(array[0][2],array[1][2]);
//    	Door d4 = new Door(array[1][2],array[2][2]);
//    	Door d5 = new Door(array[2][2],array[2][1]);
//    	Door d6 = new Door(array[2][1],array[2][0]);
//    	Door d7 = new Door(array[2][0],array[1][0]);
//    	Door d8 = new Door(array[1][0],array[0][0]);
//
//    	array[0][0].setSide(Direction.North, d8);
//    	array[0][0].setSide(Direction.South, new Wall());
//    	array[0][0].setSide(Direction.West, new Wall());
//    	array[0][0].setSide(Direction.East, d1);
//
//    	array[0][1].setSide(Direction.North, new Wall());
//    	array[0][1].setSide(Direction.South, new Wall());
//    	array[0][1].setSide(Direction.West, d1);
//    	array[0][1].setSide(Direction.East, d2);
//
//    	array[0][2].setSide(Direction.North, d3);
//    	array[0][2].setSide(Direction.South, new Wall());
//    	array[0][2].setSide(Direction.West, d2);
//    	array[0][2].setSide(Direction.East, new Wall());
//
//    	array[1][0].setSide(Direction.North, d7);
//    	array[1][0].setSide(Direction.South, d8);
//    	array[1][0].setSide(Direction.West, new Wall());
//    	array[1][0].setSide(Direction.East, new Wall());
//
//    	array[1][1].setSide(Direction.North, new Wall());
//    	array[1][1].setSide(Direction.South, new Wall());
//    	array[1][1].setSide(Direction.West, new Wall());
//    	array[1][1].setSide(Direction.East, new Wall());
//
//    	array[1][2].setSide(Direction.North, d4);
//    	array[1][2].setSide(Direction.South, d3);
//    	array[1][2].setSide(Direction.West, new Wall());
//    	array[1][2].setSide(Direction.East, new Wall());
//
//    	array[2][0].setSide(Direction.North, new Wall());
//    	array[2][0].setSide(Direction.South, d7);
//    	array[2][0].setSide(Direction.West, new Wall());
//    	array[2][0].setSide(Direction.East, d6);
//
//    	array[2][1].setSide(Direction.North, new Wall());
//    	array[2][1].setSide(Direction.South, new Wall());
//    	array[2][1].setSide(Direction.West, d6);
//    	array[2][1].setSide(Direction.East, d5);
//
//    	array[2][2].setSide(Direction.North, new Wall());
//    	array[2][2].setSide(Direction.South, d4);
//    	array[2][2].setSide(Direction.West, d5);
//    	array[2][2].setSide(Direction.East, new Wall());
    }
    public void setStart(RoomBlock thisRoomBlock) {
        this.startRoomBlock = thisRoomBlock;
    }
    public void setEnd(RoomBlock thisRoomBlock) {
        this.endRoomBlock = thisRoomBlock;
    }

    public Block getBlock(int x, int y) {
        if(x < 0 || x >= width){
            return null;
        }
        if(y < 0 || y >= height){
            return null;
        }

		return array.get(x).get(y);
//        return array[x][y];
    }
    
    public Reward getReward(int x, int y) {
    	return array_reward.get(x).get(y);
    }
    
    public Punishments getPunishment(int x, int y) {
    	return array_punishment.get(x).get(y);
    }
    
    public boolean reward_collect(int x, int y) {
    	if(getReward(x,y) == null) {
    		return false;
    	}
    	return true;
    }
    
    public boolean punishment_collect(int x, int y) {
    	if(getPunishment(x,y) == null) {
    		return false;
    	}
    	return true;
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
