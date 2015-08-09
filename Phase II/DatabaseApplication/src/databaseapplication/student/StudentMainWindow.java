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





public class StudentMainWindow extends JFrame implements ActionListener
{
	public static int height = 1200;
	public static int width = 400;
	JPanel pane = new JPanel();
	JPanel pane1 = new JPanel();
	JPanel pane2 = new JPanel();
	JPanel pane3 = new JPanel();
  JFrame frame = new JFrame();
  JLabel lb = new JLabel("Enter a major");
  JButton b1 = new JButton("List Courses");
  static TextField  lblInput = new TextField  (20);
  TextArea  lblOutput0 = new TextArea  ();
  TextArea  lblOutput1 = new TextArea  ();
  TextArea  lblOutput2 = new TextArea  ();


	  static String connStr ;
	  static Connection conn ;
	  static String query ;
	  static Statement s ;
	  static ResultSet r ;

	  public StudentMainWindow()
  {
    super("Gui");
    setSize(height,width);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    pane.add(lb);
    pane.add(lblInput);
    pane.add(lblOutput0);
    pane.add(b1);
    pane.setBorder(new javax.swing.border.TitledBorder("Registering courses"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new java.awt.GridLayout(0, 1));
    pane1.add(lb);
    pane1.add(lblInput);
    pane1.add(lblOutput0);
    pane1.add(b1);
    pane1.setBorder(new javax.swing.border.TitledBorder("Dropping courses"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new java.awt.GridLayout(0, 1));
    pane2.add(lb);
    pane2.add(lblInput);
    pane2.add(lblOutput0);
    pane2.add(b1);
    pane2.setBorder(new javax.swing.border.TitledBorder("Viewing grades"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new java.awt.GridLayout(0, 1));
    pane3.add(lb);
    pane3.add(lblInput);
    pane3.add(lblOutput0);
    pane3.add(b1);
    pane3.setBorder(new javax.swing.border.TitledBorder("Other functions"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(new java.awt.GridLayout(0, 1));
    frame.getContentPane().add(pane);
    frame.getContentPane().add(pane1);
    frame.getContentPane().add(pane2);
    frame.getContentPane().add(pane3);
    frame.setSize(height,width);
    frame.setVisible(true);
    lblInput.requestFocus();
    lblOutput0.requestFocus();
    add(frame);
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
				lblOutput0.setText("No Student with this major");
			do{
				lblOutput0.setText(lblOutput0.getText()+r.getString(1)+" 		"+r.getString(2)+" 		"+r.getString(3)+" 		"+"\n");
			} while(r.next());





			}
			catch (Exception e){
			}
	
	}
	
	
	
	public static void main(String args []) throws SQLException
	{

		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","201236760");
	   


	  new StudentMainWindow();

	}
	  
}


