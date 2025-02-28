package entities.visitor;
import entities.character.CharacterVisitor;
public interface VisitedElement {	
	public void acceptVisit(CharacterVisitor visitor);
}