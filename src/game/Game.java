package game;
import java.util.Collection;
import java.util.List;
import entities.LogicalEntity;
import entities.character.Character;
import entities.character.CharacterThread;
import entities.enemies.Enemy;
import entities.enemies.EnemyThread;
import entities.enemies.Spiny;
import entities.platforms.Platform;
import entities.powerUps.PowerUp;
import entities.projectile.FireBall;
import entities.projectile.Projectile;
import factories.LevelGenerator;
import factories.SoundGenerator;
import observer.GraphicObserver;
import observer.SoundObserver;
import ranking.Ranking;
import views.ViewController;

public class Game {
    protected LevelGenerator levelGenerator;
    protected ViewController viewController;
    protected Level currentLevel;
    protected String currentPlayerName;
    protected int numberLevel;
    protected SoundReproducer soundReproducer;
    protected CharacterThread characterThread;
    protected SoundGenerator generatorSounds;
    protected EnemyThread enemyThread;
    protected Ranking ranking;
    protected String mode;


    public Game () {
        this.numberLevel = 1;
        this.ranking = new Ranking();
    } 
    
    public void setName(String name){
        this.currentPlayerName = name;
    }
    
    public void setViewController(ViewController viewController){
        this.viewController = viewController;       
    }

    public void start(){
        setObservers();
        this.enemyThread = new EnemyThread(this);
        this.characterThread = new CharacterThread(viewController.getKeyboard(), this);
        characterThread.start();
        enemyThread.start();
        viewController.showLevelScreen();
        startLevelMusic();
    }

    public void stop(){          
        boolean enterInRanking= ranking.addToRank(currentPlayerName, getCurrentLevel().getCharacter().getScore());
        viewController.clearLevelScreen();
        enemyThread.setIsRunning(false);
        viewController.showGameOverScreen();
        waitMusic();
        if(enterInRanking)
            viewController.showRankingScreen();
        else 
            viewController.showMenuScreen();
    }

    protected void setLevel(int number){
        currentLevel = levelGenerator.createLevel(number, null);
    }
    
    public synchronized Level getCurrentLevel() {
        return currentLevel;
    }
    
    public ViewController getViewController(){
        return viewController;
    }
    
    public void setObservers(){
        setCharacterObserver(currentLevel.getCharacter());
        setPlatformsObservers(currentLevel.getPlatforms());
        setPowerUpsObservers(currentLevel.getPowerUps());
        setEnemiesObservers(currentLevel.getEnemies());
    }

    protected void setCharacterObserver(Character character){
        GraphicObserver characterObserver = viewController.registerEntity(character);
        character.registerObserver(characterObserver);
        setCharacterObserverOfSound(character);
    }  
    
    private void setCharacterObserverOfSound(Character character) {
		SoundObserver observer= new SoundObserver(this);
		character.setObserverOfSound(observer);
	}
	protected void setPlatformsObservers(List<Platform> platformsList) {
    	for (Platform platform: platformsList) {
            GraphicObserver platformObserver = viewController.registerEntity(platform, true);
    		platform.registerObserver(platformObserver);
    	}
    }
    
   protected void setEnemiesObservers(List<Enemy> enemyList) {
    	for (Enemy enemy: enemyList) {
    		GraphicObserver enemyObserver = viewController.registerEntity(enemy);
    		enemy.registerObserver(enemyObserver);
    	}
    }
    
    protected void setPowerUpsObservers(List<PowerUp> powerUpList) {
    	for (PowerUp powerUp: powerUpList){
    		GraphicObserver powerUpObserver = viewController.registerEntity(powerUp, powerUp.isActive());
    		powerUp.registerObserver(powerUpObserver);
    	}
        
    }
    
    public void removeLogicalEntity(LogicalEntity entity) {
        viewController.removeLogicalEntity(entity);
    }

    public void removeLogicalEntity(Platform entity){
        viewController.removeLogicalEntity(entity);
        characterThread.resetPlatformsByCoords();
        enemyThread.resetPlatformsByCoords();
    }

    public void playNextLevel() {
        changeLevel();
    }
    
    public void reproduceSound(String path) {
    	soundReproducer.setAuxiliarSound(path);
		soundReproducer.start();
    }

    public void reproduceSoundDeath(String path) {
    	reproduceSound(path);
    }

    public void startLevelMusic(){
        soundReproducer.setMusicSound("musicLevel1");
        soundReproducer.loop(-1);
    }
    
    protected void changeLevel() {  
        viewController.clearLevelScreen();
        if(levelGenerator.haveNextLevel()){
            this.characterThread.setIsRunning(false);
            this.enemyThread.setIsRunning(false);
            waitMusic();
            viewController.showLevelScreen();
            Character currentCharacter = resetCharacter();
            currentLevel = levelGenerator.getNextLevel(currentCharacter); 
            viewController.setBackgroundAndScroll();
            currentLevel.setCharacter(currentCharacter);
            currentCharacter.setIsBusy(true);
            setObservers();
            this.characterThread = new CharacterThread(viewController.getKeyboard(), this);
            this.enemyThread = new EnemyThread(this);
            startLevelMusic();
            characterThread.start();
            enemyThread.start();
            currentCharacter.setIsBusy(false);
        }
        else{      
            boolean enterInRanking = ranking.addToRank(currentPlayerName, getCurrentLevel().getCharacter().getScore());
            viewController.clearLevelScreen();
            enemyThread.setIsRunning(false);
            if(enterInRanking)  
                viewController.showRankingScreen();
            else viewController.showMenuScreen();
        }
    }


    private void waitMusic(){
        while(soundReproducer.isRunning()){
        }
    }

    private Character resetCharacter(){
        Character character = currentLevel.getCharacter();
        character.setInStart();
        character.setInEnd(false);
        character.setIsInAir(false);
        return character;
    }

    public void updateInformation(int score, int coins, int timer, int lives) {
        viewController.updateInformation(coins, score, timer, lives);
    }
    
    public Collection<String> getRankingPlayers() {
        return ranking.getPlayers();
    }
    
    public void setMode(String mode) {
        this.mode = mode;
        this.levelGenerator = new LevelGenerator(mode); 
        this.generatorSounds= new SoundGenerator(mode);
        this.soundReproducer = new SoundReproducer(generatorSounds); 
        setLevel(1);         
    }

    public void createFireBall(int x, int y, String direction) {
        FireBall fireBall = levelGenerator.createFireBall(x,y,direction);
        currentLevel.addFireBall(fireBall);
        setProjectilesObservers(fireBall);
    }

    protected void setProjectilesObservers(Projectile projectile){
        GraphicObserver projectileOberver = viewController.registerEntity(projectile,true);
        projectile.registerObserver(projectileOberver);   
    }



    public void reproduceLoopSound(String path, int iterations) {
        soundReproducer.setMusicSound(path);
        soundReproducer.loop(iterations);
    } 

    public void startMusic(String path){
        soundReproducer.setMusicSound("path");
        soundReproducer.loop(0);
    }

    public boolean isRunningSound() {
        return soundReproducer.isRunning();
    }

    public void createEgg(int x, int y) {
        Spiny spinny = levelGenerator.getNewSpinny(x,y);
        currentLevel.getEnemies().add(spinny);
    	GraphicObserver enemyObserver = viewController.registerEntity(spinny);
    	spinny.registerObserver(enemyObserver);  
        reproduceSound("lakitu");      
    }
}