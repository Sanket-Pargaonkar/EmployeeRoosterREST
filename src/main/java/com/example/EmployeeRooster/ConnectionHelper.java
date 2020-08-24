package com.example.EmployeeRooster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException; 
public class ConnectionHelper
{
    public static Connection conn = null;
    public static Connection  getConnection(){
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    conn =
		DriverManager.getConnection("jdbc:mysql://localhost/EmpRooster?" +
					    "user=root1&password=root1");
	}
	catch (SQLException ex) {
	    // handle any errors
	    System.out.println("SQLException: " + ex.getMessage());
	    // System.out.println("SQLState: " + ex.getSQLState());
	    // System.out.println("VendorError: " + ex.getErrorCode());
	}
	catch(Exception ex){
		System.out.println("Exception: " + ex.getMessage());
        }
	return conn;
    }
}
