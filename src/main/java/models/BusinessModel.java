/*  
 *  BusinessModel.java
 *  
 *  Description: A class that models after the business schema in the database.
 *
*/

package models;

public class BusinessModel 
{
    public String business_id;
    public String name;
    public String address;
    public String city;
    public double stars;

    public BusinessModel(String business_id, String name, String address, String city, double stars)
    {
        this.business_id = business_id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.stars = stars;
    }
}
