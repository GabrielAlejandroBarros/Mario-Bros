package game;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import factories.SoundGenerator;

public class SoundReproducer {
	private Clip audioLevelMusic;
	private Clip auxiliarAudio;
	private HashMap<String, File> sounds;
	
	public SoundReproducer(SoundGenerator generator) {
		this.sounds= generator.getFileOfSounds();
	}
	
	public void setAuxiliarSound(String path) {
		try {
			auxiliarAudio= AudioSystem.getClip();
			auxiliarAudio.open(getSound(path));
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setMusicSound(String path) {
		resourceAudioController(audioLevelMusic);
		try {
			audioLevelMusic= AudioSystem.getClip();
			audioLevelMusic.open(getSound(path));
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		   auxiliarAudio.start();
	}
	
	public void loop(int iteracions) {		
		audioLevelMusic.loop(iteracions);
	}

	private void resourceAudioController(Clip clip){
		if(clip!=null && clip.isActive()){
			clip.stop();
			clip.close();
		}
	}
	
	private AudioInputStream getSound(String path)  {
		AudioInputStream toRet=null;
		try {
		     toRet= AudioSystem.getAudioInputStream(sounds.get(path));
	    } catch (UnsupportedAudioFileException | IOException | NullPointerException e) {
				e.printStackTrace();
		}
		return toRet;
	}

    public boolean isRunning() {
		return audioLevelMusic.isRunning();
    }
}