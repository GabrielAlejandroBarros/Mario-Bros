package entities.state.spiny;

import entities.enemies.Spiny;

public abstract class SpinyState {
	
    protected Spiny spiny;
    
    public SpinyState(Spiny spiny){
        this.spiny = spiny;
    }
    public abstract void moveRight(int frame);
	public abstract void moveLeft(int frame);
    public abstract void visitPlatform();
    public abstract void applyGravity();
}
