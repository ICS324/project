package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ScrollPane;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewStudentsDialog extends JFrame{
	
	private JTable t;

	InstructorViewStudentsDialog(){
		CommonMethods cm = new CommonMethods();
		setLayout(new BorderLayout());
		try {
			JTextField f = new JTextField("the list of students");
			f.setBackground(Color.LIGHT_GRAY);
			f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
			f.setEditable(false);
			add(f,BorderLayout.NORTH);
			ScrollPane p = new ScrollPane();
			t = cm.CreateTable(cm.getConnection(),"select * from major");
			p.add(t);
			add(p,BorderLayout.CENTER);

			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize((int) (t.getPreferredSize().width*1.25),t.getPreferredSize().height*2);
		setVisible(true);
	}
	
}
