package entities.enemies;
import java.util.Iterator;
import java.util.List;
import entities.BoundingBox;
import entities.CollisionManager;
import entities.platforms.Platform;
import game.Game;

public class EnemyCollisionManager implements CollisionManager<Enemy> {
	
	protected List<Platform> platforms;
	protected List<Enemy> enemies;
	protected Game game;
	
	
	public EnemyCollisionManager(Game game) {
		this.platforms = game.getCurrentLevel().getPlatforms();
		this.game = game;
		this.enemies = game.getCurrentLevel().getEnemies();
	}
	
	public void platformsCollisions(Enemy enemy){
	    boolean collision = false;
		Iterator<Platform> it = platforms.iterator();
		BoundingBox enemyBox = enemy.getBoundingBox();
		Platform platform;
		while (it.hasNext()){
			platform = it.next();
			collision = enemyBox.collision(platform.getBoundingBox());               
			if(collision){
				platform.acceptVisit(enemy);
				collisionWithPlatform(enemy, platform);
			}
		}
	}
	
	private void collisionWithPlatform(Enemy enemy, Platform platform){
		if (enemy.getVerticalSpeed() < 0 && enemy.downCollision(platform) ) 
			downPlatformCollision(enemy, platform);
		else if(enemy.leftCollision(platform) && enemy.getDirection()=="Left")
			leftPlatformCollision(enemy, platform);
		else if(enemy.rightCollision(platform) && enemy.getDirection()=="Right")
			rightPlatformCollision(enemy, platform);
		platform.visit(enemy);
	}
	private void downPlatformCollision(Enemy enemy, Platform platform){
		enemy.setIsInAir(false);
		enemy.setVerticalSpeed(0);
		enemy.setY(platform.getY() + (platform.getHeight()));
	}
	private void leftPlatformCollision(Enemy enemy, Platform platform){
		enemy.setX(platform.getX() + platform.getWidth());
		enemy.setDirection("Right");
	}
	private void rightPlatformCollision(Enemy enemy, Platform platform){
		enemy.setX(Math.round(enemy.getX()));
		enemy.setDirection("Left");
	}

	public void enemiesCollisions(Enemy enemySource){
		boolean collision = false;
        Iterator<Enemy> enemiesIt = enemies.iterator();
        Enemy enemyDestination;
        boolean endIteration = false;
        while(enemiesIt.hasNext() && !endIteration){
            enemyDestination = enemiesIt.next();
			if(enemyDestination.isActive() && !enemySource.equals(enemyDestination)){
				collision = enemySource.colision(enemyDestination);
				if (collision) {
					collisionWithOtherEnemy(enemySource, enemyDestination);
					endIteration = true;
				}
			}
        }
	}

	public void powerUpsCollisions(Enemy entity) {}
	
	private void collisionWithOtherEnemy(Enemy enemySource, Enemy enemyDestination){
		enemyDestination.acceptVisit(enemySource);
	}
}
