package databaseapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

public class CommonMethods {
	
	public JTable CreateTable(Connection conn,String query) throws SQLException {
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		
		//while(r.next());//no need for this, just use r.last
		r.last();
		JTable a = new JTable(r.getRow()+1,r.getMetaData().getColumnCount());
		//r = s.executeQuery(query);//Also why you are executing the query again? no need to do that
		r.first();
		for(int i=0;i<a.getColumnCount();i++)//attributes
			a.setValueAt(r.getMetaData().getColumnLabel(i+1), 0, i);
		
		for(int j=1;j<a.getRowCount();j++){
			r.next();	
			for(int i=0;i<a.getColumnCount();i++)
				a.setValueAt(r.getString(i+1), j, i);
		}//for rows
		
		return a;//table
	}
}
