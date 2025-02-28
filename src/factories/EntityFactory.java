package factories;
import entities.character.Character;
import entities.character.characterStates.CharacterState;
import entities.character.characterStates.FireState;
import entities.character.characterStates.NormalState;
import entities.character.characterStates.SuperState;
import entities.enemies.*;
import entities.platforms.*;
import entities.powerUps.*;
import entities.projectile.FireBall;
import entities.state.question.Question;
import entities.state.question.QuestionBlockEmpty;
import entities.state.question.QuestionState;
import entities.state.question.WithCoin;
import entities.state.question.WithPowerUp;

import java.util.HashMap;


public class EntityFactory {
	
	protected SpriteFactory spriteFactory;
	
	public EntityFactory(String mode) {
		if( mode.toUpperCase().equals("Custom".toUpperCase())) {
		   this.spriteFactory= new Custom(mode);
		}
		else this.spriteFactory= new Original(mode);
	}
	
	public Enemy newEnemy(int type, int worldX, int worldY) {
	  	Enemy enemy = null;
	  	switch (type) {
	         case 2:	enemy = newGoomba(worldX,worldY);
					   	break;  
	         case 3:    enemy = newKoopaTroopa(worldX,worldY);
						break;
	         case 4:    enemy = newPiranhaPlant(worldX,worldY);
						break;
	         case 5:    enemy = newLakitu(worldX,worldY);
						break;
	         case 7:    enemy = newBuzzyBeetle(worldX,worldY);
						break;
	  	}
		return enemy;
	}
	
	public Question newPowerUpWithQuestionBlock(int type, int worldX, int worldY) {
		Question question= newQuestion(worldX, worldY - 1);
		PowerUp powerUp = null;
		switch (type){
		    case 10:	powerUp = newSuperMushroom(worldX,worldY);
		                question.setPowerUp(powerUp);  
						break;
		    case 11:	powerUp = newFireFlower(worldX,worldY);
		                question.setPowerUp(powerUp);
						break;
		    case 12:	powerUp = newStar(worldX,worldY);
		                question.setPowerUp(powerUp);
						break;						
		    case 13:	powerUp = newGreenMushroom(worldX,worldY);
		                question.setPowerUp(powerUp);		    
						break;						
		    case 14:	Coin coin = newCoin(worldX,worldY);
		                question.setPowerUp(coin);
						break;						
		}
		return question;
	}
	
	public Coin newOnlyCoin(int worldX, int worldY) {
		Coin coin= newCoin(worldX, worldY);
		coin.activate();
		return coin;
	}
	
	public Platform newPlatform(int type, int worldX, int worldY) {
		Platform platform=null;
		switch (type){
		    case 21:	platform = newBlock(worldX,worldY);
						break; 
		    case 22: 	platform = newBrick(worldX,worldY);
						break;	                     
		    case 23:  	platform = newVoid(worldX,worldY);
						break;
		    case 24:	platform = newFlag(worldX,worldY);
						break;         
			case 25:	platform = newMast(worldX,worldY);
						break;  
			case 26:	platform = newMastEnd(worldX,worldY);
						break;
			case 27:	platform = newPipeBottomLeft(worldX,worldY);
						break;
			case 28:	platform = newPipeBottomRight(worldX,worldY);
						break;
			case 29:	platform = newPipeTopLeft(worldX,worldY);
						break;	                     
		    case 30:	platform = newPipeTopRight(worldX,worldY);
						break;
		}
		return platform;
	}

	

	public Character createCharacter(){
        Character character = new Character(spriteFactory.getCharacterStillSprite("Right"));
		HashMap<String,CharacterState> characterStates = createStates(character);
		character.setCharacterStates(characterStates);

		
		return character;
	}
	
	private HashMap<String,CharacterState> createStates(Character character){
		CharacterState normal = new NormalState (character, characterNormalSprites());
		CharacterState supers = new SuperState (character, characterSuperSprites());
		CharacterState fire = new FireState (character, characterFireSprites());
		HashMap<String,CharacterState> characterStates =  new HashMap<String,CharacterState>();
		characterStates.put("Normal",normal);
		characterStates.put("Super", supers);
		characterStates.put("Fire", fire);
		character.setNormalInvencibleSprites(characterInvencibleSprites());
		character.setSuperInvencibleSprites(characterSuperInvencibleSprites());
		return characterStates;
	}

