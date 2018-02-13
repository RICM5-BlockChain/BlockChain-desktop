package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

public class AlreadySelectedPannel extends JPanel{
	
	private static final long serialVersionUID = 4002273426369445522L;
	List<File> filesSelected;
	int marginY;
	int marginX;
	int X;
	int Y;
	public AlreadySelectedPannel(int MarginY){
		super();
		marginY=MarginY;
		marginX=10;
		X=200;
		Y=300;
		setPreferredSize(new Dimension(X+marginX*2, marginY+Y+5));
		filesSelected = new LinkedList<File>();
		setVisible(true);
	}
	
	public void addFile(File fileName){
		filesSelected.add(fileName);
		repaint();
	}
	
	public void clearFiles(){
		filesSelected.clear();
		repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRect(marginX, marginY, X, 40);
		g.setFont(new Font(Font.SERIF,Font.BOLD,15));
		g.setColor(new Color(1,130,252));
		g.drawString("Imported files",marginX + (X/2)-(15*3), marginY+25);
		g.setColor(Color.black);
		g.setFont(new Font(Font.SERIF,0,14));
		g.drawRect(marginX, marginY, X, Y);
		for(int i=0;i<filesSelected.size();i++){
			g.drawString(filesSelected.get(i).getName(), 15,marginY+55+i*15);
		}
	}

}
