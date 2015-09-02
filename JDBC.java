package jdbc;
import java.sql.*;
import java.util.Scanner;

public class JDBC {
	public static void main(String args[]){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		
		try{
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Accounts", "root","Peanut@1");
			Statement stmt = conn.createStatement();
			String choice;
			String table;
			String sqlstmt;
			String values;
			Scanner DBChoice = new Scanner(System.in);
			System.out.println("What DB action do you want to preform?");
			System.out.println("Input: i, View: v, Make Query: q, Create table: t, delete table: d, Remove Row: r");
			choice = DBChoice.nextLine();
			switch(choice){
			case "i":
				System.out.println("Which table: ");
				table = DBChoice.nextLine();
				System.out.println("What values?");
				values = DBChoice.nextLine();
				String sql = "INSERT INTO "+ table +" " +
		                   "VALUES ("+ values +")";
				stmt.executeUpdate(sql);
				break;
			case "v":
				ResultSet rs = stmt.executeQuery("SELECT * FROM Webaccounts");
				while (rs.next()){
					String site = rs.getString(1);
					String username = rs.getString(2);
					String password = rs.getString(3);
					System.out.println("Site: "+ site );
					System.out.println("Username: " + username);
					System.out.println("Password: " + password);
					System.out.println();
				}
				rs.close();
				break;
			case "q":
				break;
			case "t":
				break;
			case "d":
				break;
			case "r":
				break;
			}
			
			stmt.close();
			conn.close();
			//System.out.println("worked");
		}
		catch (Exception ex){
			System.out.println("SQLException: " + ex.getMessage());
		}
	}
}