	private HashMap<String, Sprite> characterNormalSprites() {
		HashMap<String,Sprite> characterSprites = new HashMap<String,Sprite>();
		characterSprites.put("StillLeft",spriteFactory.getCharacterStillSprite("Left"));
		characterSprites.put("StillRight",spriteFactory.getCharacterStillSprite("Right"));
		characterSprites.put("Left1",spriteFactory.getCharacterLeftSprite(1));
		characterSprites.put("Left2", spriteFactory.getCharacterLeftSprite(2));
		characterSprites.put("Left3",spriteFactory.getCharacterLeftSprite(3));
		characterSprites.put("Right1",spriteFactory.getCharacterRightSprite(1));
		characterSprites.put("Right2", spriteFactory.getCharacterRightSprite(2));
		characterSprites.put("Right3",spriteFactory.getCharacterRightSprite(3));
		characterSprites.put("JumpingRight",spriteFactory.getCharacterJumpingSprite("Right"));
		characterSprites.put("JumpingLeft",spriteFactory.getCharacterJumpingSprite("Left"));
		characterSprites.put("InFlag",spriteFactory.getCharacterInFlag());
		characterSprites.put("Died", spriteFactory.getCharacterDead());
		return characterSprites;
	}

	private HashMap<String, Sprite> characterSuperSprites() {
		HashMap<String,Sprite> characterSuperSprites = new HashMap<String,Sprite>();
		characterSuperSprites.put("StillLeft",spriteFactory.getSuperCharacterStillSprite("Left"));
		characterSuperSprites.put("StillRight",spriteFactory.getSuperCharacterStillSprite("Right"));
		characterSuperSprites.put("Left1",spriteFactory.getSuperCharacterLeftSprite(1));
		characterSuperSprites.put("Left2", spriteFactory.getSuperCharacterLeftSprite(2));
		characterSuperSprites.put("Left3",spriteFactory.getSuperCharacterLeftSprite(3));
		characterSuperSprites.put("Right1",spriteFactory.getSuperCharacterRightSprite(1));
		characterSuperSprites.put("Right2", spriteFactory.getSuperCharacterRightSprite(2));
		characterSuperSprites.put("Right3",spriteFactory.getSuperCharacterRightSprite(3));
		characterSuperSprites.put("JumpingRight",spriteFactory.getSuperCharacterJumpingSprite("Right"));
		characterSuperSprites.put("JumpingLeft",spriteFactory.getSuperCharacterJumpingSprite("Left"));
		characterSuperSprites.put("InFlag",spriteFactory.getSuperCharacterInFlag());
		characterSuperSprites.put("Died", spriteFactory.getSuperCharacterDead());
		return characterSuperSprites;
	}
	
	private HashMap<String, Sprite> characterFireSprites() {
		HashMap<String,Sprite> characterFireSprites = new HashMap<String,Sprite>();
		characterFireSprites.put("StillLeft",spriteFactory.getFireCharacterStillSprite("Left"));
		characterFireSprites.put("StillRight",spriteFactory.getFireCharacterStillSprite("Right"));
		characterFireSprites.put("Left1",spriteFactory.getFireCharacterLeftSprite(1));
		characterFireSprites.put("Left2", spriteFactory.getFireCharacterLeftSprite(2));
		characterFireSprites.put("Left3",spriteFactory.getFireCharacterLeftSprite(3));
		characterFireSprites.put("Right1",spriteFactory.getFireCharacterRightSprite(1));
		characterFireSprites.put("Right2", spriteFactory.getFireCharacterRightSprite(2));
		characterFireSprites.put("Right3",spriteFactory.getFireCharacterRightSprite(3));
		characterFireSprites.put("JumpingRight",spriteFactory.getFireCharacterJumpingSprite("Right"));
		characterFireSprites.put("JumpingLeft",spriteFactory.getFireCharacterJumpingSprite("Left"));
		characterFireSprites.put("InFlag",spriteFactory.getFireCharacterInFlag());		
		characterFireSprites.put("Died", spriteFactory.getFireCharacterDead());
		return characterFireSprites;
	}
	
