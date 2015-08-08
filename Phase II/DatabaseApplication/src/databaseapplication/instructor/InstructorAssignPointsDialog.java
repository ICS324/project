package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseapplication.CommonMethods;

public class InstructorAssignPointsDialog extends JFrame {

	private JTable table;
	private int max;
	private String section;
	private String componenetID; 
	public InstructorAssignPointsDialog(){
		String var = "1";
		
		CommonMethods cm = new CommonMethods();
		Connection con = cm.getConnection();
		
		ArrayList<String> SRNs = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+var);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no section taught by instructor "+var);
				return;
			}
			SRNs = new ArrayList<String>();
			do{
				SRNs.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		
		section = cm.Combo(SRNs.toArray(), "select a section");
		String course = section+"";
		section = section.substring(0, section.length()-1);/*		FIXME		*/
		if(section==null)
			return;
		
		ArrayList<String> componenets = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select name FROM GRADING_COMPONENT where COURSE_NUMBER = "+section);
			
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no component"+section);
				return;
			}
			componenets = new ArrayList<String>();
			do{
				componenets.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		
		String component = cm.Combo(componenets.toArray(), "select a componenet");
		if(component==null)
			return;
		
		try {
			table = cm.CreateTable(con, "select id,concat(concat(first_name,' '),last_name) as name from STUDENT join ENROLLMENT on (ENROLLMENT.STUDENT_ID=STUDENT.ID) where ENROLLMENT.SECTION_REFRENCE_NUMBER = 1231 and id NOT in (select ENROLLMENT_STUDENT_ID from point where grading_component_id =2)");
			if(table.getRowCount()==1){
				JOptionPane.showMessageDialog(null,"there is no one to assign grade for");
				return;
			}
			ResultSet r = con.createStatement().executeQuery("select max_points from GRADING_COMPONENT where COURSE_NUMBER = "+section+" and name = '"+component+"'");
			r.next();
			max = Integer.parseInt(r.getString(1));
			r = con.createStatement().executeQuery("select id FROM GRADING_COMPONENT where COURSE_NUMBER = "+section+" and name ='"+component+"'");
			r.next();
			componenetID = r.getString(1);
		} catch (SQLException e) {}
		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
	    model.addColumn("");
	    model.setValueAt("POINTS("+max+")", 0, 2);
	    
	    Button b = new Button("submit");
	    b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows = model.getRowCount();
				try{
					for (int i=1;i<rows;i++) {
						if(model.getValueAt(i, 2)==null){
							JOptionPane.showMessageDialog(new JFrame(), "there is cell without value",null,JOptionPane.ERROR_MESSAGE);
							return;
						}
						else if(Integer.parseInt(model.getValueAt(i, 2).toString()) > max){
							JOptionPane.showMessageDialog(new JFrame(), "the value : "+Integer.parseInt(model.getValueAt(i, 2).toString())+" exceeds the maximum("+max+")",null,JOptionPane.ERROR_MESSAGE);
							return;
						}
					}//for
				
				for (int i=1;i<rows;i++) {
					try {
						con.createStatement().executeQuery("INSERT INTO POINT VALUES ('"+model.getValueAt(i, 2).toString()+"', '"+componenetID+"', '"+model.getValueAt(i, 0).toString()+"', '"+course+"')");
						
					} catch (SQLException e1) {}
				}
				dispose();
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(new JFrame(), "there is a cell with a wrong input",null,JOptionPane.ERROR_MESSAGE);
					return;
				}
			}	
		});
	    setLayout(new BorderLayout());
	    add(table);
	    add(b,BorderLayout.SOUTH);
		setSize(300,400);
		setVisible(true);
			
	}
}
