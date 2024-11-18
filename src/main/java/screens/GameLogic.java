package screens;

import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import board.Board;
import java.util.ArrayList;
import entities.Character;

public class GameLogic {

    /**
     * Checks if the player and an enemy
     */
    public boolean checkPlayerCollision(Character player, ArrayList<Enemies> enemies){
        // System.out.println("Entering collision function");
        int playerX = player.getX();
        int playerY = player.getY();
        for(int i = 0; i < enemies.size(); i++) {
            Enemies anEnemy = enemies.get(i);
            int anEnemyX = anEnemy.getX();
            int anEnemyY = anEnemy.getY();
            if(playerX == anEnemyX && playerY == anEnemyY) {
                // System.out.println("Player dead!");
                return true;
            }
        }
        return false;
        // System.out.println("Out of for loop");
    }

    /**
     * Checks to see if the player has reached the end
     */
    public boolean checkIfExitingMaze(Character player, Board gameboard) {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerRewardCnt = player.getRewardsCollected();
        if((gameboard.getEnd().getXPosition() == playerX) && (gameboard.getEnd().getYPosition() == playerY) && (playerRewardCnt == gameboard.getTotalRegRewardCnt())) {
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
        if(fromRegs > 0) {
            player.addRegReward();
            // System.out.println("triggered, total reg rewards collected = " + player.getRewardsCollected());
        }
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
