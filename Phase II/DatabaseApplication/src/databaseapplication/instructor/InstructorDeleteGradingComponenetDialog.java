package databaseapplication.instructor;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import databaseapplication.CommonMethods;

public class InstructorDeleteGradingComponenetDialog{

	InstructorDeleteGradingComponenetDialog(){
		
		String var = "1";

		ArrayList<String> courses = null;
		CommonMethods cm = new CommonMethods();
		Connection con = cm.getConnection();
		
		try {
			ResultSet r = con.createStatement().executeQuery("select distinct course_number from grading_component where INSTRUCTOR_ID = "+var);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no course assigned to be supervised by instructor "+var);
				return;
			}
			courses = new ArrayList<String>();
			do{
				courses.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e){}
		
		String course = cm.Combo(courses.toArray(), "select course");
		if(course==null)
			return;
		ArrayList<String> numbers = null;
		try {
			ResultSet r = con.createStatement().executeQuery("select name from grading_component where course_number = "+course);
			if(!r.next()){
				JOptionPane.showMessageDialog(null,"there is no grading component in "+course);
				return;
			}
			numbers = new ArrayList<String>();
			do{
				numbers.add(r.getString(1));
			}while(r.next());
			
		} catch (SQLException e1){}
		
		String selected = cm.Combo(numbers.toArray(), "select Component");
		if(selected==null)
			return;
				
		try {
			con.createStatement().executeQuery("delete from grading_component where course_number = "+course+"and name = \'"+selected+"\'");
			JOptionPane.showMessageDialog(null,"succesfully deleted "+selected+" grading component from course numbered"+course);
		} catch (SQLException e1) {}
	}
}
