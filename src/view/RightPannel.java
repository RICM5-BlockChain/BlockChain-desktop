package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RightPannel extends JPanel{
	private static final long serialVersionUID = -6485528709501127766L;
	
	public AlreadyDonePannel pd;
	public JButton bb;
	
	public RightPannel(int MarginY){
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		pd = new AlreadyDonePannel(MarginY);
		pd.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(pd);
		
		bb = new JButton("Browse");
		bb.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(bb);
	}
}
