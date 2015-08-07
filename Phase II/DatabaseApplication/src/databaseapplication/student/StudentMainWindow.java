/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication.student;

import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.sql.*;
import java.util.*;


/**
 *
 * @author Ibrahim
 */


public class StudentMainWindow extends JFrame implements ActionListener
{

  JPanel pane = new JPanel();
  JLabel lb = new JLabel("Enter a major");
  JButton b1 = new JButton("List Students");
  static TextField  lblInput = new TextField  (20);
  TextArea  lblOutput = new TextArea  ();
	  static String connStr ;
	  static Connection conn ;
	  static String query ;
	  static Statement s ;
	  static ResultSet r ;

	  public StudentMainWindow()
  {
    super("Gui");
    setSize(400,200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    pane.add(lb);
    pane.add(lblInput);
    pane.add(b1);
    add(lblOutput);
    add(pane,BorderLayout.NORTH);
    setVisible(true);
    b1.addActionListener(this);
	setResizable(false);
  }

	@Override
	public void actionPerformed(ActionEvent ev) {
		
		try{
		query = "SELECT * FROM STUDENT WHERE code = \'" + lblInput.getText()+"\'";
		   s = conn.createStatement ();
		   r = s.executeQuery(query);
	      if (ev.getActionCommand().equals("List Students"))
			if(!r.next())
			lblOutput.setText("No Student with this major");
			do{
			lblOutput.setText(lblOutput.getText()+r.getString(1)+" 		"+r.getString(2)+" 		"+r.getString(3)+" 		"+"\n");
			} while(r.next());





			}
			catch (Exception e){
			}
	
	}
	
	
	
	public static void main(String args []) throws SQLException
	{

		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","201236760");
	   conn = DriverManager.getConnection(connStr,"system","201236760");


	  new StudentMainWindow();

	}
	  
}


