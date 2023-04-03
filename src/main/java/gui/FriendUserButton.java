/*  
 *  FriendUserButton.java
 *  
 *  Description: Extends JButton and can send a friend request to the stored user.
 *
*/

package gui;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.DatabaseHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.UserModel;

public class FriendUserButton extends JButton implements ActionListener
{
    private JLabel parentResult;
    private UserModel user;

    public FriendUserButton(JLabel parentResult, UserModel user)
    {
        super();
        this.parentResult = parentResult;
        this.user = user;
        this.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this)
        {
            System.out.println("Friend request sent to " + user.name);
            String res = DatabaseHandler.getInstance().sendFriendRequest(user.userId);
            parentResult.setText("<html>" + res + "</html>");
        }
    }
}
