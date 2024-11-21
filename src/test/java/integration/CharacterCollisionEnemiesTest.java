package integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import entities.Character;
import entities.enemy.Enemies;
import entities.enemy.Moving_Enemies;
import entities.enemy.PatrollingEnemies;
import screens.GameLogic;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import directions.Direction;

public class CharacterCollisionEnemiesTest {

	private TextureRegion tex = mock(TextureRegion.class);
	private GameLogic logic = new GameLogic();
	
	/**
	 * Test when character collision with moving enemies
	 */
	@Test
	void CharacterCollisionwithMovingEnemies() {
		Character c = new Character(tex);

		ArrayList<Enemies> enemies = new ArrayList<Enemies>();
		assertFalse(logic.checkPlayerCollision(c, enemies));

		Moving_Enemies e1 = new Moving_Enemies(2,2,tex);
		enemies.add(e1);
		Moving_Enemies e2 = new Moving_Enemies(1,2,tex);
		enemies.add(e2);
		Moving_Enemies e3 = new Moving_Enemies(2,1,tex);
		enemies.add(e3);
		assertFalse(logic.checkPlayerCollision(c, enemies));
		
		Moving_Enemies e4 = new Moving_Enemies(1,1,tex);
		enemies.add(e4);
		assertTrue(logic.checkPlayerCollision(c, enemies));
	}
	
	/**
	 * Test when character collision with patrolling enemies
	 */
	@Test
	void CharacterCollisionwithPatrollingEnemies() {
		Character c = new Character(tex);
		
		ArrayList<Enemies> enemies = new ArrayList<Enemies>();
		
		PatrollingEnemies e1 = new PatrollingEnemies(2,2,Direction.Up,1,1,1,1,tex);
		enemies.add(e1);
		assertFalse(logic.checkPlayerCollision(c, enemies));
		
		PatrollingEnemies e2 = new PatrollingEnemies(1,2,Direction.Up,1,1,1,1,tex);
		enemies.add(e2);
		assertFalse(logic.checkPlayerCollision(c, enemies));
		
		PatrollingEnemies e3 = new PatrollingEnemies(2,1,Direction.Up,1,1,1,1,tex);
		enemies.add(e3);
		assertFalse(logic.checkPlayerCollision(c, enemies));
		
		PatrollingEnemies e4 = new PatrollingEnemies(1,1,Direction.Up,1,1,1,1,tex);
		enemies.add(e4);
		assertTrue(logic.checkPlayerCollision(c, enemies));
	}

}
