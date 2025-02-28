package entities.enemies;
import entities.character.CharacterVisitor;
import factories.Sprite;
import observer.GraphicObserver;
import tools.GraphicTools;

public class PiranhaPlant extends Enemy{

	static final private int pointsOnDeath=30;
	static final private int pointsOnKill=-30;
	protected final float spawnY;
	protected long stopTime;
	protected boolean insidePipe;
	
	public PiranhaPlant(Sprite sprite, float positionInX, float positionInY) {
		super(sprite, positionInX + 0.5f, positionInY - 67/43f, pointsOnDeath, pointsOnKill);
		spawnY = positionInY - 74/43f;
		direction="Up";
		flies=false;
		insidePipe = false;
	}
	
	public void move(){
		long currentTime = System.currentTimeMillis();
		if(currentTime - stopTime >= 2000){
			switch (direction) {
				case "Up":
					moveUp();
					break;
				case "Down":
					moveDown();
					break;
				case "None":
					break;
			}
			checkDirectionChange();
		}	
		setSprite(sprites.get("PiranhaPlant" + spriteNumber));
		observer.update();
	}

	public void moveUp(){
		float enemyY = getY();
		setY(GraphicTools.round2Digits(enemyY + horizontalSpeed));
	}

	public void moveDown(){
		float enemyY = getY();
		setY(GraphicTools.round2Digits(enemyY - horizontalSpeed));
	}

	private void checkDirectionChange(){
		String newDirection = getDirection() == "Up" ? "Down" : "Up";
		if(getY() <= spawnY || getY() >= spawnY + getHeight()){
			if(getY() <= spawnY)
				insidePipe = true;
			else
				insidePipe = false;
			direction = newDirection;
			stopTime = System.currentTimeMillis();
		}
	}
	
	public void applyGravity() {
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

	public boolean isOnPipe(){
		return insidePipe;
	}

}
