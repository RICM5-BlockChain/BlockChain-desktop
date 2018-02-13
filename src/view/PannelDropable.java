package view;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PannelDropable extends JPanel{
	private static final long serialVersionUID = 6463082474113795953L;
	
	MainWindow mother;
	
	public PannelDropable(MainWindow mother){
		this.mother=mother;
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

}
