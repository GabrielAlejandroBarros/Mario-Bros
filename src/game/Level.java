package game;
import java.util.List;
import entities.character.Character;
import entities.enemies.Enemy;
import entities.platforms.Platform;
import entities.powerUps.PowerUp;
import entities.projectile.FireBall;
import views.ViewConstants;

import java.util.LinkedList;

public class Level {
    
    protected int remainingTime; 
    protected List<Platform> platforms;
    protected List<Enemy> enemies;
    protected List<PowerUp> powerUps;
    protected LinkedList<FireBall> fireBalls;
    protected Character character;
    protected boolean running;
    protected String background;

    public Level(List<Platform> platforms, List<Enemy> enemies, List<PowerUp> powerUps){
        this.remainingTime = ViewConstants.LEVEL_TIME_DURATION;
        this.platforms = platforms;
        this.enemies = enemies;
        this.powerUps = powerUps;
        this.running = false;
        this.fireBalls = new LinkedList<>() ;
    }

    public void setCharacter(Character character){
        this.character = character;
    }

    public synchronized List<Platform> getPlatforms(){
        return platforms;
    }
    public synchronized List<Enemy> getEnemies(){
        return enemies;
    }
    public synchronized List<PowerUp> getPowerUps(){
        return powerUps;
    }
    public LinkedList<FireBall> getFireBalls(){
        return fireBalls;
    }

    public Character getCharacter(){
        return character;
    }
    public int getRemainingTime(){
        return remainingTime;
    }
    public boolean isRunning(){
        return running;
    }

    public void delete() {
        platforms.removeAll(platforms);
        enemies.removeAll(enemies);
        powerUps.removeAll(powerUps);
        fireBalls.removeAll(fireBalls);
    }

    public void addFireBall(FireBall fireBall) {
        fireBalls.addLast(fireBall);        
    }

    public String getBackground() {
        return background;
    }
    public void setBackground(String background){
        this.background = background;
    }

}