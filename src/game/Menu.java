package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import display.Display;

public class Menu extends JFrame implements ActionListener{
	
	private JButton jouer,quitter;

	
	public Menu() {
		
		jouer = new JButton("Jouer");
		quitter = new JButton("Quitter");
		
		this.setLayout(new BorderLayout());
		ImageIcon icone = new ImageIcon("C:\\Users\\Krishan\\eclipse-workspace\\Java_MJP\\src\\mrjackpocket_bckg.jpg");
		JLabel image = new JLabel(icone);
		this.add(image);
		
		JPanel boutons = new JPanel();
		boutons.setLayout(new GridLayout(1,2));
		boutons.add(jouer);
		boutons.add(quitter);
		this.add(boutons,BorderLayout.SOUTH);
		this.setSize(1024,768);
		this.setVisible(true);
		this.setLocation(650,50);
		this.setResizable(false);
		jouer.addActionListener(this);
		quitter.addActionListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		int saisi=0;
		
		if(((JButton)e.getSource()).getText() == "Jouer") {
			this.setVisible(false);
			//chooseplayer fen = new chooseplayer();
		}
		if(((JButton)e.getSource()).getText() == "Quitter") {
			 saisi= JOptionPane.showConfirmDialog(this, "Etes-vous s�r de votre choix ?", "Dialogue de confirmation",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
	            switch (saisi){
	            case JOptionPane.OK_OPTION:
	                System.exit(1);
	                break;
	            case JOptionPane.CANCEL_OPTION:
	                break;
	            }
		}
	}

}	
