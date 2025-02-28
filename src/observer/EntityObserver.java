package observer;
import javax.swing.*;
import entities.LogicalEntity;
import factories.Sprite;
import tools.GraphicTools;
import views.LevelScreen;

public class EntityObserver extends GraphicObserver{
    protected boolean isActive;
    protected LevelScreen levelScreen;


    public EntityObserver(LevelScreen levelScreen, LogicalEntity observedEntity, boolean isActive){
        super(observedEntity);
        this.isActive = isActive;
        this.levelScreen = levelScreen;
        update();
    }

    public void update(){
        if(isActive){
            super.update();
        }
    }   

    public void activateObserver(){
        isActive = true;
        update();
    }
    
    public void spawnCoin(Sprite coinSprite){
        String path = coinSprite.getSkinPath();
        ImageIcon spriteIcon = new ImageIcon(getClass().getResource(path));
        JLabel coinsJLabel = new JLabel(spriteIcon);
    	int x = GraphicTools.transformX(observedEntity.getX()+0.25f,this);
        int y = GraphicTools.transformY(observedEntity.getY()+1,this);
    	coinsJLabel.setBounds(x, y,spriteIcon.getIconWidth(),spriteIcon.getIconHeight());
        levelScreen.drawCoin(coinsJLabel);
    }
    
    
    
}