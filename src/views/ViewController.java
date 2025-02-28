package views;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.LogicalEntity;
import entities.character.CharacterEntity;
import entities.character.Keyboard;
import entities.enemies.EnemyEntity;
import game.Game;
import observer.GraphicObserver;

public class ViewController {   
    protected JFrame window;
    protected LevelScreen levelScreen;
    protected MenuScreen menuScreen;
    protected Keyboard keyboardInputs;
    protected Game game;
    protected RankingScreen rankingScreen;
    protected PreGameScreen preGameScreen;
    protected JPanel gameOverScreen;

    public ViewController(Game game){   
        this.game = game;
        game.setViewController(this);
        this.levelScreen = new LevelScreen(this);
        this.menuScreen = new MenuScreen(this);
        this.preGameScreen = new PreGameScreen(this);
        configureWindow();
        setGameOverScreen();
        showMenuScreen();
    }

    public void configureWindow (){
        keyboardInputs = new Keyboard();
        window = new JFrame("p-comision23 :: MarioBros");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setSize(ViewConstants.WIN_WIDTH, ViewConstants.WIN_HEIGHT);
		window.setLocationRelativeTo(null);
        window.addKeyListener(keyboardInputs);
		window.setVisible(true);
    }


    public void newGame(String mode, String name){
        this.game = new Game();
        game.setMode(mode);
        game.setName(name);
        game.setViewController(this);
        setBackgroundAndScroll();
        game.start();
    }

    public void showMenuScreen(){
        window.setContentPane(menuScreen);
        refresh();
    }
    public void showLevelScreen(){
        window.setContentPane(levelScreen);
        window.requestFocusInWindow();
        refresh();
    }

    public void showRankingScreen(){ 
        this.rankingScreen = new RankingScreen(this);
        window.setContentPane(rankingScreen);
        refresh();
    }
    public void showPreGameScreen(){
        window.setContentPane(preGameScreen);
        refresh();
    }

    public void showGameOverScreen() {
        window.setContentPane(gameOverScreen);
        refresh();
    }


    public void refresh(){
        window.revalidate();
        window.repaint();
    }
    
    public Keyboard getKeyboard(){
        return keyboardInputs;
    }

    public GraphicObserver registerEntity(CharacterEntity character){
        GraphicObserver characterObserver = levelScreen.drawEntityCharacter(character);
        refresh();
        return characterObserver;

    }

    public GraphicObserver registerEntity(LogicalEntity entity, boolean isActive){
        GraphicObserver entityObserver = levelScreen.drawLogicalEntity(entity, isActive);
        refresh();
        return entityObserver;
    }

    public GraphicObserver registerEntity(EnemyEntity enemy){
        GraphicObserver entityObserver = levelScreen.drawLogicalEntity(enemy);
        refresh();
        return entityObserver;
    }

    
    public void removeLogicalEntity(LogicalEntity e) {
        levelScreen.removeEntity(e);
    }
    
    public void updateInformation(int newCoins, int newScore, int newTime, int newLives){
        levelScreen.updateInformationPanel(newCoins,newScore, newTime, newLives);
    }
    public void clearLevelScreen() {
        this.levelScreen = new LevelScreen(this);
    }

    public void exitGame(){
        window.setVisible(false);
        window.dispose();
        System.exit(0);
    }

    public LevelScreen getLevelScreen(){
        return levelScreen;
    }

    public Collection<String> getPlayers() {
        return game.getRankingPlayers();
    }
    public String getLevelBrackground() {
        return game.getCurrentLevel().getBackground();
    }

    public void setBackgroundAndScroll(){
        levelScreen.setBackgroundAndScroll();
    }

    private void setGameOverScreen(){
        JPanel gameOverScreen = new JPanel();
        JLabel gameOverText = new JLabel("GAME OVER", JLabel.CENTER);
        gameOverText.setForeground(Color.WHITE);
        gameOverText.setFont(ViewConstants.font.deriveFont(48f));
     
        gameOverScreen.setPreferredSize(new Dimension(ViewConstants.WIN_WIDTH,ViewConstants.WIN_HEIGHT));
        gameOverScreen.setLayout(new BorderLayout());
        gameOverScreen.setBackground(Color.BLACK);
        gameOverScreen.add(gameOverText,BorderLayout.CENTER);
        this.gameOverScreen = gameOverScreen;
    }

}