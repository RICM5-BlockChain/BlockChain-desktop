package view;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Launcher;
import model.CSV;
import model.PDF;
import model.Student;

public class MainWindow extends JFrame{
	
	private static final long serialVersionUID = -7973353347922125981L;
	public AlreadyDonePannel pannelDone;
	public PannelDropable pannelDrop;
	
	public MainWindow(String title,int width,int height){
		super(title);
		setSize(width, height);
		pannelDone = new AlreadyDonePannel();
		pannelDrop = new PannelDropable(this);
	}
	
	public void filesDropped(List<File> lf){
		
	}
	
	
	
	public void showUp(){
		List<Student> ls = new LinkedList<Student>();
		ls.clear();
		for(int i=0;i<lf.size();i++){
			ls.addAll(CSV.readCSV(lf.get(i)));
		}
		lf.clear();
		
		int nombreEtudiant = ls.size();
		System.out.println("Nombre d'étudiants présent dans le fichier : " + nombreEtudiant);
		jd.setVisible(false);
		int dialogButton = JOptionPane.showConfirmDialog(this, nombreEtudiant + " étudiants trouvés dans le fichier, cela est correct ?","Confirmation",JOptionPane.YES_NO_OPTION);
		if(dialogButton==0){
			for(int i=0;i<ls.size();i++){
				//System.out.println(ls.get(i).finalPresentation());
				String hash = PDF.exportAsPdf(ls.get(i));
				/** TODO
				 * 	send hash to blockChain
				 *  Get transaction number 
				 *  => send mail with pdf + transaction number
				 */
			}
			
			Launcher.config.setAsDone(ls);
			System.out.println("taille de la liste : "+ls.size());
			
			
		}
		else{
			
		}
		
		this.setVisible(false);
		this.setVisible(true);
		this.repaint();
		jd.setVisible(true);
		
		
	}
	
	public void ErrorExit(){
		this.setVisible(false);
		System.exit(-1);
	}
}
