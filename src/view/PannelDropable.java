package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelDropable extends JPanel{
	private static final long serialVersionUID = 6463082474113795953L;
	
	List<String> filesDone;
	MainWindow mother;
	int X;
	int Y;
	int marginX = 10;
	int marginY = 10;
	int StartingXforDone;
	

	public PannelDropable(MainWindow mother,int sizeX,int sizeY,int startingXForDone){
		super();
		X = sizeX;
		Y = sizeY;
		setPreferredSize(new Dimension(sizeX, sizeY));
		setMaximumSize(new Dimension(sizeX, sizeY));
		setMinimumSize(new Dimension(sizeX, sizeY));
		setSize(300, 300);
		this.mother=mother;
		this.StartingXforDone=startingXForDone;
		filesDone = new LinkedList<String>();
		this.setDropTarget(new DropTarget() {
			private static final long serialVersionUID = -3948411003514866271L;

			@SuppressWarnings("unchecked")
			public synchronized void drop(DropTargetDropEvent evt) {
		        try {
		            evt.acceptDrop(DnDConstants.ACTION_COPY);
		            List<File> droppedFiles = (List<File>)
		                evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
		            List<File> lf = new LinkedList<File>();
		            for (File file : droppedFiles) {
		            	if(!file.getName().endsWith(".csv")){
		                	JOptionPane.showMessageDialog(null,"Le fichier n'est pas un fichier CSV, l'application va fermer.","ERREUR",JOptionPane.ERROR_MESSAGE);
		                	mother.ErrorExit();
		                }
		                System.out.println(file.getName());
		                lf.add(file);
		                
		            }
		            mother.filesDropped(lf);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});
	}
	
	public void addFile(String fileName){
		filesDone.add(fileName);
		repaint();
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		/*
		 * Drop pannel
		 */
		g.drawRect(marginX, marginY,marginX+X,marginY+Y);
		String s = "Drag and drop your CSV files here";
		g.setFont(new Font(Font.MONOSPACED,0,12));
		g.drawString(s,(int)(marginX+ (X/2)-(s.length()*3.5)),marginY+ Y/2);
		
		/*
		 * ImportedFiles pannel
		 */
		g.setFont(new Font(Font.SERIF,Font.ITALIC,15));
		g.drawString("imported files", StartingXforDone+15, marginY+15);
		g.setFont(new Font(Font.SERIF,0,12));
		for(int i=0;i<filesDone.size();i++){
			g.drawString(filesDone.get(i), StartingXforDone+15,marginY+30+i*15);
		}
	}

}
