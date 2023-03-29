/*  
 *  NavigationBar.java
 *  
 *  Description: Handles all navigation to different templates.
 *
*/

package gui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class NavigationBar extends JMenuBar
{
    private JMenu menuSearch, menuFriend, menuReview;
    private JMenuItem menuItemSearchBusiness, menuItemSearchUser;

    public NavigationBar()
    {
        super();
        createMenu();
        createMenuItem();
    }

    public void createMenu()
    {
        menuSearch = new JMenu("Search");
        menuFriend = new JMenu("Friend");
        menuReview = new JMenu("Review");
        this.add(menuSearch);
        this.add(menuFriend);
        this.add(menuReview);
    }

    public void createMenuItem()
    {
        menuItemSearchUser = new JMenuItem("Users");
        menuItemSearchBusiness = new JMenuItem("Businesses");
        menuSearch.add(menuItemSearchUser);
        menuSearch.add(menuItemSearchBusiness);
    }
}
