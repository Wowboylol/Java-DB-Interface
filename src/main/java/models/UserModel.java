/*  
 *  UserModel.java
 *  
 *  Description: A class that models after the user_yelp schema in the database.
 *
*/

package models;

import java.util.Date;

public class UserModel 
{
    public String userId;
    public String name;
    public Date yelping_since;
    public int useful;
    public int funny;
    public int cool;

    public UserModel(String userId, String name, Date yelping_since, int useful, int funny, int cool)
    {
        this.userId = userId;
        this.name = name;
        this.yelping_since = yelping_since;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
    }
}
