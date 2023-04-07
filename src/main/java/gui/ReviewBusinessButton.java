/*  
 *  ReviewBusinessButton.java
 *  
 *  Description: Extends JButton and redirects to the ReviewBusiness GUI with the stored business ID.
 *
*/

package gui;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.BusinessModel;

public class ReviewBusinessButton extends JButton implements ActionListener
{
    private NavigationBar navBar;
    private BusinessModel business;

    public ReviewBusinessButton(BusinessModel business, NavigationBar navBar)
    {
        super();
        this.business = business;
        this.navBar = navBar;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this)
        {
            navBar.redirectToReviewBusiness(business.business_id);
        }
    }
}