	private HashMap<String, Sprite> characterInvencibleSprites() {
		HashMap<String,Sprite> characterSprites = new HashMap<String,Sprite>();
		characterSprites.put("StillLeft",spriteFactory.		getCharacterInvencibleStillSprite("Left"));
		characterSprites.put("StillRight",spriteFactory.	getCharacterInvencibleStillSprite("Right"));
		characterSprites.put("Left1",spriteFactory.			getCharacterInvencibleLeftSprite(1));
		characterSprites.put("Left2", spriteFactory.		getCharacterInvencibleLeftSprite(2));
		characterSprites.put("Left3",spriteFactory.			getCharacterInvencibleLeftSprite(3));
		characterSprites.put("Right1",spriteFactory.		getCharacterInvencibleRightSprite(1));
		characterSprites.put("Right2", spriteFactory.		getCharacterInvencibleRightSprite(2));
		characterSprites.put("Right3",spriteFactory.		getCharacterInvencibleRightSprite(3));
		characterSprites.put("JumpingRight",spriteFactory.	getCharacterInvencibleJumpingSprite("Right"));
		characterSprites.put("JumpingLeft",spriteFactory.	getCharacterInvencibleJumpingSprite("Left"));
		characterSprites.put("InFlag",spriteFactory.getCharacterInFlag());
		characterSprites.put("Died", spriteFactory.getCharacterDead());
		return characterSprites;
	}

	private HashMap<String, Sprite> characterSuperInvencibleSprites() {
		HashMap<String,Sprite> characterSuperSprites = new HashMap<String,Sprite>();
		characterSuperSprites.put("StillLeft",spriteFactory.	getSuperCharacterInvencibleStillSprite("Left"));
		characterSuperSprites.put("StillRight",spriteFactory.	getSuperCharacterInvencibleStillSprite("Right"));
		characterSuperSprites.put("Left1",spriteFactory.		getSuperCharacterInvencibleLeftSprite(1));
		characterSuperSprites.put("Left2", spriteFactory.		getSuperCharacterInvencibleLeftSprite(2));
		characterSuperSprites.put("Left3",spriteFactory.		getSuperCharacterInvencibleLeftSprite(3));
		characterSuperSprites.put("Right1",spriteFactory.		getSuperCharacterInvencibleRightSprite(1));
		characterSuperSprites.put("Right2", spriteFactory.		getSuperCharacterInvencibleRightSprite(2));
		characterSuperSprites.put("Right3",spriteFactory.		getSuperCharacterInvencibleRightSprite(3));
		characterSuperSprites.put("JumpingRight",spriteFactory.	getSuperCharacterInvencibleJumpingSprite("Right"));
		characterSuperSprites.put("JumpingLeft",spriteFactory.	getSuperCharacterInvencibleJumpingSprite("Left"));
		characterSuperSprites.put("InFlag",spriteFactory.getSuperCharacterInFlag());
		characterSuperSprites.put("Died", spriteFactory.getSuperCharacterDead());
		return characterSuperSprites;
	}	
	
	private HashMap<String, QuestionState> createQuestionStates(Question question) {
		HashMap<String, QuestionState> states= new HashMap<String, QuestionState>();
		Sprite sprite= spriteFactory.getQuestionBlockSprite();
	    WithCoin stateWithCoin=  new WithCoin(sprite);
	    WithPowerUp stateWithOrtherPowerUp= new WithPowerUp(sprite);
	    QuestionBlockEmpty emptyQuestionState= new QuestionBlockEmpty(spriteFactory.getQuestionEmptyBlock());
		stateWithCoin.setQuestionBlock(question);
		stateWithOrtherPowerUp.setQuestionBlock(question);
		emptyQuestionState.setQuestionBlock(question);
	    states.put("WithCoin", stateWithCoin);
		states.put("WithOtherPowerUp", stateWithOrtherPowerUp);
		states.put("EmptyQuestion", emptyQuestionState);
		return states;
	}
	
	private SuperMushroom newSuperMushroom(int worldX, int worldY) {
		SuperMushroom superMushroom = new SuperMushroom(spriteFactory.getSuperMushroomSprite(),worldX,worldY);
	    return superMushroom;
	}
    
	private GreenMushroom newGreenMushroom(int worldX, int worldY) {
	    GreenMushroom greenMushroom = new GreenMushroom(spriteFactory.getGreenMushroomSprite(),worldX,worldY);
	    return greenMushroom;                   
    }
	
	private Star newStar(int worldX, int worldY) {
	    Star star = new Star(spriteFactory.getStarSprite(),worldX,worldY);
	    return star;
	}
	
	private Coin newCoin(int worldX, int worldY) {
	    Coin coin = new Coin(spriteFactory.getCoinSprite(),worldX,worldY );
	    return coin;
	}
	
