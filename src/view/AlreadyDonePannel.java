package view;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import model.Student;
import controller.Launcher;

public class AlreadyDonePannel extends JPanel{
	private static final long serialVersionUID = 4002273426369445522L;
	
	
	
	public AlreadyDonePannel(){
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Etudiant déjà fait :", 15, 15);
		List<Student> ls = Launcher.config.getDone();
		for(int i=0;i<ls.size();i++){
			g.drawString(ls.get(i).getName(), 15, 30+i*15);
		}
	}

}
