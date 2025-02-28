package factories;
import java.util.LinkedList;
import java.util.List;
import entities.character.Character;
import entities.enemies.*;
import entities.platforms.Platform;
import entities.powerUps.PowerUp;
import entities.projectile.FireBall;
import entities.state.question.Question;
import game.Level;
import views.ViewConstants;

public class LevelGenerator {
    protected EntityFactory entityFactory;
    protected Parser parser;
    protected int levelNumber;

    public LevelGenerator(String mode) {
        this.entityFactory= new EntityFactory(mode);
        this.parser= new Parser();
    }

    public Level createLevel(int number, Character character){
        this.levelNumber = number;
        parser.setLevel(levelNumber);
        Character characterReference = character == null ? entityFactory.createCharacter() : character;
        List<Platform> platforms = new LinkedList<Platform>();
        List<PowerUp> powerUps = new LinkedList<PowerUp>();
        List<Enemy> enemies = new LinkedList<Enemy>();
        while(parser.hasToRead()) {
            generateEntity(characterReference,platforms, enemies, powerUps);     
        } 
        Level levelGenerate= new Level(platforms, enemies, powerUps);  
        levelGenerate.setCharacter(characterReference);  
        levelGenerate.setBackground(entityFactory.createBackground(levelNumber));
        return levelGenerate;
    }

    private void generateEntity(Character charater,List<Platform> platforms,List<Enemy> enemies,List<PowerUp> powerUps){    
        int type= parser.getType();
        int worldX = parser.getPositionX();
        int worldY = parser.getPositionY();
        if( type>1 && type<9 ) {
            Enemy enemy= entityFactory.newEnemy(type, worldX, worldY);
            if(type == 5){
                Lakitu lakitu=(Lakitu) enemy;
                lakitu.setCharacterReference(charater);
            }
            enemies.add(enemy);
        }
        if( type>9 && type<15) { 
            Question questionBlock = entityFactory.newPowerUpWithQuestionBlock(type, worldX, worldY);
            powerUps.add(questionBlock.getPowerUp());
            platforms.add(questionBlock);
        }
        if(type == 15) {
            PowerUp powerUp= entityFactory.newOnlyCoin(worldX, worldY);
            powerUps.add(powerUp);
        } 
        if( type>19 && type<=30) { 
           Platform platform= entityFactory.newPlatform(type, worldX, worldY);
           platforms.add(platform);
        }       
    }

    public Character createCharacter(){
        return entityFactory.createCharacter();
    }

    public Level getNextLevel(Character previousCharacter) {
        this.levelNumber = levelNumber + 1;
        Level level = null;
        if(levelNumber <= ViewConstants.MAX_LEVELS)
            level = createLevel(levelNumber, previousCharacter);
        return level;
    }

    public FireBall createFireBall(int x, int y, String direction) {
        return entityFactory.newFireBall(x, y, direction);
    }

    public boolean haveNextLevel(){
        return levelNumber+1 <= ViewConstants.MAX_LEVELS;
    }

    public Spiny getNewSpinny(int x, int y) {
        return entityFactory.getNewSpiny(x, y);
    }
}