package utilities;

import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;

public class sqliteConnection {
	//Connection conn=null;
	public static Connection dbConnector() 
	{
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/unirome", "phpmyadmin", "root123");
			//Class.forName("org.sqlite.JDBC");
//			JOptionPane.showMessageDialog(null, "Successfully Connected!!");
			//Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\sqlite3\\databases\\mydatabase.sqlite");
			return conn;
		}
		catch(Exception e) 
		{
//			JOptionPane.showMessageDialog(null, "Connection Failed!!!!!");
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
	}
	
}
