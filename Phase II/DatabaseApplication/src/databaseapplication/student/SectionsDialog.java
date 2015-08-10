package databaseapplication.student;



import databaseapplication.CommonMethods;
import databaseapplication.instructor.Main;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aqeil54
 */
public class SectionsDialog extends JDialog {
JFrame f = new JFrame("The available sections");



    public SectionsDialog() throws SQLException  {
    Connection conn ;
    String query ;
    conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","201236760");      
         
      String Rnum = "";
        String Snum = "";
        String Iid = "";
        f.setLayout(new FlowLayout());
        DefaultTableModel model = new DefaultTableModel();
        String[] columnNamess = {"REFRENCE_NUMBER","SECTION_NUMBER", "INSTRUCTOR_ID"};
        model.setColumnIdentifiers(columnNamess);
        JTable table = new JTable(model);
        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    		
      query = "SELECT * FROM SECTION  where REFRENCE_NUMBER = \'" + StudentMainWindow.lblInput1.getText()+"\'";
      StudentMainWindow.s = conn.createStatement ();
      StudentMainWindow.r = StudentMainWindow.s.executeQuery(query);
       
            try{
            int i = 0;
            
            while (StudentMainWindow.r.next()) {
                Rnum = StudentMainWindow.r.getString("REFRENCE_NUMBER");
                Snum = StudentMainWindow.r.getString("SECTION_NUMBER");
                Iid = StudentMainWindow.r.getString("INSTRUCTOR_ID");
                model.addRow(new Object[]{Rnum, Snum, Iid});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
       

                CommonMethods cm = new CommonMethods();
                //final Connection con = Main.con;
		
                final String course = cm.getFrom(cm, conn//To Edit Substring
         	   	 ,"select REFRENCE_NUMBER from section"
            	   	 ,"there is no section assigned to instructor "
               		 ,"select a section");
                if(course ==null)
                    return;

            
     f.add(scroll);  
    f.setSize(500, 500);
    f.setResizable(false);
    f.setVisible(true);
    
     

                
    

    }
 
    }

   



