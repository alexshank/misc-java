package sample;

import java.sql.*;

/**
 * Created by Alex on 6/28/2016.
 * DBManager class that contains methods that access a database
 */
public class DBManager
{

    /*
     * URL that must be specific to the database that is being accessed
     * string needs the correct syntax with IP address (in this case: localhost), login info, database name, and port info
     * (port default is 1433)!!!
     * only really technical part of accessing databases is finding this information and establishing a connection.
     */
    private static String connectionUrl = "jdbc:sqlserver://localhost;user=sa;password=password;databaseName=List_Media;port=1433";

    // method for when your SQL statement will return a ResultSet (select, etc...)
    public static ResultSet ExecuteQuery(String cmd) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection con = DriverManager.getConnection(connectionUrl);
        Statement s = con.createStatement();
        return s.executeQuery(cmd);
    }

    // method for when your SQL statements will not return a ResultSet (insert, update, delete, etc...)
    public static void Execute(String cmd) throws SQLException, ClassNotFoundException
    {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection con = DriverManager.getConnection(connectionUrl);
        Statement s = con.createStatement();
        s.executeUpdate(cmd);

    }

}// End of DBManager class

