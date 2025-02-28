package entities.character.characterStates;
import java.util.HashMap;
import entities.character.Character;
import factories.Sprite;

public abstract class CharacterState {
    protected Character character;
    protected HashMap<String, Sprite> stateSprites;
    protected boolean breakBlocks;

    public CharacterState(Character character, HashMap<String, Sprite> stateSprites, boolean capacityToBreakBlocks) {
        this.character = character;
        this.stateSprites = stateSprites;
        this.breakBlocks = capacityToBreakBlocks;
    }
    public abstract void damaged();
    public HashMap<String, Sprite> getSprites() {
		return stateSprites;
	}
    public abstract int getStarPoints();
    public abstract int getMushroomPoints();
    public abstract int getFireFlowerPoints();
    public  boolean breakBlock() {
    	return breakBlocks;
    }
    public abstract boolean canThrowFireball();       
    public abstract boolean isSuper();
}