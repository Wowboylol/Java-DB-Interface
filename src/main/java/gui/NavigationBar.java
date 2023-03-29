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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.GuiHandler;

public class NavigationBar extends JMenuBar implements ActionListener
{
    private JMenu menuSearch, menuFriend, menuReview;
    private JMenuItem menuItemSearchBusiness, menuItemSearchUser;
    private SearchUser searchUser;

    public NavigationBar(GuiHandler guiHandler)
    {
        super();
        createMenu();
        createMenuItem();
        searchUser = new SearchUser(guiHandler);
    }

    private void createMenu()
    {
        menuSearch = new JMenu("Search");
        menuFriend = new JMenu("Friend");
        menuReview = new JMenu("Review");
        this.add(menuSearch);
        this.add(menuFriend);
        this.add(menuReview);
    }

    private void createMenuItem()
    {
        menuItemSearchUser = new JMenuItem("Users");
        menuItemSearchUser.addActionListener(this);
        menuItemSearchUser.setActionCommand("searchUser");
        menuSearch.add(menuItemSearchUser);

        menuItemSearchBusiness = new JMenuItem("Businesses");
        menuSearch.add(menuItemSearchBusiness);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        switch(command)
        {
            case "searchUser":
                System.out.println("Search User");
                searchUser.display();
                break;
            default:
                System.out.println("Error: Invalid command");
                break;
        }
    }
}
