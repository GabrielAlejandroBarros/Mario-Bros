package factories;
import java.io.File;
import java.util.HashMap;
public class SoundGenerator {
	protected HashMap<String, File> sounds;
	protected final String folderPath;	
	
	public SoundGenerator(String mode) {
		this.folderPath = "src"+ File.separator+"assets"+ File.separator+ "sounds" + File.separator + mode;
		this.sounds = new HashMap<String, File>();
		createSounds();
	}

	private void createSounds() {
		 sounds.put("stageClear", new File(folderPath+File.separator+ "stageClear.wav"));
		 sounds.put("gameOver",new File(folderPath+File.separator+ "gameOver.wav"));
		 sounds.put("jump",new File(folderPath+File.separator+ "jump.wav"));
		 sounds.put("characterDies", new File(folderPath+File.separator+ "CharacterDie.wav"));
		 sounds.put("coin", new File(folderPath+File.separator+ "coin.wav"));
		 sounds.put("1-up", new File(folderPath+File.separator+ "1-up.wav"));
		 sounds.put("powerUpAppears", new File(folderPath+File.separator+ "powerUpAppears.wav"));
		 sounds.put("mushroom", new File(folderPath+File.separator+ "mushroom.wav"));
		 sounds.put("kick", new File(folderPath+File.separator+ "kick.wav"));
		 sounds.put("fireball", new File(folderPath+File.separator+ "fireball.wav"));
		 sounds.put("fireballImpact", new File(folderPath+File.separator+ "fireballImpact.wav"));
		 sounds.put("stomp", new File(folderPath+File.separator+ "stomp.wav"));
		 sounds.put("breakBrick", new File(folderPath+File.separator+ "breakBrick.wav"));
		 sounds.put("musicLevel1",new File(folderPath+ File.separator+ "musicLevel1.wav"));
		 sounds.put("starMusic",  new File(folderPath+ File.separator+ "starMusic.wav"));
		 sounds.put("superCharacterDamaged", new File(folderPath + File.separator + "superCharacterDamaged.wav"));
		 sounds.put("lakitu", new File(folderPath + File.separator + "lakitu.wav"));
	}
	
	public HashMap<String, File> getFileOfSounds(){
		return sounds;
	}
}
