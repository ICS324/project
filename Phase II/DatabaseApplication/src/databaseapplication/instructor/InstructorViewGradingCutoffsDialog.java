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

public class InstructorViewGradingCutoffsDialog extends JFrame {

	public InstructorViewGradingCutoffsDialog(){
		String var = "1";
		CommonMethods cm = new CommonMethods();
		Connection con = cm.getConnection();
		ArrayList<String> sections = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+var);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no section assigned to instructor "+var);
				return;
			}
			sections = new ArrayList<String>();
			do{
				sections.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		
		String section = cm.Combo(sections.toArray(), "select a section");
		if(section==null)
			return;
		
		setLayout(new BorderLayout());
		JTable t = null;
		try {
			JTextField f = new JTextField("the cutoffs for section "+section);
			f.setBackground(Color.LIGHT_GRAY);
			f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
			f.setEditable(false);
			add(f,BorderLayout.NORTH);
			ScrollPane p = new ScrollPane();
			t = cm.CreateTable(con,
					"select letter_grade,value from GRADING_CUTOFFS where SEC_REF_NUM = "+section+" order by value desc");
			p.add(t);
			add(p,BorderLayout.CENTER);

			} catch (SQLException e) {}
		setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);

		
	}
}