package databaseapplication.instructor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorAssignCutoffsDialog extends JFrame {
	
	private JTable table;

	public InstructorAssignCutoffsDialog() {
            final CommonMethods cm = Main.cm;
            final Connection con = Main.con;
	
            final String section = cm.getFrom(cm, con
                    ,"select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+Main.instructorID
                    ,"there is no section taught by instructor "+Main.instructorID
                    ,"select a section");
            if(section==null)
                return;

		setSize(200, 300);
		setLayout(new BorderLayout());
		final JTextField letter = new JTextField();
		final JTextField mark = new JTextField();
		JButton button = new JButton("Add");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.add(letter);
		panel.add(mark);
		
		panel.add(button);
		
                table = cm.CreateTable(con, "SELECT GRADING_CUTOFFS.LETTER_GRADE, GRADING_CUTOFFS.VALUE FROM GRADING_CUTOFFS WHERE GRADING_CUTOFFS.SEC_REF_NUM = "+section+"order by value desc");
                add(table,BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mark.getText().length()==0||letter.getText().length()==0)
					return;
				try {
					if(Integer.parseInt(mark.getText())<0){
						JOptionPane.showMessageDialog(new JFrame(), "the value must be positive",null,JOptionPane.ERROR_MESSAGE);
						return;
					}
					con.createStatement().executeQuery("INSERT INTO GRADING_CUTOFFS (LETTER_GRADE, VALUE, SEC_REF_NUM) VALUES ('"+letter.getText()+"', '"+mark.getText()+"', '"+section+"')");
					remove(table);					
					table = cm.CreateTable(con, "SELECT GRADING_CUTOFFS.LETTER_GRADE, GRADING_CUTOFFS.VALUE FROM GRADING_CUTOFFS WHERE GRADING_CUTOFFS.SEC_REF_NUM = "+section);
					add(table,BorderLayout.CENTER);
					setVisible(true);
				} catch (SQLException e1) {}
				  catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(new JFrame(), "the value must be numeric",null,JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		add(panel,BorderLayout.NORTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2);
		setResizable(false);
		setVisible(true);
	}
}
