package entities.enemies;
import entities.character.CharacterVisitor;
import entities.platforms.Platform;
import factories.Sprite;
import observer.GraphicObserver;
import tools.GraphicTools;

public class Goomba extends Enemy{

	static final private int pointsOnDeath=60;
	static final private int pointsOnKill=-30;
	
	public Goomba(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY,pointsOnDeath,pointsOnKill);
		direction="Left";
		flies=false;
	}

	public void moveRight() {
		float enemyX=getX();
		setX(GraphicTools.round2Digits(enemyX + horizontalSpeed));
		setSprite(sprites.get("Walking" + spriteNumber));
		observer.update();
	}
	
	public void moveLeft() {
		float enemyX=getX();
		setX(GraphicTools.round2Digits(enemyX - horizontalSpeed));
		setSprite(sprites.get("Walking" + spriteNumber));
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

	@Override
	public void visit(Platform platform) {
		
	}
	
}
