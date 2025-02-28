package entities.enemies;
import java.util.HashMap;
import entities.character.Character;
import entities.character.CharacterVisitor;
import entities.platforms.Platform;
import entities.state.koopaTroopa.KoopaTroopaNormalState;
import entities.state.koopaTroopa.KoopaTroopaShellState;
import entities.state.koopaTroopa.KoopaTroopaState;
import factories.Sprite;
import observer.GraphicObserver;
public class KoopaTroopa extends Enemy{

	static final private int pointsOnDeath=90;
	static final private int pointsOnKill=-45;	
	
	protected HashMap<String, KoopaTroopaState> koopaTroopaStates;
	protected KoopaTroopaState koopaTroopaActualState;
	
	public KoopaTroopa(Sprite sprite, int positionInX, int positionInY) {
		super(sprite, positionInX, positionInY, pointsOnDeath, pointsOnKill);
		this.direction="Left";
		this.flies=false;
		this.koopaTroopaStates = getKoopaTroopaStates();
		this.koopaTroopaActualState = koopaTroopaStates.get("Normal");
	}

	private HashMap<String, KoopaTroopaState> getKoopaTroopaStates(){
		HashMap<String, KoopaTroopaState> koopaTroopaStates = new HashMap<String, KoopaTroopaState>();
		koopaTroopaStates.put("Normal", new KoopaTroopaNormalState(this));
		koopaTroopaStates.put("Shell", new KoopaTroopaShellState(this));
		return koopaTroopaStates;
	}
	
	public void move(){
		koopaTroopaActualState.move(spriteNumber);
	}

	public void hit(Character character){
		koopaTroopaActualState.hit(character);
	}

	public void normalMode(){
		koopaTroopaActualState = koopaTroopaStates.get("Normal");
	}

	public void shellMode(){
		KoopaTroopaShellState shellState = (KoopaTroopaShellState) koopaTroopaStates.get("Shell");
		koopaTroopaActualState = shellState;
		shellState.updateShell();
	}

	public int getPointsOnDeath() {
		return pointsOnDeath;
	}
	
	public int getPointsOnKill() {
		return pointsOnKill;
	}
	
	public void acceptVisit(CharacterVisitor visitor) {
    	visitor.visit(this);
    }
	
	public void setObserver(GraphicObserver observer) {
		this.observer = observer;
	}
	
	public void visit(Enemy enemy) {
		koopaTroopaActualState.hitEnemy(enemy);
	}

	public void visit(Platform platform) {
		
	}
	
}
