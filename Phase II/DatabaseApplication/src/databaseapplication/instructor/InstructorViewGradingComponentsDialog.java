package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewGradingComponentsDialog extends JFrame {
	
	public InstructorViewGradingComponentsDialog() {
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
			JTextField f = new JTextField("the grading components of section:"+var);
			f.setBackground(Color.LIGHT_GRAY);
			f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
			f.setEditable(false);
			add(f,BorderLayout.NORTH);
			ScrollPane p = new ScrollPane();
			t = cm.CreateTable(cm.getConnection(),
					"select name,max_points,weight from grading_component where course_number ="+var);
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
