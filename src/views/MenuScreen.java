package views;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class MenuScreen extends JPanel{

	protected JButton exit;
	protected JButton newGame;
	protected JButton ranking;
	protected ViewController viewController;
	private Image background;
	
	public MenuScreen(ViewController view) {
		this.viewController= view;
		setPreferredSize(new Dimension(ViewConstants.WIN_WIDTH,ViewConstants.WIN_HEIGHT));
        setLayout(null);
        createJButtons();
		this.background = new ImageIcon("src/assets/backgrounds/menuBackground.png").getImage();
	}

	private void createJButtons() {
		configureButtons();
		addButtonListeners();
        addButtons();
	}
	
	private void configureButtons(){
		newGame= new JButton("PLAY");
		ranking= new JButton("RANKING");
		exit= new JButton("EXIT");
	    ranking.setBounds(75,ViewConstants.WIN_WIDTH/2 ,200, 50);
	    newGame.setBounds(300,ViewConstants.WIN_WIDTH/2 ,200, 50);
	    exit.setBounds(525,ViewConstants.WIN_WIDTH/2 ,200, 50);

		newGame.setFont(ViewConstants.font);
		ranking.setFont(ViewConstants.font);
		exit.setFont(ViewConstants.font);
	}
	private void addButtonListeners(){
		newGame.addActionListener(new NewGameButtonListener());
        ranking.addActionListener(new RankingButtonListener());
		exit.addActionListener(new ExitButtonListener());
	}
	private void addButtons(){
        add(newGame);
        add(ranking);
        add(exit);
	}
	
	private class RankingButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			viewController.showRankingScreen();
		}
		
	}
	
	private class ExitButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			viewController.exitGame();
		}
	}

	private class NewGameButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			viewController.showPreGameScreen();
		}
		
	}

	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }

}