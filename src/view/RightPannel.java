package view;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RightPannel extends JPanel{
	private static final long serialVersionUID = -6485528709501127766L;
	
	public AlreadyDonePannel pd;
	public JButton bb;
	
	public RightPannel(MainWindow mother,int MarginY){
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		pd = new AlreadyDonePannel(MarginY);
		pd.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(pd);
		
		bb = new JButton("Browse");
		bb.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(bb);
		
		bb.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Choose load file");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (chooser.showOpenDialog(mother) == JFileChooser.APPROVE_OPTION) {
					if(!chooser.getSelectedFile().getName().endsWith(".csv")){
						JOptionPane.showMessageDialog(mother, "Type de fichier choisis incorrect, merci de choisir un fichier avec l'extension .csv","Error",JOptionPane.WARNING_MESSAGE);
					}
					else{
						LinkedList<File> lf = new LinkedList<File>();
						lf.add(chooser.getSelectedFile());
						mother.filesDropped(lf);	
					}
					
				}
				else{
					JOptionPane.showMessageDialog(mother, "Type de fichier choisis incorrect, merci de choisir un fichier avec l'extension .csv","Error",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
	}
}
