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
import java.util.ArrayList;

import models.UserModel;

public class DatabaseHandler 
{
    private static DatabaseHandler instance = null;
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String username = "s_lsa105";
    private String password = "P3yd27fFmTJ6Q43Q";
    private String loginId = "";
    private String loginName = "";
    private ArrayList<UserModel> users = new ArrayList<UserModel>();

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

    public String login(String userId)
    {
        try {
			preparedStatement = connect.prepareStatement("SELECT U.user_id, U.name FROM user_yelp U WHERE U.user_id = '" + userId + "'");
			resultSet = preparedStatement.executeQuery();

            if(resultSet.next() == false) { return "User ID not found"; }
			do {
				loginName = resultSet.getString("name");
                loginId = resultSet.getString("user_id");
				System.out.print("\nLogged into user: " + loginName + "\n");
			} while(resultSet.next());

			resultSet.close();
            return "Welcome " + loginName + "!";
		}
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public String searchUser(String name, boolean useful, boolean funny, boolean cool)
    {
        try {
            preparedStatement = connect.prepareStatement(
                "SELECT * FROM user_yelp U WHERE U.name LIKE '%" + name + "%'" +
                "AND " + (useful ? "U.useful > 0" : "U.useful = 0") +
                "AND " + (funny ? "U.funny > 0" : "U.funny = 0") +
                "AND " + (cool ? "U.cool > 0" : "U.cool = 0") +
                "ORDER BY U.name"
            );
			resultSet = preparedStatement.executeQuery();

            if(resultSet.next() == false) { return "No users matching criteria found!"; }
            users.clear();
            do {
                UserModel tmpUser = new UserModel(
                    resultSet.getString("user_id"),
                    resultSet.getString("name"),
                    resultSet.getDate("yelping_since"),
                    resultSet.getInt("useful"),
                    resultSet.getInt("funny"),
                    resultSet.getInt("cool")
                );
                users.add(tmpUser);
                System.out.println(resultSet.getString("name"));
			} while(resultSet.next());

            resultSet.close();
            return "Users found!";
        }
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public boolean isLoggedIn() { return (loginId != "" && loginId != null); }
}
