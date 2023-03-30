/*  
 *  DatabaseHandler.java
 *  
 *  Description: Singleton that connects with SQL database and handles queries.
 *
*/

package main;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler 
{
    private static DatabaseHandler instance = null;
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String username = "s_lsa105";
    private String password = "P3yd27fFmTJ6Q43Q";

    private DatabaseHandler()
    {
        connect();
    }

    public static DatabaseHandler getInstance()
    {
        if (instance == null) { instance = new DatabaseHandler(); }
        return instance;
    }

    private void connect()
    {
        System.out.println("Connecting with username: " + username + " and password: " + password);
        String connectionURL = "jdbc:sqlserver://cypress.csil.sfu.ca;" + "user = " + username + ";" + "password =" + password;

        try {
            connect = DriverManager.getConnection(connectionURL);
            System.out.println("Connected to database");
        }
        catch(SQLException sqlError) {
            System.out.println("Failed to connect to database: " + sqlError.getMessage());
            return;
        }
    }

    public void login(String userId)
    {
        try {
			preparedStatement = connect.prepareStatement("SELECT * FROM user_yelp U WHERE U.user_id = '" + userId + "'");
			resultSet = preparedStatement.executeQuery();
			
			System.out.println("\nLogged into user: ");
			
			while (resultSet.next()) {
                String tmp = "";
				tmp = resultSet.getString("user_id");
				System.out.print(tmp + "\n");
			}
			resultSet.close();
		}
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return;
        }
    }
}
