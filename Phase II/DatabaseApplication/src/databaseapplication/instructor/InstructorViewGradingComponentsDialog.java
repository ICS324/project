package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewGradingComponentsDialog extends JFrame {
	
	public InstructorViewGradingComponentsDialog() {
		String var = "1";
		CommonMethods cm = new CommonMethods();
		Connection con = cm.getConnection();
		
		ArrayList<String> components = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select distinct COURSE_NUMBER from grading_component where INSTRUCTOR_ID = "+var);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,var+" dont have any grading compnenets");
				return;
			}
			components = new ArrayList<String>();
			do{
				components.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		
		String selected = cm.Combo(components.toArray(), "select a course");
		if(selected==null)
			return;

		setLayout(new BorderLayout());
		JTable t = null;
                JTextField f = new JTextField("the grading components of section "+selected);
                f.setBackground(Color.LIGHT_GRAY);
                f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
                f.setEditable(false);
                add(f,BorderLayout.NORTH);
                ScrollPane p = new ScrollPane();
                t = cm.CreateTable(cm.getConnection(),
                        "select name,max_points,weight from grading_component where course_number ="+selected);
                p.add(t);
                add(p,BorderLayout.CENTER);
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);

	}
}
