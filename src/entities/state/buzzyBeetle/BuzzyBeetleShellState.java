package entities.state.buzzyBeetle;

import entities.enemies.BuzzyBeetle;

public class BuzzyBeetleShellState implements BuzzyBeetleState {
    protected BuzzyBeetle buzzyBeetle;

    public BuzzyBeetleShellState(BuzzyBeetle buzzyBeetle){
        this.buzzyBeetle = buzzyBeetle;
    }

    public void moveLeft(int frame) {

    }

    public void moveRight(int frame) {

    }
    
    public int hit() {
        buzzyBeetle.dead();
        return buzzyBeetle.getPointsOnDeath();
    }
    
}
