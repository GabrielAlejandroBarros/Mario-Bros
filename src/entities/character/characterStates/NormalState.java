package entities.character.characterStates;
import java.util.HashMap;

import entities.character.Character;
import factories.Sprite;

public class NormalState extends CharacterState{
		
	public NormalState(Character character, HashMap<String, Sprite> stateSprites ) {
		super(character, stateSprites, false);
	}

	public void damaged() {
		character.dead();
	}
	
	public HashMap<String, Sprite> getSprites() {
		HashMap<String, Sprite> sprites;
		if(character.isInvencible())
			sprites = character.getNormalInvencibleSprites();
		else 
			sprites = stateSprites;
		return sprites;
	}

	public int getStarPoints() {
		return 20;
	}

	
	public int getMushroomPoints() {
		return 10;
	}

	public int getFireFlowerPoints() {
		return 5;
	}
	public boolean isSuper(){
		return false;
	}

	
	public boolean canThrowFireball() {
		return false;
	}
	
}
