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

public class InstructorViewStudentsDialog extends JFrame{
	
	private JTable t;

	InstructorViewStudentsDialog(){
		String var = "";
		while(var.length()==0){
			var = JOptionPane.showInputDialog("enter instructor's id");
			if(var==null)
				return;
		}
		
		CommonMethods cm = new CommonMethods();
		setLayout(new BorderLayout());
		try {
			JTextField f = new JTextField("the list of students");
			f.setBackground(Color.LIGHT_GRAY);
			f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
			f.setEditable(false);
			add(f,BorderLayout.NORTH);
			ScrollPane p = new ScrollPane();
			t = cm.CreateTable(cm.getConnection(),
					"select section.\"number\" as \"SECTION NUMBER\",student_id,concat(concat(first_name,' '),last_name) as name from section join enrollment on (section_refrence_number = refrence_number) join student on (student_id = id) where instructor_id = \'" + var+"\' order by \"number\",student_id");
			p.add(t);
			add(p,BorderLayout.CENTER);

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
		setVisible(true);
	}
	
}
