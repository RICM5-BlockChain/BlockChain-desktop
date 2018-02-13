package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.CSVFile;
import model.PDF;
import model.Student;

public class ValidationWindow extends JFrame{
	private static final long serialVersionUID = 2463352889255129611L;
	SelectionPannel sp;
	JButton ValidationButton;
	public ValidationWindow(String title, List<CSVFile> csvlist){
		super(title);
		this.setSize(new Dimension(300,500));
		
		ValidateListElement[] elements = new ValidateListElement[csvlist.size()];
		for(int i=0;i<csvlist.size();i++){
			elements[i]=new ValidateListElement(csvlist.get(i));
		}
		
		
		sp = new SelectionPannel(elements);
		sp.setAlignmentX(LEFT_ALIGNMENT);
		
		JScrollPane js = new JScrollPane(sp);
		js.setPreferredSize(new Dimension(300,450));
		
		
		ValidationButton = new JButton("Valider");
		ValidationButton.setAlignmentX(CENTER_ALIGNMENT);
		ValidationButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showConfirmDialog(null, "Une fois valid�, les dipl�mes des �l�ves selectionn�s, vont �tre "
						+ "certifi�s et �crit dans la BlockChain. Cette �criture est "
						+ "irr�versible","Attention",JOptionPane.YES_NO_OPTION);
				List<Student> ValidList = sp.getAllValidateStudents();
				for(int i=0;i<ValidList.size();i++){
					System.out.println(ValidList.get(i).getName());
					String hash = PDF.exportAsPdf(ValidList.get(i));
				}
				
			}
		});
		
		
		
		this.add(js);
		this.add(ValidationButton,BorderLayout.SOUTH);
		this.setVisible(true);
	}

}