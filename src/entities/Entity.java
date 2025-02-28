package entities;
import factories.Sprite;
import observer.GraphicObserver;
import tools.GraphicTools;
import views.ViewConstants;
public abstract class Entity implements LogicalEntity {	

	protected float positionInX;
    protected float positionInY;
    protected Sprite sprite;
    protected GraphicObserver observer;
    protected BoundingBox boundingBox;

    public Entity(Sprite sprite, float positionInX, float positionInY){
        this.sprite = sprite;
        this.positionInX = positionInX;
        this.positionInY =positionInY;
    }

    public void setBoundingBox(BoundingBox hitbox){
        this.boundingBox = hitbox;
    }
    
    public BoundingBox getBoundingBox(){
        return boundingBox;
    }
    
    public boolean colision(Entity entity){
        boolean colision = false;
        if(entity.getBoundingBox() != null)
            colision = boundingBox.intersects(entity.getBoundingBox());
        return colision;
    }
    public boolean leftCollision(Entity entity){
        return boundingBox.leftCollision(entity.getBoundingBox().getBoundsRight());
    }
    
    public boolean rightCollision(Entity entity){
        return boundingBox.rightCollision(entity.getBoundingBox().getBoundsLeft());
    }
    
    public boolean upCollision(Entity entity){
        return boundingBox.upCollision(entity.getBoundingBox().getBoundsBottom());
    }
    
    public boolean downCollision(Entity entity){
        return boundingBox.downCollision(entity.getBoundingBox().getBoundsTop());
    }
    
    public Sprite getSprite(){
        return sprite;
    }

    public void setSprite(Sprite sprite){
         this.sprite = sprite;
    }

    public void registerObserver(GraphicObserver observer){
        this.observer = observer;
    }

    public void setX(float positionInX){
        this.positionInX = positionInX;
    }

    public void setY(float positionInY){
        this.positionInY = positionInY;
    }

    public float getX(){
        return positionInX;
    }

    public float getY(){
        return positionInY;
    }

    public float getWidth(){
        return GraphicTools.round2Digits(observer.getWidth() / (float) ViewConstants.CELL_SIZE);
    }
    public float getHeight(){
        return GraphicTools.round2Digits(observer.getHeight() / (float) ViewConstants.CELL_SIZE);
    }
    
    public GraphicObserver getGraphicObserver(){
        return observer;
    }
    
}
