/*  
 *  userLogin.java
 *  
 *  Description: Displays GUI for login, which must be performed before accessing
 *               any other GUI.
 *
*/

package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.DatabaseHandler;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserLogin extends JPanel implements ActionListener
{
    private JLabel title;
    private JLabel userId;
    private JTextField userIdField;
    private JButton loginButton;

    public UserLogin()
    {
        setupPanel();
    }

    private void setupPanel()
    {
        this.setLayout(null);

        title = new JLabel("User Login");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(375, 20);
        this.add(title);

        userId = new JLabel("User ID");
        userId.setFont(new Font("Arial", Font.PLAIN, 20));
        userId.setSize(100, 20);
        userId.setLocation(255, 270);
        this.add(userId);
        
        userIdField = new JTextField();
        userIdField.setFont(new Font("Arial", Font.PLAIN, 15));
        userIdField.setSize(190, 20);
        userIdField.setLocation(355, 270);
        this.add(userIdField);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setSize(100, 20);
        loginButton.setLocation(400, 320);
        loginButton.addActionListener(this);
        this.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == loginButton)
        {
            DatabaseHandler.getInstance().login(userIdField.getText());
        }
    }
}
