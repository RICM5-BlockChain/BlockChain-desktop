package view;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftPannel extends JPanel{
	private static final long serialVersionUID = 7379171729501157003L;
	public PannelDropable pd;
	public JButton Validate;
	private MainWindow mother;
	
	public LeftPannel(MainWindow mother,int MarginY,int X,int Y){
		super();
		this.mother= mother;
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		pd = new PannelDropable(mother, MarginY,X,Y);
		pd.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(pd);
		
		
		Validate = new JButton("Validation");
		Validate.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(Validate);
		
		this.setVisible(true);
	}

}
