package entities;
import java.awt.Rectangle;

public class BoundingBox extends Rectangle {
    protected BoundingBox leftBound;
    protected BoundingBox rightBound;
    protected BoundingBox upperBound;
    protected BoundingBox downBound;

    public BoundingBox (int x, int y, int width, int height){
        super(x,y,width,height);
    }
    
    public void createExternalBounds(){
        this.upperBound = new BoundingBox(x+3, y, width-6, height / 4);
        this.downBound = new BoundingBox(x+3, y + height * 3 / 4, width-6, height / 4);
        this.leftBound = new BoundingBox(x, y+3, width / 4, height-6);
        this.rightBound = new BoundingBox(x + width * 3 / 4, y+3, width / 4, height-6);
    }
    
    public void updateBoundingBoxCoords(int newX, int newY) {
        this.setLocation(newX,newY);
        downBound.setLocation(newX+3, newY + height * 3 / 4);
        upperBound.setLocation(newX+3,newY);
        leftBound.setLocation(newX, newY + 3);
        rightBound.setLocation(newX + width * 3 / 4,newY + 3);
    }    


    public boolean collision (BoundingBox entityBox) {
        return this.intersects(entityBox);
    }

    public boolean rightCollision(BoundingBox externalBounding) {
        boolean colision = getBoundsRight().intersects(externalBounding);   
        return colision;
    }
    
    public boolean leftCollision(BoundingBox externalBounding) {
        boolean colision = getBoundsLeft().intersects(externalBounding);  
        return colision;
    }
    
    public boolean downCollision(BoundingBox externalBounding) {
        boolean downCollision = getBoundsBottom().intersects(externalBounding);
        return downCollision;
    }    

    public boolean upCollision(BoundingBox externalBounding) {
        boolean upCollision = getBoundsTop().intersects(externalBounding); 
        return upCollision;
    }

    public BoundingBox getBoundsTop() {
        return upperBound;
    }

    protected BoundingBox getBoundsBottom() {
        return downBound;
    }

    protected BoundingBox getBoundsLeft() {
        return leftBound;
    }

    protected BoundingBox getBoundsRight() {
        return rightBound;
    }

    public void updateExternalBoundsToBig(){
        upperBound.height *= 2;
        downBound.height *= 2;
        leftBound.height *= 2;
        rightBound.height *= 2;
    }

    public void updateExternalBoundsToSmall(){
        upperBound.height /= 2;
        downBound.height /= 2;
        leftBound.height /= 2;
        rightBound.height /= 2;
    }

}