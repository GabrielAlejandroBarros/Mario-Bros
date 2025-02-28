package entities.platforms;

import entities.character.CharacterVisitor;
import factories.Sprite;

public class PipeBottomLeft extends Pipe {
	
	public PipeBottomLeft(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY);
	}

	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }

}
