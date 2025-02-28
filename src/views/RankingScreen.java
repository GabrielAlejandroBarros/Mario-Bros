package views;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RankingScreen extends JPanel {
    protected ViewController viewController;
    protected JPanel contentPanel;
    protected RankingPanel rankingPanel;
    private JButton backButton;

    public RankingScreen(ViewController viewController) {
		this.viewController= viewController;
		setPreferredSize(new Dimension(ViewConstants.WIN_WIDTH,ViewConstants.WIN_HEIGHT));
        setLayout(null);
        addRankingPanel();
        addBackButton();  
    }

    private void addRankingPanel() {
        this.rankingPanel = new RankingPanel(viewController);
        rankingPanel.setVisible(true);
        rankingPanel.setBounds(0, 0, ViewConstants.PANEL_WITDH, ViewConstants.PANEL_HEIGHT);
        this.add(rankingPanel);
        repaint();
        
    }
    
    private void addBackButton() {
        backButton = new JButton("BACK TO MENU");        
        backButton.addActionListener(new BackButtonListener());
        backButton.setBounds(0, 0 , 300, 30);
        backButton.setFont(ViewConstants.font);
        this.add(backButton);
        this.setComponentZOrder(backButton, 0); 
    }
    
    private class BackButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            viewController.showMenuScreen();
        }
		
	}  
}