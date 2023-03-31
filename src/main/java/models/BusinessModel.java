/*  
 *  BusinessModel.java
 *  
 *  Description: A class that models after the business schema in the database.
 *
*/

package models;

public class BusinessModel 
{
    String business_id;
    String name;
    String address;
    String city;
    double stars;

    public BusinessModel(String business_id, String name, String address, String city, double stars)
    {
        this.business_id = business_id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.stars = stars;
    }
}
