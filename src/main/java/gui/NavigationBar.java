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
    private JMenuItem menuItemSearchBusiness, menuItemSearchUser, menuItemReviewBusiness;
    private SearchUser searchUser;
    private SearchBusiness searchBusiness;
    private ReviewBusiness reviewBusiness;

    public NavigationBar(GuiHandler guiHandler)
    {
        super();
        searchUser = new SearchUser(guiHandler);
        searchBusiness = new SearchBusiness(guiHandler, this);
        reviewBusiness = new ReviewBusiness(guiHandler);
    }

    public void enableMenu()
    {
        createMenu();
        createMenuItem();
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
        menuItemSearchBusiness.addActionListener(this);
        menuItemSearchBusiness.setActionCommand("searchBusiness");
        menuSearch.add(menuItemSearchBusiness);

        menuItemReviewBusiness = new JMenuItem("Business");
        menuItemReviewBusiness.addActionListener(this);
        menuItemReviewBusiness.setActionCommand("review");
        menuReview.add(menuItemReviewBusiness);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        switch(command)
        {
            case "searchUser":
                searchUser.display();
                break;
            case "searchBusiness":
                searchBusiness.display();
                break;
            case "review":
                reviewBusiness.display();
                break;
            default:
                System.out.println("Error: Invalid command");
                break;
        }
    }

    public void redirectToReviewBusiness(String businessId)
    {
        reviewBusiness.display();
        reviewBusiness.changeBusinessIdField(businessId);
    }
}
