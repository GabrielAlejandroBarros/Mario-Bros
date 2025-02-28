package views;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;

public class PreGameScreen extends JPanel {   
    private ViewController viewController;
	private Image background;
    private JTextField nameInput;
    private ButtonGroup modeSelecter;    
	protected JButton initGame;
    protected JButton backButton;
    private String name;
    private String mode;

    public PreGameScreen(ViewController view) {
		this.viewController= view;
		setPreferredSize(new Dimension(ViewConstants.WIN_WIDTH,ViewConstants.WIN_HEIGHT));
        setLayout(null);
        createNameInput();
        createModeSelecter();
        createInitButton();        
        addBackButton();
		this.background = new ImageIcon("src/assets/backgrounds/menuBackground.png").getImage();
        this.mode = "ORIGINAL";
	}

    private void createNameInput() {
        this.nameInput = new JTextField("ENTER YOUR NAME");
        nameInput.setBounds(225, 330, 300, 40); 
        nameInput.setFont(ViewConstants.font);
        nameInput.setForeground(Color.GRAY);
        nameInput.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (nameInput.getText().equals("ENTER YOUR NAME")) {
                    nameInput.setText("");
                    nameInput.setForeground(Color.BLACK); 
                }
            }    
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (nameInput.getText().isEmpty()) {
                    nameInput.setText(""); 
                    nameInput.setForeground(Color.GRAY);    
                }
            }
        });    
        nameInput.addActionListener(new NameInputListener());
        add(nameInput);
    }
     
    private void createModeSelecter() {
        this.modeSelecter = new ButtonGroup();
        JLabel select = new JLabel("PICK MODE");
        select.setBounds(300, 375, 300, 30);
        
        JToggleButton customMode = new JToggleButton("CUSTOM");
        customMode.setBounds(50, 400, 300, 30);
        customMode.addActionListener(new ModeInputListener("CUSTOM"));
        
        JToggleButton originalMode = new JToggleButton("ORIGINAL");
        originalMode.setBounds(400, 400, 300, 30);
        originalMode.addActionListener(new ModeInputListener("ORIGINAL"));
        modeSelecter.add(customMode);
        modeSelecter.add(originalMode);

        select.setFont(ViewConstants.font);
        customMode.setFont(ViewConstants.font);
        originalMode.setFont(ViewConstants.font);
        add(select);
        add(customMode);
        add(originalMode);
    }
    
    private void createInitButton(){
        this.initGame = new JButton("INIT LEVEL");        
        initGame.setFont(ViewConstants.font);
	    initGame.setBounds(200,500 ,350, 50);
		initGame.addActionListener(new NewGameButtonListener());
        add(initGame);
    }
    public void setMode(String mode){
        this.mode = mode;
    }
    public void setName(String name){
        this.name = name;
    }
    private void addBackButton() {
        backButton = new JButton("BACK TO MENU");        
        backButton.addActionListener(new BackButtonListener());
        backButton.setBounds(0, 0 , 300, 30);
        backButton.setFont(ViewConstants.font);
        this.add(backButton);
        this.setComponentZOrder(backButton, 0); 
    }

    private class NameInputListener implements ActionListener {        
        public void actionPerformed(ActionEvent e) {
            String playerName = nameInput.getText();
            setName(playerName);
        }
    }
    private class ModeInputListener implements ActionListener {
        private String mode;
    
        public ModeInputListener(String mode) {
            this.mode = mode;
        }
    
        public void actionPerformed(ActionEvent e) {
            setMode(mode);
        }
    }
    private class NewGameButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
            setName(name);
			viewController.newGame(mode, nameInput.getText());
		}
	}
    private class BackButtonListener implements ActionListener{
       
        public void actionPerformed(ActionEvent e) {
            viewController.showMenuScreen();
        }		
	}  
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}