/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.instructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTable;

import databaseapplication.CommonMethods;

/**
 *
 * @author Ibrahim
 * 
 * @author ibra5_000
 * @version v1.1
 * 
 */

public class InstructorMainWindow extends JFrame{
    /*
  	//TODO assigning grade cutoffs (view , update , delete)
  	//TODO assigning grading componenets (view , update , delete)
  	//TODO assinging points (view , update , delete)
 	//view students in a section 
     * 
     */
	//private static Connection conn;	
	public InstructorMainWindow() {

	
		new InstructorViewStudentsDialog();
//		this.setSize(500, 500);		//TODO
//		JTable a = null;
//		try {
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ibra5him","1415620.");
//			//TODO keep this "jdbc:oracle:thin:@localhost:1521:xe"
//			a = new CommonMethods().CreateTable(conn,"select fname,minit,lname,ssn from employee");
//			
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		this.add(a);
//		this.setVisible(true);
	}
	
}
