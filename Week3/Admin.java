
ppackage Week3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Admin {
	
	static Scanner input=new Scanner(System.in);
	public static void display(Connection conn) throws SQLException {
		System.out.println("Please provide the function to do: ");
		System.out.println("1.	Add new accountants.\r\n"
				+ "2.	View existing accountants.\r\n"
				+ "3.	Edit accountant details.\r\n"
				+ "4.	Delete accountants.\r\n"
				+ "5.   Logout ");
		int n=input.nextInt();
		switch (n) {
		case 1:
			System.out.println("Enter id: ");
			int id=input.nextInt();
			System.out.println("Enter name: ");
			String name=input.next();
			System.out.println("Enter email");
			String email=input.next();
			System.out.println("Enter phone");
			long phno=input.nextLong();
			System.out.println("Enter password: ");
			String password=input.next();
			String query="insert into accountant values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setLong(4, phno);
			ps.setString(5, password);
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("Success");
			}
			break;
		case 2:
			String query1="select * from accountant";
			PreparedStatement ps1=conn.prepareStatement(query1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()) {
				System.out.println("ID : "+rs.getInt(1)+" "+"NAME: "+" "+rs.getString(2)+" "+"Email: "+" "+rs.getString(3)+" "+"Phno: "+rs.getLong(4)+" "+"Password: "+rs.getString(5));
			}
			break;
		case 3:
			System.out.println("Enter the details to update: 1.name 2.Email 3.phno 4.password ");
			System.out.println("Enter choice: ");
			int r=input.nextInt();
			switch (r) {
			case 1:
				String query2="update accountant set name=? where id=? ";
				System.out.println("Enter the ID");
				int newid=input.nextInt();
				System.out.println("Enter new name: ");
				String nuser=input.next();
				PreparedStatement p2=conn.prepareStatement(query2);
				p2.setString(1, nuser);
				p2.setInt(2, newid);
				int k=p2.executeUpdate();
				if(k>0) {
					System.out.println("Updated successfully");
				}
				break;
			case 2:
				String query3="update accountant set email=? where id=? ";
				System.out.println("Enter the ID");
				int newid1=input.nextInt();
				System.out.println("Enter new email: ");
				String email1=input.next();
				PreparedStatement p3=conn.prepareStatement(query3);
				p3.setString(1, email1);
				p3.setInt(2, newid1);
				int k1=p3.executeUpdate();
				if(k1>0) {
					System.out.println("Updated successfully");
				}
				
				break;
			case 3:
				String query5="update accountant set phone=? where id=? ";
				System.out.println("Enter the ID");
				int newid5=input.nextInt();
				System.out.println("Enter new phone: ");
				long ph=input.nextLong();
				PreparedStatement p5=conn.prepareStatement(query5);
				p5.setLong(1, ph);
				p5.setInt(2, newid5);
				int k5=p5.executeUpdate();
				if(k5>0) {
					System.out.println("Updated successfully");
				}
				break;
				
				
			case 4:
				String query4="update accountant set password=? where id=? ";
				System.out.println("Enter the ID");
				int newid4=input.nextInt();
				System.out.println("Enter new password: ");
				String npass=input.next();
				PreparedStatement p4=conn.prepareStatement(query4);
				p4.setString(1, npass);
				p4.setInt(2, newid4);
				int t=p4.executeUpdate();
				if(t>0) {
					System.out.println("Updated successfully");
				}
				break;
				
			default:
			    break;
			}
			break;
		case 4:
			System.out.println("Enter the account id to delete");
			int n1=input.nextInt();
			String query3="delete from accountant where id=?";
			PreparedStatement p3=conn.prepareStatement(query3);
			p3.setInt(1, n1);
			int h=p3.executeUpdate();
			if(h>0) {
				System.out.println("Deleted successfully");
			}
			break;
		default:
			break;
		}
	}
	
}
