package entities.powerUps;
import entities.character.CharacterVisitor;
import factories.Sprite;

public class FireFlower extends PowerUp{
	
	static final private int points = 5;

	public FireFlower(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, points);
	}
	
	public int getPoints() {
		return points;
	}
	
	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
}
