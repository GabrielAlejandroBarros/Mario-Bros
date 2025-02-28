package entities.enemies;
import java.util.HashMap;
import entities.character.CharacterVisitor;
import entities.platforms.Platform;
import entities.state.spiny.*;
import factories.Sprite;
import observer.EnemyObserver;
import observer.GraphicObserver;

public class Spiny extends Enemy{		
	static final private int pointsOnDeath=60;
	static final private int pointsOnKill=-30;	
	protected SpinyState spinyActualState;
	protected HashMap<String, SpinyState> spinyStates;	
	
	public Spiny(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, pointsOnDeath, pointsOnKill);
		this.direction = "Left";
		this.flies = false;
		this.spinyStates = getSpinyStates();
		this.spinyActualState = spinyStates.get("Egg");
	}
	
	public void move(){
		switch (direction) {
            case "Left":
                moveLeft();
                break;
            case "Right":
				moveRight();
                break;
            case "None":
            	break;
        }
		observer.update();
	}

	public int getPointsOnDeath() {
		return pointsOnDeath;
	}	
	
	public int getPointsOnKill() {
		return pointsOnKill;
	}
	
	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
	
	public void setObserver(GraphicObserver observer) {
		this.observer = observer;
	}

	public void changeToEggState(){		
		spinyActualState = spinyStates.get("Egg");	
		observer.update();
	}
	
	public void changeToNormalState(){
		spinyActualState = spinyStates.get("Normal");
        Sprite normalSprite = sprites.get("Right1");
        setSprite(normalSprite);
        observer.update();
		((EnemyObserver)observer).updateBoundingBoxSize();
	}
	private HashMap<String, SpinyState> getSpinyStates(){
		HashMap<String, SpinyState> spinyStates = new HashMap<String, SpinyState>();
		spinyStates.put("Normal", new SpinyNormalState(this));
		spinyStates.put("Egg", new SpinyEggState(this));
		return spinyStates;
	}
	
	public void moveRight() {
		spinyActualState.moveRight(spriteNumber);
	}	
	
	public void moveLeft() {
		spinyActualState.moveLeft(spriteNumber);
	}

	public void applyGravity(){
		spinyActualState.applyGravity();		
		observer.update();
	}

	public void visit(Platform platform) {
		spinyActualState.visitPlatform();
	}
}
