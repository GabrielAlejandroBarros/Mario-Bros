package entities.state.koopaTroopa;
import factories.Sprite;
import tools.GraphicTools;
import entities.character.Character;
import entities.enemies.Enemy;
import entities.enemies.KoopaTroopa;

public class KoopaTroopaShellState implements KoopaTroopaState {
	
    protected KoopaTroopa koopaTroopa;
    protected long spawnTimeMillis;
    protected boolean standing;

    public KoopaTroopaShellState(KoopaTroopa koopaTroopa){
        this.koopaTroopa = koopaTroopa;
        this.spawnTimeMillis = System.currentTimeMillis();
        this.standing = true;
    }

    public void move(int frame){
        if(standing)
            checkChangeState();
        else
            switch (koopaTroopa.getDirection()) {
                case "Left":
                    moveLeft(frame);
                    break;
                case "Right":
                    moveRight(frame);
                    break;
                case "None":
                    break;
            }
    }
    private void moveRight(int frame) {
        float enemyX = koopaTroopa.getX();
        float horizontalSpeed = koopaTroopa.getHorizontalSpeed();
        koopaTroopa.setX(GraphicTools.round2Digits(enemyX + horizontalSpeed * 4));
        koopaTroopa.getObserver().update();
        
	}
	
	private void moveLeft(int frame) {
        float enemyX = koopaTroopa.getX();
        float horizontalSpeed = koopaTroopa.getHorizontalSpeed();
        koopaTroopa.setX(GraphicTools.round2Digits(enemyX - horizontalSpeed * 4));
        koopaTroopa.getObserver().update();
        
	} 

    public void hit(Character character){
        standing = false;
        String direction = koopaTroopa.getDirection() != "None" ? "None" : checkSideCollision(character);
        koopaTroopa.setDirection(direction);
    }

    private String checkSideCollision(Character character){
        String direction = character.getBoundingBox().rightCollision(koopaTroopa.getBoundingBox()) ? "Right" : "Left";
        return direction;
    }

    public void updateShell(){
        setShellSprite();
        updateSpawnTime();
        koopaTroopa.setDirection("None");
        standing = true;
    }

    private void setShellSprite(){
        Sprite shellSprite = koopaTroopa.getSpritesMap().get("Shell");
        koopaTroopa.setSprite(shellSprite);
        koopaTroopa.getObserver().update();
    }

    private void updateSpawnTime(){
        spawnTimeMillis = System.currentTimeMillis();
    }

    private void checkChangeState(){
        if(System.currentTimeMillis() - spawnTimeMillis >= 5000)
            koopaTroopa.normalMode();
    }

    public void hitEnemy(Enemy enemy){
        if(!standing)
            enemy.dead();
    }
        
    

    
    
}
