package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewPointsDialog extends JFrame{
	public InstructorViewPointsDialog() {
		String var = "";
		while(var.length()==0){
			var = JOptionPane.showInputDialog("enter section reference number");//TODO variable
			if(var==null)
				return;
		}
		
		CommonMethods cm = new CommonMethods();
		setLayout(new BorderLayout());
		JTable t = null;
		try {
			JTextField f = new JTextField("the points");
			f.setBackground(Color.LIGHT_GRAY);
			f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
			f.setEditable(false);
			add(f,BorderLayout.NORTH);
			ScrollPane p = new ScrollPane();
			t = cm.CreateTable(cm.getConnection(),
					"select Student.id, CONCAT(CONCAT(first_name, ' '), last_name) name,POINT.EARNED_POINTS EARNED,GRADING_COMPONENT.NAME \"IN\" from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where ENROLLMENT_REFRENCE_NUMBER = "+var+" order by POINT.GRADING_COMPONENT_ID asc,\"ID\" asc");
			p.add(t);
			add(p,BorderLayout.CENTER);

			} catch (SQLException e) {}
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
		setVisible(true);

	}
}
