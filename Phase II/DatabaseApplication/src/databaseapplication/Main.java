/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseapplication;

import Frameworks.ConnectionManager;

import java.util.LinkedList;

import javax.swing.UIManager;

import databaseapplication.instructor.InstructorMainWindow;

/**
 *
 * @author Ibrahim
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	//new InstructorMainWindow();
        // TODO code application logic here
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){}
       // SuperManager.startApp();
        ConnectionManager m = new ConnectionManager("jdbc:derby://localhost:1527/AppDB","ibrahim","ibrahim");
        m.openConnection();
        m.insert("college","'A','Innformation','ccse'");
    }
    
}
