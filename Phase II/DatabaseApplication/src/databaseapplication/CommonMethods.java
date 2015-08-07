package databaseapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CommonMethods {
	
	public Connection getConnection(){
		String[][] users = {{"jdbc:derby://localhost:1527/ProjectDB","ibrahim","ibrahim"},
							{"jdbc:oracle:thin:@localhost:1521:xe","ibra5him","1415620."},
							{"jdbc:oracle:thin:@localhost:1521:xe","system","201236760"},
							{"jdbc:oracle:thin:@ics-db.ccse.kfupm.edu.sa:1521:xe","s201224780","201224780"}
							};
		
		for (String[] x : users) {
			try {
				return DriverManager.getConnection(x[0],x[1],x[2]);					
			} catch (SQLException e) {}			
		}
		return null;//unsuccesful
	}

	public JTable CreateTable(Connection conn,String query) throws SQLException {
		Statement s = conn.createStatement();
		ResultSet r = s.executeQuery(query);
		
		while(r.next());
		
		JTable a = new JTable(r.getRow()+1,r.getMetaData().getColumnCount());
		a.setRowHeight(20);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		a.setDefaultRenderer(String.class, centerRenderer);
		r = s.executeQuery(query);
		for(int i=0;i<a.getColumnCount();i++){//attributes
			a.setValueAt(r.getMetaData().getColumnLabel(i+1), 0, i);
			a.getColumnModel().getColumn(i).setPreferredWidth(r.getMetaData().getColumnLabel(i+1).length()*10);
		}
		
		for(int j=1;j<a.getRowCount();j++){
			r.next();	
			for(int i=0;i<a.getColumnCount();i++){
				a.setValueAt(r.getString(i+1), j, i);
				if(r.getString(i+1)!=null)
					if(a.getColumnModel().getColumn(i).getPreferredWidth()<r.getString(i+1).length()*10)
						a.getColumnModel().getColumn(i).setPreferredWidth(r.getString(i+1).length()*10);
			}
		}//for rows
		
		return a;//table
	}


}
