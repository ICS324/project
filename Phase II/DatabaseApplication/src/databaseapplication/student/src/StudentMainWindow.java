



import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author aqeil54
 */


public class StudentMainWindow extends JFrame implements ActionListener
{
JFrame f = new JFrame("The available sections");
public static int height = 250;
public static int width = 800;
int StudentID =1;

JPanel pane1 = new JPanel();

JPanel pane2 = new JPanel();
JPanel pane3 = new JPanel();
public  GetCurrentDate gd;

JLabel lb1 = new JLabel("Enter the course number:");
JLabel lb2 = new JLabel("Enter the course number:");
JLabel lb3 = new JLabel("Enter Your ID");
JLabel lb4 = new JLabel();

JButton b1 = new JButton("Register");
JButton b22 = new JButton();
JButton b12 = new JButton("View Courses");
JButton b13 = new JButton("Get Sections");
JButton b2 = new JButton("Drop");
JButton b3 = new JButton("View");


ArrayList<String> elements = new ArrayList<>();
String[] strigs;
DefaultComboBoxModel model ;
protected static TextField  lblInput1 = new TextField  (20);
protected static TextField  lblInput2 = new TextField  (20);
protected static TextField  lblInput3 = new TextField  (20);

protected  TextArea  lblOutput1 = new TextArea  ();
TextArea  lblOutput2 = new TextArea  ();
TextArea  lblOutput3 = new TextArea  ();


	  protected static String connStr ;
	  protected static Connection conn ;
	  protected String query ;
          protected String query2 ;
          protected String query3 ;
	  protected static Statement s ;
	  protected static ResultSet r ;
          protected static ResultSet r2 ;
          protected static ResultSet r3 ;
           
	  public StudentMainWindow() throws SQLException
  {
    super("Student Main Window");
    f.setSize(width,height);
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new FlowLayout());
    pane1.setLayout(new GridLayout(0, 4));
    pane1.add(lb1);
    pane1.add(lblInput1);
    pane1.add(b1);
    pane1.add(b12);
    pane1.add(b13);
    pane1.add(lb4);
    
    //Combo(CourseNum);
    
    //pane1.add(c1);
    pane1.setBorder(new javax.swing.border.TitledBorder("Registering courses"));
    
    f.add(pane1);
    

    
    pane2.add(lb2);
    pane2.add(lblInput2);
    pane2.add(b2);
    pane2.setBorder(new javax.swing.border.TitledBorder("Dropping courses"));
    
   f.add(pane2);
    
    
   pane3.add(lb3);
    pane3.add(lblInput3);
    pane3.add(b3);
    pane3.setBorder(new javax.swing.border.TitledBorder("Viewing grades"));
    
   f.add(pane3);
    
    
   
    f.setVisible(true);
    b1.addActionListener(this);
    b12.addActionListener(this);
    b1.addActionListener(this);
    b13.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
	setResizable(false);
  }

	@Override
	public void actionPerformed(ActionEvent ev) {
		
        
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","201236760");
            if (ev.getSource() == b22){
            
           
            }
            else if (ev.getSource() == b1){ 
            int ss =Integer.parseInt(lblInput1.getText());
            query = "INSERT into ENROLLMENT (REG_DATE, STUDENT_ID, SECTION_REFRENCE_NUMBER) values ('2/2/2014','"+StudentID +"' ,'"+ss+"') ";           
                   s = conn.createStatement();
                   r = s.executeQuery(query);               
            }    
            else if (ev.getSource() == b12){
             new StudentViewCoursesDialog();
              }
            else if (ev.getSource() == b13){
            
           new SectionsDialog();

            
               }
            
            
            else if (ev.getSource() == b2){
            int ss2 =Integer.parseInt(lblInput2.getText());
            query = "DELETE from ENROLLMENT whrere SECTION_REFRENCE_NUMBER = \'" + lblInput1.getText()+"\'";
            
                   s = conn.createStatement ();
                   r = s.executeQuery(query);
                             
            }
            else if (ev.getSource() == b3){
            new StudentsGradesDialog(); 
            }
            
            else{}       
            }

            catch (Exception e){
            }


            }	


	
	
	public static void main(String args []) throws SQLException 
	{

           


	  new StudentMainWindow();

	}
	  
}


