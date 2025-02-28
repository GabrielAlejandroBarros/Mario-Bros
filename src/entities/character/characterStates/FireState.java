package entities.character.characterStates;
import java.util.HashMap;

import entities.character.Character;
import factories.Sprite;

public class FireState extends SuperState{
	
	public FireState(Character character, HashMap<String, Sprite> stateSprites) {
		super(character, stateSprites);
	}
	
	public HashMap<String, Sprite>  getSprites(){
		HashMap<String, Sprite> sprites;
		if(character.isInvencible()){
			sprites = character.getSuperInvencibleSprites();
		}
		else 
			sprites = stateSprites;
		return sprites;
	}
	
	public int getFireFlowerPoints() {
		return 35;
	}

	public boolean canThrowFireball(){
		return true;
	}
}
