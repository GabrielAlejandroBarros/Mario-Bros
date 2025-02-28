package observer;

import game.Game;

public class SoundObserver {
	
	protected Game game;
           
	public SoundObserver(Game manager) {
		game= manager;
	}
	
	public void reproduceSound(String path) {
		game.reproduceSound(path);
	}

	public void reproduceSoundOneIteration(String path, int iterations) {
		game.reproduceLoopSound(path, iterations);
	}

	public boolean isRunning(){
		return game.isRunningSound();
	}

	public void loopMusicLevel(){
		game.startLevelMusic();
	}
	

}
