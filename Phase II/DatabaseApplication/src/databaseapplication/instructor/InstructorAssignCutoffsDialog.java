package databaseapplication.instructor;

import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import databaseapplication.CommonMethods;

public class InstructorAssignCutoffsDialog {
	
	public InstructorAssignCutoffsDialog() {
		CommonMethods cm = new CommonMethods();
		ArrayList<String> courses = null;
		int i_ID = 2;//TODO variable
		try {
			ResultSet r = cm.getConnection().createStatement().executeQuery("select REFRENCE_NUMBER from section where INSTRUCTOR_ID = "+i_ID);
				
			courses = new ArrayList<String>();
			while(r.next())
				courses.add(r.getString(1));
				
			System.out.println();
		} catch (SQLException e) {}
		if(courses.isEmpty()){
			JOptionPane.showMessageDialog(null,"This instuctor dont have any section");
			
			return;
		}
		JComboBox<Object> sections = new JComboBox<Object>(courses.toArray());
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(3,0));
		
		f.add(new JTextField("Select a section"));
		f.add(sections);
		f.setBounds(400, 300, 100, 100);
		f.setVisible(true);
		Button button = new Button("Select");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) sections.getSelectedItem();
				f.dispose();
				f.removeAll();
				
				//TODO
				
				f.setSize(400, 400);
				f.setVisible(true);
			}
		});
		f.add(button);
		
		
		
	}
}
