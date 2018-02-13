package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftPannel extends JPanel{
	private static final long serialVersionUID = 7379171729501157003L;
	public PannelDropable pd;
	public JButton Validate;
	
	public LeftPannel(MainWindow mother,int MarginY,int X,int Y){
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		pd = new PannelDropable(mother, MarginY,X,Y);
		pd.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(pd);
		
		
		Validate = new JButton("Validation");
		Validate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Validate.addMouseListener(new MouseListener() {
			
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
				mother.ValidateFiles();
				
			}
		});
		
		this.add(Validate);
		
		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(10,15));
		this.add(jp);
		
		this.setVisible(true);
	}

}
