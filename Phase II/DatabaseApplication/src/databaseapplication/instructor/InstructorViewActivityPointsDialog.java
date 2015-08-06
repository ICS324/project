package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewActivityPointsDialog extends JFrame {
	
		public InstructorViewActivityPointsDialog() {
			String var = "";
			while(var.length()==0){
				var = JOptionPane.showInputDialog("select an activity");//TODO variable
				if(var==null)
					return;
			}
			
			CommonMethods cm = new CommonMethods();
			Connection con = cm.getConnection();
			setLayout(new BorderLayout());
			JTable t = null;
			try {
				JTextField f = new JTextField("the points for activity");
				f.setBackground(Color.LIGHT_GRAY);
				f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
				f.setEditable(false);
				add(f,BorderLayout.NORTH);
				ScrollPane p = new ScrollPane();
				t = cm.CreateTable(con,
						"select Student.id, CONCAT(CONCAT(first_name, ' '), last_name) name,POINT.EARNED_POINTS EARNED from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where GRADING_COMPONENT.ID = "+var+" order by POINT.GRADING_COMPONENT_ID asc");
				p.add(t);
				
				ResultSet r = con.createStatement().executeQuery("select avg(POINT.EARNED_POINTS) Average from STUDENT join POINT join GRADING_COMPONENT on(POINT.GRADING_COMPONENT_ID=GRADING_COMPONENT.ID) on (STUDENT.ID = POINT.ENROLLMENT_STUDENT_ID) where GRADING_COMPONENT.ID = 1 ");
				r.next();
				JTextField text = new JTextField("the average is " + r.getString(1));
				text.setEditable(false);
				text.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
				add(p,BorderLayout.CENTER);
				add(text,BorderLayout.SOUTH);
				} catch (SQLException e) {}
			setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
			setVisible(true);

		}
}
