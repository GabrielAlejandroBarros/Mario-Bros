package entities.platforms;

import entities.character.CharacterVisitor;
import factories.Sprite;

public class PipeTopRight extends Pipe {
	
	public PipeTopRight(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY);
	}

	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }

}
