package entities.enemies;
import entities.character.Character;
import entities.character.CharacterVisitor;
import factories.Sprite;
import observer.GraphicObserver;
import tools.GraphicTools;
import views.ViewConstants;

public class Lakitu extends Enemy {
	
	static final private int pointsOnDeath=60;
	static final private int pointsOnKill=0;
	
	protected Character characterReference;
	protected long arrivalTimeMillis;
	protected String characterSide;
	protected boolean waiting;
	protected float travelTime;
	protected boolean canThrowEgg;
	private int eggCooldown;
	
	public Lakitu(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, pointsOnDeath, pointsOnKill);
		this.direction="Left";
		this.flies=true;
		this.characterReference = null;
		this.characterSide = "Left";
		this.horizontalSpeed = ViewConstants.CHARACTER_SPEED;
		this.waiting = false;
		this.travelTime = 0;
		this.canThrowEgg = false;
		this.eggCooldown = ViewConstants.EGG_COOLDOWN;
	}

	public void move(){
		updateCooldown();
		float destinationX = characterSide == "Right" ? characterReference.getX() - 3 : characterReference.getX() + 3;
		if(!isOnDestinationCoords(destinationX)){
			updateHorizontalSpeed();
			changeDirectionToDestination(destinationX);
			super.move();
		}
		else {
			travelTime = 0;
			if(!waiting){
				arrivalTimeMillis = System.currentTimeMillis();
				waiting = true;
			}
			if(checkThrowTime()){
				waiting = false;
				switchSide();
			}
		}
	}

	private void updateCooldown() {
		if(eggCooldown >= ViewConstants.EGG_COOLDOWN){
			canThrowEgg = true;
			eggCooldown = 0;
		}
		else{	
			eggCooldown += ViewConstants.GAMETICK;
			canThrowEgg = false;
		}
	}		

	private void changeDirectionToDestination(float destinationX){
		if(destinationX > positionInX)
			direction = "Right";
		else if(destinationX < positionInX)
			direction = "Left";
		else if(isOnDestinationCoords(destinationX))
			direction = "None";
	}

	private boolean isOnDestinationCoords(float destinationX){
		return positionInX - horizontalSpeed <= destinationX && destinationX <= positionInX + horizontalSpeed;
	}

	private boolean checkThrowTime(){
		return System.currentTimeMillis() - arrivalTimeMillis >= 3000;
	}

	private void switchSide(){
		characterSide = characterSide == "Left" ? "Right" : "Left";
	}

	private void updateHorizontalSpeed(){
		travelTime += travelTime < 1 ? 1/26f : 0;
		horizontalSpeed =  ViewConstants.CHARACTER_SPEED + 0.08f * travelTime * travelTime * travelTime;		
	}

	public void moveRight() {
		float enemyX=getX();
		setX(GraphicTools.round2Digits(enemyX + horizontalSpeed));
		setSprite(sprites.get("Right"));
		observer.update();
	}
	
	public void moveLeft() {
		float enemyX=getX();
		setX(GraphicTools.round2Digits(enemyX - horizontalSpeed));
		setSprite(sprites.get("Left"));
		observer.update();
	}

	public void setCharacterReference(Character character){
		this.characterReference = character;
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

	public boolean canThrowEgg(){
		return canThrowEgg;
	}
	
}
