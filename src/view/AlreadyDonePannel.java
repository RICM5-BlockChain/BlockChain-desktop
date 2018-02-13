package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class AlreadyDonePannel extends JPanel{
	private static final long serialVersionUID = 4002273426369445522L;
	List<String> filesDone;
	int marginY;
	
	public AlreadyDonePannel(int MarginY){
		super();
		marginY=MarginY;
		setPreferredSize(new Dimension(200, 300));
		filesDone = new LinkedList<String>();
		setVisible(true);
	}
	
	public void addFile(String fileName){
		filesDone.add(fileName);
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(new Font(Font.SERIF,Font.BOLD,15));
		g.drawString("Imported files", 15, marginY+15);
		g.setFont(new Font(Font.SERIF,0,12));
		for(int i=0;i<filesDone.size();i++){
			g.drawString(filesDone.get(i), 15,marginY+30+i*15);
		}
	}

}
