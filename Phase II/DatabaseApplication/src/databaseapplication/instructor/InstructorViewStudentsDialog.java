package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorViewStudentsDialog extends JFrame{
	
	private JTable t;

	InstructorViewStudentsDialog(){
            CommonMethods cm = Main.cm;
            Connection con = Main.con;
            String section = cm.getFrom(cm, con
            			,"select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+Main.instructorID
            			,"there is no section taught by instructor "+Main.instructorID
            			,"select a section");
            if(section==null)
                return;

            setLayout(new BorderLayout());
            JTextField f = new JTextField("the list of students");
            f.setBackground(Color.LIGHT_GRAY);
            f.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
            f.setEditable(false);
            add(f,BorderLayout.NORTH);
            ScrollPane p = new ScrollPane();
            t = cm.CreateTable(con,
                    "select student_id,concat(concat(first_name,' '),last_name) as name from enrollment join student on (student_id = id) where SECTION_REFRENCE_NUMBER = "+section+" order by student_id");
            p.add(t);
            add(p,BorderLayout.CENTER);
            setSize((int) (t.getPreferredSize().width*1.25 +50 ),t.getPreferredSize().height*2 +50);

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
            setResizable(false);
            setVisible(true);
	}
}