	private FireFlower newFireFlower(int worldX, int worldY) {
		FireFlower flower = new FireFlower(spriteFactory.getFireFlowerSprite(), worldX,worldY);
	    return flower;
	}
	
	private KoopaTroopa newKoopaTroopa(int worldX, int worldY) {
	    KoopaTroopa koopa = new KoopaTroopa(spriteFactory.getKoopaTroopaLeftSprite(1),worldX,worldY);
		koopa.setSpritesMap(getKoopaTroopaSprites());
	    return koopa;
	}
	private HashMap<String, Sprite> getKoopaTroopaSprites(){
		HashMap<String,Sprite> koopaTroopaSprites = new HashMap<String,Sprite>();
		koopaTroopaSprites.put("Left1", spriteFactory.getKoopaTroopaLeftSprite(1));
		koopaTroopaSprites.put("Left2", spriteFactory.getKoopaTroopaLeftSprite(2));
		koopaTroopaSprites.put("Right1", spriteFactory.getKoopaTroopaRightSprite(1));
		koopaTroopaSprites.put("Right2", spriteFactory.getKoopaTroopaRightSprite(2));
		koopaTroopaSprites.put("Shell", spriteFactory.getKoopaTroopaShellSprite());
		return koopaTroopaSprites;
	}

	private BuzzyBeetle newBuzzyBeetle(int worldX, int worldY) {
		BuzzyBeetle beetle = new BuzzyBeetle(spriteFactory.getBuzzyBeetleLeftSprite(1),worldX,worldY);
		beetle.setSpritesMap(getBuzzyBeetleSprites());
	    return beetle;
	}
	private HashMap<String, Sprite> getBuzzyBeetleSprites(){
		HashMap<String,Sprite> buzzyBeetleSprites = new HashMap<String,Sprite>();
		buzzyBeetleSprites.put("Left1", spriteFactory.getBuzzyBeetleLeftSprite(1));
		buzzyBeetleSprites.put("Left2", spriteFactory.getBuzzyBeetleLeftSprite(2));
		buzzyBeetleSprites.put("Right1", spriteFactory.getBuzzyBeetleRightSprite(1));
		buzzyBeetleSprites.put("Right2", spriteFactory.getBuzzyBeetleRightSprite(2));
		buzzyBeetleSprites.put("Shell", spriteFactory.getBuzzyBeetleShellSprite());
		return buzzyBeetleSprites;
	}

	private Lakitu newLakitu(int worldX, int worldY) {
		Lakitu lakitu = new Lakitu(spriteFactory.getLakituLeftSprite(),worldX,worldY);
		lakitu.setSpritesMap(getLakituSprites());
	    return lakitu;
	}
	private HashMap<String, Sprite> getLakituSprites(){
		HashMap<String,Sprite> lakituSprites = new HashMap<String,Sprite>();
		lakituSprites.put("Left", spriteFactory.getLakituLeftSprite());
		lakituSprites.put("Right", spriteFactory.getLakituRightSprite());
		return lakituSprites;
	}

	private PiranhaPlant newPiranhaPlant(int worldX, int worldY) {
		PiranhaPlant piranha = new PiranhaPlant(spriteFactory.getPiranhaPlantSprite(1),worldX ,worldY );
		piranha.setSpritesMap(getPiranhaPlantSprites());
	    return piranha;
	}
	private HashMap<String, Sprite> getPiranhaPlantSprites(){
		HashMap<String,Sprite> piranhaPlantSprites = new HashMap<String,Sprite>();
		piranhaPlantSprites.put("PiranhaPlant1", spriteFactory.getPiranhaPlantSprite(1));
		piranhaPlantSprites.put("PiranhaPlant2", spriteFactory.getPiranhaPlantSprite(2));
		return piranhaPlantSprites;
	}

