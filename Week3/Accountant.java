package freesoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Accountant {
	static Scanner input=new Scanner(System.in);
	public static void display(Connection conn) throws SQLException {
		System.out.println("Please provide the function to do: ");
		System.out.println("1.	Add new Student.\r\n"
				+ "2.	View existing Student.\r\n"
				+ "3.	Edit student details.\r\n"
				+ "4.	Delete student records.\r\n"
				+ "5.   Check due fee \r\n"+
				  "6.   Logout");
		int n=input.nextInt();
		switch (n) {
		case 1:
			System.out.println("Enter id: ");
			int id=input.nextInt();
			System.out.println("Enter name: ");
			String name=input.next();
			System.out.println("Enter email: ");
			String email=input.next();
			System.out.println("Enter course");
			String course=input.next();
			System.out.println("Enter fee");
			double fee=input.nextDouble();
			System.out.println("Enter paid");
			double paid=input.nextDouble();
			System.out.println("Enter due");
			double due=input.nextDouble();
			System.out.println("Enter address");
			String address=input.next();
			System.out.println("Enter phno");
			long phone=input.nextLong();
			String query="insert into admin values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, course);
			ps.setDouble(5, fee);
			ps.setDouble(6, paid);
			ps.setDouble(7, due);
			ps.setString(8, address);
			ps.setLong(9, phone);
			int i=ps.executeUpdate();
			if(i>0) {
				System.out.println("Success");
			}
			break;
		case 2:
			String query1="select * from student";
			PreparedStatement ps1=conn.prepareStatement(query1);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()) {
				System.out.println("ID : "+rs.getInt(1)+" "+" NAME: "+" "+rs.getString(2)+" "+"EMAIL: "+" "+rs.getString(3)+
				"COURSE : "+rs.getString(4)+" "+" FEE: "+" "+rs.getDouble(5)+" "+"PAID: "+" "+rs.getDouble(6)+
				"Due: "+rs.getDouble(7)+" "+" Address: "+" "+rs.getString(8)+" "+"Phone: "+" "+rs.getLong(9)
						);
			}
			break;
		case 3:
			System.out.println("Enter the details to update: 1.Name 2.email 3.phno ");
			System.out.println("Enter choice: ");
			int r=input.nextInt();
			switch (r) {
			case 1:
				String query2="update student set name=? where id=? ";
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
				String query3="update student set email=? where id=? ";
				System.out.println("Enter the ID");
				int newid1=input.nextInt();
				System.out.println("Enter new email: ");
				String npass=input.next();
				PreparedStatement p3=conn.prepareStatement(query3);
				p3.setString(1, npass);
				p3.setInt(2, newid1);
				int t=p3.executeUpdate();
				if(t>0) {
					System.out.println("Updated successfully");
				}
				break;
			case 3:
				String query5="update student set phone=? where id=? ";
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
			default:
			    break;
			}
			
		case 4:
			System.out.println("Enter the student id to delete");
			int n1=input.nextInt();
			String query3="delete from student where id=?";
			PreparedStatement p3=conn.prepareStatement(query3);
			p3.setInt(1, n1);
			int h=p3.executeUpdate();
			if(h>0) {
				System.out.println("Deleted successfully");
			}
			break;
		case 5:
			System.out.println("Students with due");
			String query6="select * from students where due>0";
			PreparedStatement p6=conn.prepareStatement(query6);
			ResultSet rs1=p6.executeQuery();
			while(rs.next()) {
				System.out.println("ID : "+rs.getInt(1)+" "+" NAME: "+" "+rs.getString(2)+" "+"EMAIL: "+" "+rs.getString(3)+
				"COURSE : "+rs.getString(4)+" "+" FEE: "+" "+rs.getDouble(5)+" "+"PAID: "+" "+rs.getDouble(6)+
				"Due: "+rs.getDouble(7)+" "+" Address: "+" "+rs.getString(8)+" "+"Phone: "+" "+rs.getLong(9)
						);
		default:
			break;
		}
	}
}
