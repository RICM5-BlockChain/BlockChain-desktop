package view;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import model.Student;

public class SelectionPannel extends JPanel implements Scrollable{
	private static final long serialVersionUID = -3797757035768123068L;
	ValidateListElement[] list;
	
	public SelectionPannel(ValidateListElement[] list){
		super();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i=0;i<list.length;i++){
			list[i].setAlignmentX(LEFT_ALIGNMENT);
			this.add(list[i]);
		}
		
		this.setVisible(true);
		
		
	}

	public List<Student> getAllValidateStudents(){
		List<Student> ls = new LinkedList<Student>();
		for(int i=0;i<list.length;i++){
			ls.addAll(list[i].getAllValidStudent());
		}
		
		return ls;
	}
	
	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(300,450);
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
