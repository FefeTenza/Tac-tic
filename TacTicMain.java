import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TacTicMain extends JFrame implements ActionListener {
	
	private JButton [][]buttons = new JButton[3][3];
	private JButton b1 = new JButton("SINGLEPLAYER");
	private JButton b2 = new JButton("MULTIPLAYER");
    private JLabel statusLabel = new JLabel("");
    private TacTicComp game = null;          //singleplayer
	private TacTicComp gamer = null;    //multiplayer
    private int human = 0;
    private int computer = 0;
	private int enemy = 0;
    private boolean isPlay = false;
    private String []chars=new String[]{"","X","O"};
	private static int cnt = 0;


    private void setStatus(String s) {
		statusLabel.setText(s);
    }

    private void setButtonsEnabled(boolean enabled) {
		for(int i=0;i<3;i++)
		for(int j=0;j<3;j++) {
			buttons[i][j].setEnabled(enabled);
			if(enabled) buttons[i][j].setText(" ");
		}
    }

    public TacTicMain() {
		setTitle("Tac Tic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel centerPanel =new JPanel(new GridLayout(3,3));
        Font font = new Font("Arial",Font.BOLD, 32);
        for(int i=0;i<3;i++)
		for(int j=0;j<3;j++) {
			buttons[i][j] = new JButton(" ");
			buttons[i][j].setFont(font);
			buttons[i][j].addActionListener(this);
			buttons[i][j].setFocusable(false);
			centerPanel.add(buttons[i][j]);
		}

       
		b1.addActionListener(this);    //SINGLEPLAYER button
		b2.addActionListener(this);    //multiplayer button

        JPanel northPanel = new JPanel();
        northPanel.add(statusLabel);
 
		northPanel.add(b1);
		northPanel.add(b2);

        setStatus("Click a Button To Start");
        setButtonsEnabled(false);

        add(northPanel,"North");
        add(centerPanel,"Center");

        setSize(600,400);

        setLocationRelativeTo(null);
    }

    public static void main(String []args) {
		new TacTicMain().setVisible(true);          //creates the board
    }

	private void computerTurn() {
		if(!isPlay) return;                                // if isPlay is = true

        int []pos = game.nextMove(computer);                  
        if(pos!=null) {
			int i = pos[0];
			int j = pos[1];
			buttons[i][j].setText(chars[computer]);
			game.setCoordinates(i,j,computer);
        }
        checkState();
    }

    private void gameOver(String s) {
		setStatus(s);
		setButtonsEnabled(false);
		isPlay = false;
	}

    private void checkState() {                                      //checks who won or lost in singleplayer
		if(game.whoWin(human)) {
		gameOver("Congratulations, You've Won!");
		}
		if(game.whoWin(computer)) {
			gameOver("Sorry, You Lose!");
		}
        if(game.nextMove(human)==null && game.nextMove(computer)==null) {
			gameOver("Draw, Rematch!");
		}
    }

	private void checkStater() {                                                   //checks who won or lost in multiplayer
		if(gamer.whoWin(human)) {
			gameOver(" Player 1 has Won!");
        }
		if(gamer.whoWin(enemy)) {
			gameOver(" Player 2 has Won");
		}
        if(gamer.nextMove(human)==null && gamer.nextMove(enemy)==null) {
			gameOver("Draw, Rematch!");
		}
    }

    private void click(int i,int j) {
		if(game.getCoordinates(i,j)==TacTicComp.EMPTY) {         // click is for humans in singleplayer
			buttons[i][j].setText(chars[human]);
            game.setCoordinates(i,j,human);
            checkState();
            computerTurn();
        }
    }

	private void clickers(int i,int j) {
		if(gamer.getCoordinates(i,j)==TacTicComp.EMPTY) {         // clicker is for humans in multiplayer , still need to add a method that will be for the 2nd player
			cnt++;
			if(cnt%2!=0){
				buttons[i][j].setText(chars[human]);
				gamer.setCoordinates(i,j,human);
				checkStater() ;
			}else{
			buttons[i][j].setText(chars[enemy]);
            gamer.setCoordinates(i,j,enemy);
				checkStater();
			}
        }
    }


 
    public void actionPerformed(ActionEvent event) {
		if(event.getSource() == b1){                                             //singleplayer clicked
                play();
        }else {
			for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(event.getSource()==buttons[i][j])
                            click(i,j);
		}

		if(event.getSource() == b2){                                                 //multiplayer clicked
			player();
            }else {
				for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					if(event.getSource()==buttons[i][j])
						clickers(i,j);
			}
 
    }

    private void play() {
		game = new TacTicComp();   // calling class
        human = TacTicComp.ONE;    //first player is human, sets human to one
        computer = TacTicComp.TWO;   //2nd player is computer, sets comp to two
        setStatus("Your Turn");        //
        setButtonsEnabled(true);
        isPlay = true;
    }

	private void player(){
		gamer = new TacTicComp();            //this is supposed to call Boardy class that looks like the rest of the GUI
		human = TacTicComp.ONE;     //calling the attribute in the AI class
		enemy = TacTicComp.TWO;
		setStatus("Your Turn");        //
        setButtonsEnabled(true);
        isPlay = true;
	}
 
}
