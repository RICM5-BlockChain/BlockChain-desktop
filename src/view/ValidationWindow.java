package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import model.CSVFile;
import model.HTTP;
import model.HTTPModels;
import model.Mailer;
import model.PDF;
import model.Student;
import model.USB;

public class ValidationWindow extends JFrame{
	private static final long serialVersionUID = 2463352889255129611L;
	SelectionPannel sp;
	JButton ValidationButton;
	ValidationWindow main;
	
	public ValidationWindow(String title, List<CSVFile> csvlist){
		super(title);
		main=this;
		this.setSize(new Dimension(300,500));
		this.setLocationRelativeTo(null);
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
				main.setVisible(false);
				int choice = JOptionPane.showConfirmDialog(null, "Une fois validé, les diplômes des élèves selectionnés, vont être "
						+ "certifiés et écrit dans la BlockChain. Cette écriture est "
						+ "irréversible","Attention",JOptionPane.YES_NO_OPTION);
				
				if(choice == JOptionPane.OK_OPTION){
					
					JOptionPane.showMessageDialog(null,"Veuillez valider puis insérer votre clef USB de securité");
					char[] privateKey = USB.main(null);
						if(privateKey!=null){
							List<Student> ValidList = sp.getAllValidateStudents();
							JOptionPane.showMessageDialog(null, "Merci de valider puis de retirer la clef de securité");
							if(USB.removedKey()){
								for(int i=0;i<ValidList.size();i++){
									System.out.println(ValidList.get(i).getName());
									String hash = PDF.exportAsPdf(ValidList.get(i));
									try {
										String transaction = HTTPModels.sendPost(hash);
										Mailer.send_attached(ValidList.get(i).getMail(), ValidList.get(i).getDiplomaName() + " - " + ValidList.get(i).getName(),"Voici le diplome et le num�ro de transaction � conserver, AUCUN DUPLICATA POSSIBLE\ntransaction : \"transaction\"=\""+transaction+"\"",PDF.getPathTo(ValidList.get(i).getName()));
									} catch (Exception e1) {
										JOptionPane.showMessageDialog(null, "Erreur lors de l'envoi des donn�es, merci de v�rifier votre connexion internet, contacter un administrateur si le probleme persiste");
										e1.printStackTrace();
									}
								}
								System.exit(0);
							}
							else{
								JOptionPane.showMessageDialog(null, "Probleme de controle, fermeture du programme");
								System.exit(-1);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Clé USB non conforme, fermeture du programme");
							System.exit(-1);
						}
						
					
				}
				else{
					
				}
			}
		});
		
		
		
		this.add(js);
		this.add(ValidationButton,BorderLayout.SOUTH);
		this.setVisible(true);
	}

}
