package databaseapplication.instructor;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorDeleteGradingComponenetDialog extends JFrame{
	
	private String var1;
	private ArrayList<String> numbers;
	private JFrame f;

	InstructorDeleteGradingComponenetDialog(){
		var1 = "1234";
		String var = "1";
		numbers = null;
		ArrayList<String> courses = null;
		CommonMethods cm = new CommonMethods();
		Connection con = cm.getConnection();
		
		try {
			ResultSet r = con.createStatement().executeQuery("select distinct course_number from grading_component where INSTRUCTOR_ID = "+var);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no course assigned to supervised by instructor "+var);
				return;
				}
			

			courses = new ArrayList<String>();
			courses.add("Select Course");
			do{
				courses.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		JComboBox<Object> box = new JComboBox<>(courses.toArray());
		f = new JFrame();
		f.setLayout(new GridLayout(2,0));
		f.add(box);
		
		Button button = new Button("Select");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) box.getSelectedItem();
				if(!selected.equals("Select Course")){
					f.dispose();	
				
				try {
					ResultSet r = con.createStatement().executeQuery("select name from grading_component where course_number = "+var1);
					if(!r.next()){
						JOptionPane.showMessageDialog(null,"there is no grading component in "+var1);
						return;
						}
					
					numbers = new ArrayList<String>();
					do{
						numbers.add(r.getString(1));
					}while(r.next());
					
				} catch (SQLException e1){}
				
				setBounds(400, 300, 100, 100);
				setLayout(new GridLayout(3,0));
				JTextField text = new JTextField("select componenet");
				text.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
				text.setEditable(false);
				add(text);
				JComboBox<Object> sections = new JComboBox<Object>(numbers.toArray());
				add(sections);
				
				Button button = new Button("Select");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String selected = (String) sections.getSelectedItem();
						try {
							con.createStatement().executeQuery("delete from grading_component where course_number = "+var1+"and name = \'"+selected+"\'");
							JOptionPane.showMessageDialog(null,"succesfully deleted "+selected);
							dispose();
							return;
						} catch (SQLException e1) {}				
					}
				});
				add(button);
				Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				setLocation(dim.width/2-f.getSize().width/2, dim.height/2-f.getSize().height/2);
				setResizable(false);
				setVisible(true);
				}
			}
		});
		f.add(button);
		f.setSize(100, 100);
		f=cm.CentralizeFrame(f);
		
		
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
}
