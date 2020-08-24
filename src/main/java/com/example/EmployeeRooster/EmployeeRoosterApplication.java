package com.example.EmployeeRooster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class EmployeeRoosterApplication {


    @RequestMapping("/")
    String home() {
	String last = "";
	try{
	 Connection conn = ConnectionHelper.getConnection();
	 Statement stmt =  conn.createStatement();

	 String sql;
	 sql = "SELECT locationname FROM locations";
	 ResultSet rs = stmt.executeQuery(sql);

	 while(rs.next()){
	      last += rs.getString("locationname") + ", ";
	     System.out.println(", Last: " + last);
	 }
	 if(last.length() > 1){
	     last = last.substring(0,last.length() -2 );
	 }
	 rs.close();
	 stmt.close();
	 conn.close();
	}catch(Exception ex){
	    System.out.println("Exception: " + ex.getMessage());
	}
        return last;
    }


    @RequestMapping("/getEmps")
    String getEmployeesByDate(@RequestParam("locid")int locid, @RequestParam("date") Date date) {
	String last = "";
	System.out.println(", Last: " + date.getClass().getName());
	
	try{
	    
	 Connection conn = ConnectionHelper.getConnection();
	 Statement stmt =  conn.createStatement();

	 String sql;

	 sql = "select e.employeename from rooster r join employee e  on e.id = r.employeeid where '" + date.toString()+"' between startDate and endDate and location ="+locid;
	 ResultSet rs = stmt.executeQuery(sql);

	 while(rs.next()){

	      last += rs.getString("employeename") + ", ";
	     System.out.println(", Last: " + last);
	 }
	 if(last.length() > 1){
	     last = last.substring(0,last.length() -2 );
	 }

	 rs.close();
	 stmt.close();
	 conn.close();
	}catch(Exception ex){
	    System.out.println("Exception: " + ex.getMessage());
	}
        return last;
    }

    

    
    
    public static void main(String[] args) {
	try{
	    SpringApplication.run(EmployeeRoosterApplication.class, args);
	}
	catch(Exception ex){
	    System.out.println("SQLException: " + ex.getMessage());
	}
    }

}
