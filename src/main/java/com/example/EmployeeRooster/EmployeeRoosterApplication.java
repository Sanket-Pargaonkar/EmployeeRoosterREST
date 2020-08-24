package com.example.EmployeeRooster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import org.springframework.http.ResponseEntity;
import org.springframework.format.*;
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

	 //STEP 5: Extract data from result set
	 while(rs.next()){
	     //Retrieve by column name
	      last += rs.getString("locationname") + ", ";

	     //Display values
	     System.out.println(", Last: " + last);
	 }
	 if(last.length() > 1){
	     last = last.substring(0,last.length() -2 );
	 }
	 //STEP 6: Clean-up environment
	 rs.close();
	 stmt.close();
	 conn.close();
	}catch(Exception ex){
	    System.out.println("Exception: " + ex.getMessage());
	}
        return last;
    }


    @RequestMapping("/getEmps")
    String getEmployeesByDate(@RequestParam("locid")int locid, @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
	String last = "";
	try{
	    
	 Connection conn = ConnectionHelper.getConnection();
	 Statement stmt =  conn.createStatement();

	 String sql;
	 sql = "SELECT locationname FROM locations";
	 ResultSet rs = stmt.executeQuery(sql);

	 //STEP 5: Extract data from result set
	 while(rs.next()){
	     //Retrieve by column name
	      last += rs.getString("locationname") + ", ";

	     //Display values
	     System.out.println(", Last: " + last);
	 }
	 if(last.length() > 1){
	     last = last.substring(0,last.length() -2 );
	 }
	 //STEP 6: Clean-up environment
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
