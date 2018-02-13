package view;

import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class AlreadyDonePannel extends JPanel{
	private static final long serialVersionUID = 4002273426369445522L;
	List<String> filesDone;
	int X;
	int Y;
	
	
	public AlreadyDonePannel(int locationX,int locationY){
		super();
		X = locationX;
		Y = locationY;
		filesDone = new LinkedList<String>();
	}
	
	public void addFile(String fileName){
		filesDone.add(fileName);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font(Font.SERIF,Font.ITALIC,15));
		g.drawString("imported files", X+15, Y+15);
		g.setFont(new Font(Font.SERIF,0,12));
		for(int i=0;i<filesDone.size();i++){
			g.drawString(filesDone.get(i), X+15,Y+ 30+i*15);
		}
	}

}
