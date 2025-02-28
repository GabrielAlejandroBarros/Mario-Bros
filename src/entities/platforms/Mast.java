package entities.platforms;

import entities.character.CharacterVisitor;
import factories.Sprite;

public class Mast extends Platform{
    	static final private boolean isBreakeable = false;

	public Mast(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, isBreakeable);
	}

	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
}