	private Goomba newGoomba(int worldX, int worldY) {
		Goomba goomba = new Goomba(spriteFactory.getGoombaLeftSprite(), worldX,worldY);
		goomba.setSpritesMap(getGoombaSprites());
	    return goomba;
	}
	private HashMap<String, Sprite> getGoombaSprites(){
		HashMap<String,Sprite> goombaSprites = new HashMap<String,Sprite>();
		goombaSprites.put("Walking1", spriteFactory.getGoombaLeftSprite());
		goombaSprites.put("Walking2", spriteFactory.getGoombaRightSprite());
		return goombaSprites;
	}
	public Spiny getNewSpiny(int worldX, int worldY){
        Spiny spiny = new Spiny(spriteFactory.getSpinyEggSprite(), worldX, worldY);
		spiny.setSpritesMap(getSpinySprites());
		return spiny;
	}	
	private HashMap<String, Sprite> getSpinySprites(){
		HashMap<String,Sprite> spinySprites = new HashMap<String,Sprite>();
		spinySprites.put("Left1",  spriteFactory.getSpinyLeftSprite(1));
		spinySprites.put("Left2",  spriteFactory.getSpinyLeftSprite(2));
		spinySprites.put("Right1", spriteFactory.getSpinyRightSprite(1));
		spinySprites.put("Right2", spriteFactory.getSpinyRightSprite(2));
		spinySprites.put("Egg", spriteFactory.getSpinyEggSprite());
		return spinySprites;
	}
  
	private Pipe newPipeTopLeft(int worldX, int worldY) {
		Pipe pipe = new PipeTopLeft(spriteFactory.getPipeTopLeftSprite(),worldX,worldY);
	    return pipe;
	}
	private Pipe newPipeTopRight(int worldX, int worldY) {
		Pipe pipe = new PipeTopRight(spriteFactory.getPipeTopRightSprite(),worldX,worldY);
	    return pipe;
	}
	private Pipe newPipeBottomLeft(int worldX, int worldY) {
		Pipe pipe = new PipeBottomLeft(spriteFactory.getPipeBottomLeftSprite(),worldX,worldY);
	    return pipe;
	}
	private Pipe newPipeBottomRight(int worldX, int worldY) {
		Pipe pipe = new PipeBottomRight(spriteFactory.getPipeBottomRightSprite(),worldX,worldY);
	    return pipe;
	}
	
	private VoidBlock newVoid(int worldX, int worldY) {
		VoidBlock gameVoid = new VoidBlock(spriteFactory.getVoidSprite(), worldX,worldY);
	    return gameVoid;
	}
	
	private Flag newFlag(int worldX, int worldY) {
		Flag flag = new Flag(spriteFactory.getFlagSprite(), worldX,worldY);
	    return flag;
	}
	
	private Question newQuestion(int worldX, int worldY) {
		Question question = new Question(spriteFactory.getQuestionBlockSprite(), worldX, worldY, getCoinAnimationSprites());
		HashMap<String, QuestionState> states= createQuestionStates(question);
		question.setStates(states);
	    return question;
	}
	private HashMap<String,Sprite> getCoinAnimationSprites(){
		HashMap<String,Sprite> coinSprites = new HashMap<String,Sprite>();
		for(int i = 0; i<4; i++){
			coinSprites.put(""+(i+1),spriteFactory.getCoinAnimationSprite(i+1));
		}
		return coinSprites;

	}

	private Brick newBrick(int worldX, int worldY) {
		Brick brick = new Brick(spriteFactory.getBrickSprite(),worldX,worldY);
	    return brick;
	}
	
	private Block newBlock(int worldX, int worldY) {
		Block block = new Block(spriteFactory.getBlockSprite(),worldX,worldY);
	    return block;
	}
	private Mast newMast(int worldX, int worldY) {
		Mast mast = new Mast(spriteFactory.getMastSprite(),worldX,worldY);
	    return mast;
	}
	private MastEnd newMastEnd(int worldX, int worldY) {
		MastEnd mast = new MastEnd(spriteFactory.getMastEndSprite(),worldX,worldY);
	    return mast;
	}

    public FireBall newFireBall(int x, int y, String direction) {
		return new FireBall(spriteFactory.getFireballSprite(1), x, y, direction, getFireBallSprites());		
    }
	
	private HashMap<String,Sprite> getFireBallSprites(){
		HashMap<String,Sprite> fireBallSprites = new HashMap<String,Sprite>();
		for(int i = 0; i<4; i++){
			fireBallSprites.put(""+(i+1),spriteFactory.getFireballSprite(i+1));
		}
		fireBallSprites.put("blow1", spriteFactory.getFireBallExplotionSprite(1));
		fireBallSprites.put("blow2", spriteFactory.getFireBallExplotionSprite(2));
		fireBallSprites.put("blow3", spriteFactory.getFireBallExplotionSprite(3));
		return fireBallSprites;
	}

    public String createBackground(int levelNumber) {
		return spriteFactory.getLevelBackground(levelNumber);
    }
}