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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Accounts", "root","******");
            Statement stmt = conn.createStatement();
            String choice;
            String table;
            String sqlstmt;
            //String values;
            Scanner DBChoice = new Scanner(System.in);
            Printtables(conn);
            System.out.println("Which table do you want to preform an action on: ");
            table = DBChoice.nextLine();
            System.out.println("What DB action do you want to preform?");
            System.out.println("Input: i, View: v, Make Query: q, Create table: t, delete table: d, Remove Row: r");
            choice = DBChoice.nextLine();
            switch(choice){
                case "i":
                    Printcols(conn, table);
                    String values;
                    System.out.println("What values?");
                    values = DBChoice.nextLine();
                    String sql = "INSERT INTO "+ table +" " +
                    "VALUES ("+ values +")";
                    stmt.executeUpdate(sql);
                    break;
                    
                case "v":
                    System.out.println("What do you want to select from "+table +"?");
                    String select = DBChoice.nextLine();
                    ResultSet rs = stmt.executeQuery("SELECT "+ select +" FROM "+ table +"");
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
                    System.out.println("Write a query for "+table);
                    String query = DBChoice.nextLine();
                    ResultSet qr = stmt.executeQuery(query);
                    while (qr.next()){
                        String site = qr.getString(1);
                        System.out.println("Site: "+ site );
                        System.out.println();
                    }
                    qr.close();
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
    static void Printtables(Connection conn){
        try{
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tables = md.getTables(null, null, "%", null);
            while (tables.next()) {
                System.out.println(tables.getString(3));
            }
        }
        catch(Exception ex){
            
        }
    }
    static void Printcols(Connection conn, String table){
        try{
            DatabaseMetaData md = conn.getMetaData();
            ResultSet cols = md.getColumns(null, null,table, null );
            while (cols.next()) {
                System.out.print(cols.getString("TYPE_NAME"));
                System.out.println(cols.getString("COLUMN_NAME"));
            }
        }
        catch(Exception ex){
            
        }
    }
}