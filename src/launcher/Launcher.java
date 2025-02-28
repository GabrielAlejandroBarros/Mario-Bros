package launcher;

import game.Game;
import views.ViewController;

public class Launcher {
    public static void main(String arg[]){
        Game game= new Game();
        ViewController viewController = new ViewController(game);
    }
}
