package entities.enemies;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import entities.platforms.Platform;
import game.Game;
import tools.LogicTools;
import views.ViewConstants;
import entities.character.Character;

public class EnemyThread extends Thread {
	
    protected List<Enemy> enemies;
    private EnemyCollisionManager ecm; 
    private int frameCount;
	private HashMap<String, Platform> platformsByCoords;
    private boolean isRunning;
    private Game game;

    public EnemyThread(Game game) {
        this.enemies = game.getCurrentLevel().getEnemies();
        this.frameCount = 0;
        this.ecm = new EnemyCollisionManager(game);
        this.platformsByCoords = LogicTools.groupPlatformsByCoords(game.getCurrentLevel().getPlatforms());
        this.game = game;
    }

    public void run() {
    	List<Enemy> enemyCopy = new ArrayList<>(enemies); //Copia de la lista enemigos para poder modificarla
        setIsRunning(true);
    	while(isRunning) {
    		Iterator<Enemy> iterator = enemyCopy.iterator();
    		Enemy enemy;
    		frameCount++;
    		while (iterator.hasNext()) {
    			enemy = iterator.next();               
    			if (enemy.isActive()) {    
                    if(enemy.canThrowEgg()){
                        game.createEgg(Math.round(enemy.getX()), Math.round(enemy.getY()));                        
                        enemyCopy = new ArrayList<>(enemies);
                    }
    				moveEnemy(enemy);
                    checkDeactivateEnemy(enemy);
    			}
                if(!enemy.isAlive()){
                    enemies.remove(enemy);                      
                    enemyCopy = new ArrayList<>(enemies);
                    enemy.deactivateEnemy();
                }
    	    }
            if(!game.getCurrentLevel().getCharacter().isInEnd()){
                try {
                    Thread.sleep(ViewConstants.GAMETICK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setIsRunning(boolean value) {
        this.isRunning = value;
    }

    public void resetPlatformsByCoords(){
        platformsByCoords = LogicTools.groupPlatformsByCoords(game.getCurrentLevel().getPlatforms());
    }
    
    private void moveEnemy(Enemy enemy) {
    	ecm.platformsCollisions(enemy);
        ecm.enemiesCollisions(enemy);
        enemy.applyGravity();
        checkEnemyInAir(enemy);
        enemy.move();
        
        if(frameCount%20==0)
            enemy.updateSpriteNumber();
    }
    
    private void checkEnemyInAir(Enemy enemy){
    	if(!enemy.isInAir() && !LogicTools.isOnSolid(platformsByCoords,enemy) ){
            enemy.setIsInAir(true);
        }
    }

    private void checkDeactivateEnemy(Enemy enemy){
        Character character = game.getCurrentLevel().getCharacter();
        if(enemy.getX() <= character.getX() - (ViewConstants.LEFT_CHARACTER_SPACE + 4))
            enemy.deactivateEnemy();

    }
    
}