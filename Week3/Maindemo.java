
package Week3;

import java.awt.DisplayMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Maindemo {
	static String JdbcURL = "jdbc:mysql://localhost:3306/softdb";
    static String Username = "root";
    static String password = "root";
	static Connection con = null;
	public static void main(String[] args) throws Exception {
		Scanner input=new Scanner(System.in);
		
	      try {
	         con=DriverManager.getConnection(JdbcURL, Username, password);
	      }
	      catch(Exception e) {
	         e.printStackTrace();
	      }
	      int n=0;
	      do {

		      System.out.println("Enter which you want to access 1.Admin 2.Accountant 3.Exit ");
		      n=input.nextInt();
		      switch(n) {
		      case 1:
		    	  Admin.display(con);
		    	  break;
		    	  
		      case 2:
		    	  Accountant.display(con);
		    	  break;
		    	default:
		    		System.out.println("---------------------");
		    		break;
		      
		      
		      }
		      
		      
		      
		} while (n<3);

	}

}

