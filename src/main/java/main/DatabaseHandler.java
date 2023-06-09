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
import java.util.Random;

import models.BusinessModel;
import models.UserModel;

public class DatabaseHandler 
{
    private static DatabaseHandler instance = null;
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String username = "---";
    private String password = "---";
    private String loginId = "";
    private String loginName = "";
    private ArrayList<UserModel> users = new ArrayList<UserModel>();
    private ArrayList<BusinessModel> businesses = new ArrayList<BusinessModel>();

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

    private String generateRandomId()
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 22;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String reviewId = buffer.toString();
        return reviewId;
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

            users.clear();
            if(resultSet.next() == false) { return "No users matching criteria found!"; }
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
                // System.out.println(resultSet.getString("name"));
			} while(resultSet.next());

            resultSet.close();
            return users.size() + " users found!";
        }
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public String searchBusiness(String name, String city, int minStars, int maxStars)
    {
        try {
            preparedStatement = connect.prepareStatement(
                "SELECT * FROM business B WHERE B.name LIKE '%" + name + "%'" +
                "AND B.city = '" + city + "'" +
                "AND B.stars >= " + minStars +
                "AND B.stars <= " + maxStars +
                "ORDER BY B.name"
            );
            resultSet = preparedStatement.executeQuery();

            businesses.clear();
            if(resultSet.next() == false) { return "No businesses matching criteria found!"; }
            do {
                BusinessModel tmpBusiness = new BusinessModel(
                    resultSet.getString("business_id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getString("city"),
                    resultSet.getDouble("stars")
                );
                businesses.add(tmpBusiness);
                System.out.println(resultSet.getString("name"));
            } while(resultSet.next());

            resultSet.close();
            return businesses.size() + " businesses found!";
        }
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public String sendFriendRequest(String userId)
    {
        try {
            preparedStatement = connect.prepareStatement(
                "INSERT INTO friendship VALUES ('" + loginId + "', '" + userId + "')"
            );
            preparedStatement.executeUpdate();

            return "Friend with id: " + userId + " added!";
        }
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public String reviewBusiness(String businessId, int stars)
    {
        try {
            String reviewId = generateRandomId();
            preparedStatement = connect.prepareStatement(
                "INSERT INTO review VALUES ('" + reviewId + "', '" 
                + loginId + "', '" 
                + businessId + "', '" 
                + stars + "', " 
                + "DEFAULT, " + "DEFAULT, "
                + "DEFAULT, " + "DEFAULT)"
            );
            preparedStatement.executeUpdate();

            System.out.println("Review added with id: " + reviewId + "!");
            return "Review added with id: " + reviewId + "!";
        }
        catch(SQLException sqlError) {
            System.out.println("\nSQL Exception occurred, the state: " + sqlError.getSQLState()+"\nMessage: "+ sqlError.getMessage());
            return sqlError.getMessage();
        }
    }

    public boolean isLoggedIn() { return (loginId != "" && loginId != null); }
    public ArrayList<UserModel> getSearchedUsers() { return users; }
    public ArrayList<BusinessModel> getSearchedBusinesses() { return businesses; }
}
