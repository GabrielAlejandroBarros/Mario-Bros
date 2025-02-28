package entities;
import entities.character.Character;
import entities.platforms.Flag;
import factories.Sprite;
import views.ViewConstants;

public class Animations{
	
    public void characterDeathAnimation(Character character) {
            character.setIsBusy(true);
            character.setVerticalSpeed(0);
            character.jump("Died");

            while (character.getY()>-5) {
                character.applyGravity();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (character.getSoundObserver().isRunning()) {
                
            }
            character.getSoundObserver().loopMusicLevel();
            character.setIsBusy(false);
            character.setInStart();
            character.setIsInAir(false);
    }

    public void superAnimation(Character character, String actualState, String newState){
            character.setIsBusy(true);
            if(character.isInAir())
                changeSpritesIfJump(character, actualState, newState);
            else 
                changeSpritesIfNoJump(character,actualState,newState);
            character.setIsBusy(false);
    }

    public void characterInFlagAnimation(Character character, Flag flag){
        character.setIsBusy(true);
        while(flag.getY()>1){
            character.setY(character.getY()-0.5f);
            flag.setY(flag.getY()-0.5f);
            character.getGraphicObserver().update();
            flag.getGraphicObserver().update();
            try{
                Thread.sleep(ViewConstants.GAMETICK*2); 
            } 
            catch(InterruptedException e )   {
                e.printStackTrace();
            }
        }
    } 

    private void changeSpritesIfJump(Character character, String actualState, String newState){
        Sprite jumpingRight= character.getCharacterStates().get(actualState).getSprites().get("JumpingRight");
        Sprite superJumpingRight= character.getCharacterStates().get(newState).getSprites().get("JumpingRight");
        Sprite jumpingLeft= character.getCharacterStates().get(actualState).getSprites().get("JumpingLeft");
        Sprite superJumpingLeft= character.getCharacterStates().get(newState).getSprites().get("JumpingLeft");
        for(int i = 0; i<5; i++){
            try{
                if(character.isMovingRight()) {
                    character.setSprite(jumpingRight);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                    character.setSprite(superJumpingRight);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                }
                else {
                    character.setSprite(jumpingLeft);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                    character.setSprite(superJumpingLeft);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                }
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        character.setIsBusy(false);
    }
    
    private void changeSpritesIfNoJump(Character character, String actualState, String newState){
        Sprite normalRight= character.getCharacterStates().get(actualState).getSprites().get("JumpingRight");
        Sprite superRight= character.getCharacterStates().get(newState).getSprites().get("JumpingRight");
        Sprite normalLeft= character.getCharacterStates().get(actualState).getSprites().get("JumpingLeft");
        Sprite superLeft= character.getCharacterStates().get(newState).getSprites().get("JumpingLeft");
        for(int i = 0; i<5; i++){
            try{
                if(character.isMovingRight()) {
                    character.setSprite(normalRight);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                    character.setSprite(superRight);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                }
                else {
                    character.setSprite(normalLeft);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                    character.setSprite(superLeft);
                    character.getGraphicObserver().update();
                    Thread.sleep(75);
                }
            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
       

    