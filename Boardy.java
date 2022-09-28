import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Boardy implements ActionListener {
	
	Random random = new Random();
	JFrame JF = new JFrame();
	JPanel titlePanel = new JPanel();       //to hold the title
	JPanel btnPanel = new JPanel();      // to hold all the buttons
    JLabel textF = new JLabel();	     //to display a message of some sort
	JButton[] btn = new JButton[9];     //9 buttons coz we only need 9 buttons
	boolean p1_turn;                    //true if it's player1's turn andf false if it's player2's turn
	
	public Boardy(){
		
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JF.setSize(800, 800);
		JF.getContentPane().setBackground(new Color(50, 50, 50));
		JF.setLayout(new BorderLayout());
		JF.setVisible(true);
		
		textF.setBackground(new Color(25, 25, 25));
		textF.setForeground(new Color(25, 255, 0));
		textF.setFont(new Font("Monospaced bold Italic", Font.BOLD, 75));
		textF.setHorizontalAlignment(JLabel.CENTER);
		textF.setText("Tac-Tic");
		textF.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 800, 100);
		// int level 
		btnPanel.setLayout(new GridLayout(3, 3));
		btnPanel.setBackground(new Color(150, 25, 25));
		
		titlePanel.add(textF);
		JF.add(titlePanel, BorderLayout.NORTH);
		JF.add(btnPanel);
		
	    for (int i = 0; i <= 9; i++){
			btn[i] = new JButton();
		    btnPanel.add(btn[i]);
		    btn[i].setFont(new Font("Serif Plain", Font.BOLD, 120));
		    btn[i].setFocusable(false);
		    btn[i].addActionListener(this);
	    }	
		
	}//constructor
	
	//override
	public void actionPerformed(ActionEvent e){
		
		for(int i = 0; i < 9; i++){
			if(e.getSource() == btn[i]){
				if(p1_turn){
					if(btn[i].getText() == ""){
						btn[i].setForeground(new Color(255, 0, 0));
						btn[i].setText("X");
						p1_turn = false;
						textF.setText("O turn");
						
						check();
					}
				}else{
					if(btn[i].getText() == ""){
						btn[i].setForeground(new Color(0, 0, 255));
						btn[i].setText("O");
						p1_turn = true;
						textF.setText("X turn");
						
						check();
					}
				}
			}
		}
		
		
		
	}
	
	public void firstTurn(){
		try{
			Thread.sleep(200);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		if(random.nextInt(2) == 0){
			p1_turn = true;
			textF.setText("X turn");
		}else{
			p1_turn = false;
			textF.setText("O turn");
		}	
		
	}//who's turn is first
	
	public void check(){
		//check if X wins
		if(
		   (btn[0].getText() == "X")&&
		   (btn[1].getText() == "X")&&
		   (btn[2].getText() == "X")
		   ){
		   xWins(0, 1, 2);
		   }	   
		if(
		   (btn[3].getText() == "X")&&
		   (btn[4].getText() == "X")&&
		   (btn[5].getText() == "X")
		   ){
		   xWins(3, 4, 5);
		   }
        if(
		   (btn[6].getText() == "X")&&
		   (btn[7].getText() == "X")&&
		   (btn[8].getText() == "X")
		   ){
		   xWins(6, 7, 8);
		   }	 
        if(
		   (btn[0].getText() == "X")&&
		   (btn[3].getText() == "X")&&
		   (btn[6].getText() == "X")
		   ){
		   xWins(0, 3, 6);
		   }
        if(
		   (btn[1].getText() == "X")&&
		   (btn[4].getText() == "X")&&
		   (btn[7].getText() == "X")
		   ){
		   xWins(1, 4, 7);
		   }	 		   
	    if(
		   (btn[2].getText() == "X")&&
		   (btn[5].getText() == "X")&&
		   (btn[8].getText() == "X")
		   ){
		   xWins(2, 5, 8);
		   }	 
		if(
		   (btn[0].getText() == "X")&&
		   (btn[4].getText() == "X")&&
		   (btn[8].getText() == "X")
		   ){
		   xWins(0, 4, 8);
		   }	 
		if(
		   (btn[2].getText() == "X")&&
		   (btn[4].getText() == "X")&&
		   (btn[6].getText() == "X")
		   ){
		   xWins(2, 4, 6);
		   }	
		   
		   
		//check if O wins
		if(
		   (btn[0].getText() == "O")&&
		   (btn[1].getText() == "O")&&
		   (btn[2].getText() == "O")
		   ){
		   oWins(0, 1, 2);
		   }	   
		if(
		   (btn[3].getText() == "O")&&
		   (btn[4].getText() == "O")&&
		   (btn[5].getText() == "O")
		   ){
		   oWins(3, 4, 5);
		   }
        if(
		   (btn[6].getText() == "O")&&
		   (btn[7].getText() == "O")&&
		   (btn[8].getText() == "O")
		   ){
		   oWins(6, 7, 8);
		   }	 
        if(
		   (btn[0].getText() == "O")&&
		   (btn[3].getText() == "O")&&
		   (btn[6].getText() == "O")
		   ){
		   oWins(0, 3, 6);
		   }
        if(
		   (btn[1].getText() == "O")&&
		   (btn[4].getText() == "O")&&
		   (btn[7].getText() == "O")
		   ){
		   oWins(1, 4, 7);
		   }	 		   
	    if(
		   (btn[2].getText() == "O")&&
		   (btn[5].getText() == "O")&&
		   (btn[8].getText() == "O")
		   ){
		   oWins(2, 5, 8);
		   }	 
		if(
		   (btn[0].getText() == "O")&&
		   (btn[4].getText() == "O")&&
		   (btn[8].getText() == "O")
		   ){
		   oWins(0, 4, 8);
		   }	 
		if(
		   (btn[2].getText() == "O")&&
		   (btn[4].getText() == "O")&&
		   (btn[6].getText() == "O")
		   ){
		   oWins(2, 4, 6);
		   }	
	}//check if there are any winning conditions
	
	public void xWins(int a, int b, int c){
		
		btn[a].setBackground(Color.GREEN);
		btn[b].setBackground(Color.GREEN);
		btn[c].setBackground(Color.GREEN);
		
		for(int i = 0; i < 9; i++){
			btn[i].setEnabled(false);
		}
		textF.setText("X Wins");
	}//if x wins
	
	public void oWins(int a, int b, int c){
		
		btn[a].setBackground(Color.GREEN);
		btn[b].setBackground(Color.GREEN);
		btn[c].setBackground(Color.GREEN);
		
		for(int i = 0; i <= 9; i++){
			btn[i].setEnabled(false);
		}
		textF.setText("O Wins");
	}//if o wins 
	
}	
   