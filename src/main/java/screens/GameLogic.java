package screens;

import board.Board;
import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import entities.Character;

import java.util.ArrayList;


public class GameLogic {

    /**
     * This function checks and allows the enemies to move
     */
    public float moveEnemies(ArrayList<Enemies> enemies, Character player, Board gameboard, ArrayList<Boolean> canEnemyMove, int TILE_SIZE){
        for(int i = 0; i < enemies.size(); i++) {
            boolean isMovingEnemy = (enemies.get(i)) instanceof Moving_Enemies; // Q: are objects in list of type Enemy or do they retain their subclass?
            if(isMovingEnemy == true) {
                Moving_Enemies anEnemy = (Moving_Enemies) enemies.get(i);
                char direction = anEnemy.find_player(player, gameboard);
                boolean value = anEnemy.direction(direction, gameboard);
                canEnemyMove.set(i, value);
            } else {
                PatrollingEnemies anEnemy = (PatrollingEnemies) enemies.get(i);
                boolean value = anEnemy.direction('I', gameboard); // char input doesn't matter
                canEnemyMove.set(i, value);
            }
        }
        return (float) TILE_SIZE;
    }

    /**
     * Checks if the player and an enemy occupy same cell
     */
    public boolean checkPlayerCollision(Character player, ArrayList<Enemies> enemies){
        int playerX = player.getX();
        int playerY = player.getY();
        for(int i = 0; i < enemies.size(); i++) {
            Enemies anEnemy = enemies.get(i);
            int anEnemyX = anEnemy.getX();
            int anEnemyY = anEnemy.getY();
            if(playerX == anEnemyX && playerY == anEnemyY) {
                System.out.println("Player dead!");
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see if the player has reached the end
     */
    public boolean checkIfExitingMaze(Character player, Board gameboard) {
        int playerX = player.getX();
        int playerY = player.getY();
        if((gameboard.getEnd().getXPosition() == playerX) && (gameboard.getEnd().getYPosition() == playerY) && (gameboard.getTotalRegRewardCnt() == 0)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the player as reached a reward. <br>
     * If there is a reward, the reward will be collected and the score added to player score. <br>
     * The reward is removed from board after. <br>
     */
    public void checkReward(Character player, Board gameboard, float time) {
        int playerX = player.getX();
        int playerY = player.getY();
        int fromRegs = gameboard.regRewardCollect(playerX, playerY);
        int fromBons = gameboard.bonRewardCollect(playerX, playerY,time);
        int score = fromRegs + fromBons;
        player.add_score(score);
    }

    /**
     * Checks if the player's score is less than zero.
     */
    public boolean checkScore(Character player) {
        if(player.getScore() < 0) {
            return true;
        }
        return false;
    }

    /**
     * Check if the player reached a punishment. <br>
     * If there is a punishment, the punishment will be given to the player. <br>
     * The punishment is removed after.<br>
     */
    public void checkPunishment(Character player, Board gameboard, float time) {
        int playerX = player.getX();
        int playerY = player.getY();
        int score = gameboard.regPunishmentCollect(playerX, playerY) + gameboard.bonPunishmentCollect(playerX, playerY, time);
        player.minus_score(score);
    }
}
