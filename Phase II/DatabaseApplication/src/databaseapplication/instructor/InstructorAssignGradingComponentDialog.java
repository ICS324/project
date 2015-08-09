/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.instructor;

import databaseapplication.CommonMethods;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author ibra5_000
 */
class InstructorAssignGradingComponentDialog extends JFrame {
private JTable table;
    public InstructorAssignGradingComponentDialog()  {
        
		final CommonMethods cm = Main.cm;
                final Connection con = Main.con;
		
                final String course = cm.getFrom(cm, con
         	   	    ,"select \"number\",title from course where \"number\" in (select distinct floor(REFRENCE_NUMBER/10) from section where INSTRUCTOR_ID = "+Main.instructorID+")"
            	   	 ,"there is no section assigned to instructor "+Main.instructorID
               		 ,"select a section");
                if(course ==null)
                    return;
                //final String course = section.substring(0, section.length()-1);/*		FIXME		*/
                
                setSize(300, 300);
		setLayout(new BorderLayout());
		final JTextField name = new JTextField();
		final JTextField max = new JTextField();
                final JTextField weight = new JTextField();
		JButton button = new JButton("Add");
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout());
		panel.add(name);
		panel.add(max);
		panel.add(weight);
                
		panel.add(button);
                table = cm.CreateTable(con, "select name,max_points,weight from grading_component where COURSE_NUMBER = +"+course.split(",")[0]+" order by id asc");
                add(table,BorderLayout.CENTER);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(name.getText().length()==0||max.getText().length()==0||weight.getText().length()==0)
					return;
				try {
					if(Integer.parseInt(max.getText())<0){
						JOptionPane.showMessageDialog(new JFrame(), "the value must be positive",null,JOptionPane.ERROR_MESSAGE);
						return;
					}
                                        else if(Integer.parseInt(weight.getText())<0){
						JOptionPane.showMessageDialog(new JFrame(), "the value must be positive",null,JOptionPane.ERROR_MESSAGE);
						return;
					}
                                        ResultSet r = con.createStatement().executeQuery("select max(id)+1 from grading_component");
                                        r.next();
                                        String id = r.getString(1);
                                        if(id==null)
                                            id="1";
                                        System.out.println(id);
					con.createStatement().executeQuery("INSERT INTO GRADING_COMPONENT  VALUES ('"+id+"', '"+name.getText()+"', '"+max.getText()+"', '"+weight.getText()+"', '"+course+"', '"+Main.instructorID+"')");
					remove(table);					
					table = cm.CreateTable(con, "select name,max_points,weight from grading_component where COURSE_NUMBER = +"+course+" order by id asc");
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
