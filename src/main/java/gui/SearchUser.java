/*  
 *  SearchUser.java
 *  
 *  Description: Displays GUI for searching users.
 *
*/

package gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;  

import main.DatabaseHandler;
import main.GuiHandler;
import models.UserModel;

public class SearchUser extends JPanel implements ActionListener
{
    private GuiHandler guiHandler;
    private JLabel title;
    private JLabel name;
    private JTextField nameField;
    private JLabel useful;
    private JRadioButton usefulNo;
    private JRadioButton usefulYes;
    private ButtonGroup usefulGroup;
    private JLabel funny;
    private JRadioButton funnyNo;
    private JRadioButton funnyYes;
    private ButtonGroup funnyGroup;
    private JLabel cool;
    private JRadioButton coolNo;
    private JRadioButton coolYes;
    private ButtonGroup coolGroup;
    private JButton searchButton;
    private JButton clearButton;
    private JLabel result;
    private JPanel searchResultList;

    public SearchUser(GuiHandler guiHandler)
    {
        this.guiHandler = guiHandler;
    }

    private void setupPanel()
    {
        this.setLayout(null);

        title = new JLabel("Search User");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(375, 20);
        this.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        this.add(name);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 15));
        nameField.setSize(190, 20);
        nameField.setLocation(200, 100);
        this.add(nameField);

        useful = new JLabel("Useful");
        useful.setFont(new Font("Arial", Font.PLAIN, 20));
        useful.setSize(100, 20);
        useful.setLocation(100, 150);
        this.add(useful);

        usefulNo = new JRadioButton("No");
        usefulNo.setFont(new Font("Arial", Font.PLAIN, 15));
        usefulNo.setSelected(true);
        usefulNo.setSize(75, 20);
        usefulNo.setLocation(200, 150);
        this.add(usefulNo);

        usefulYes = new JRadioButton("Yes");
        usefulYes.setFont(new Font("Arial", Font.PLAIN, 15));
        usefulYes.setSelected(false);
        usefulYes.setSize(75, 20);
        usefulYes.setLocation(275, 150);
        this.add(usefulYes);

        usefulGroup = new ButtonGroup();
        usefulGroup.add(usefulNo);
        usefulGroup.add(usefulYes);

        funny = new JLabel("Funny");
        funny.setFont(new Font("Arial", Font.PLAIN, 20));
        funny.setSize(100, 20);
        funny.setLocation(100, 200);
        this.add(funny);

        funnyNo = new JRadioButton("No");
        funnyNo.setFont(new Font("Arial", Font.PLAIN, 15));
        funnyNo.setSelected(true);
        funnyNo.setSize(75, 20);
        funnyNo.setLocation(200, 200);
        this.add(funnyNo);

        funnyYes = new JRadioButton("Yes");
        funnyYes.setFont(new Font("Arial", Font.PLAIN, 15));
        funnyYes.setSelected(false);
        funnyYes.setSize(75, 20);
        funnyYes.setLocation(275, 200);
        this.add(funnyYes);

        funnyGroup = new ButtonGroup();
        funnyGroup.add(funnyNo);
        funnyGroup.add(funnyYes);

        cool = new JLabel("Cool");
        cool.setFont(new Font("Arial", Font.PLAIN, 20));
        cool.setSize(100, 20);
        cool.setLocation(100, 250);
        this.add(cool);

        coolNo = new JRadioButton("No");
        coolNo.setFont(new Font("Arial", Font.PLAIN, 15));
        coolNo.setSelected(true);
        coolNo.setSize(75, 20);
        coolNo.setLocation(200, 250);
        this.add(coolNo);

        coolYes = new JRadioButton("Yes");
        coolYes.setFont(new Font("Arial", Font.PLAIN, 15));
        coolYes.setSelected(false);
        coolYes.setSize(75, 20);
        coolYes.setLocation(275, 250);
        this.add(coolYes);

        coolGroup = new ButtonGroup();
        coolGroup.add(coolNo);
        coolGroup.add(coolYes);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 15));
        searchButton.setSize(100, 20);
        searchButton.setLocation(150, 300);
        searchButton.addActionListener(this);
        this.add(searchButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 15));
        clearButton.setSize(100, 20);
        clearButton.setLocation(275, 300);
        clearButton.addActionListener(this);
        this.add(clearButton);

        result = new JLabel("");
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        result.setSize(500, 25);
        result.setLocation(100, 350);
        this.add(result);

        JScrollPane searchResults = new JScrollPane();
        searchResults.setLayout(new ScrollPaneLayout());
        searchResults.setSize(370, 500);
        searchResults.setLocation(480, 100);
        searchResults.setAlignmentX(CENTER_ALIGNMENT);
        this.add(searchResults);

        searchResultList = new JPanel();
        searchResultList.setLayout(new BoxLayout(searchResultList, BoxLayout.Y_AXIS));
        searchResultList.setBackground(java.awt.Color.WHITE);
        searchResults.setViewportView(searchResultList);
    }

    private void updateSearchResultList()
    {
        ArrayList<UserModel> searchedUsers = DatabaseHandler.getInstance().getSearchedUsers();
        searchResultList.removeAll();
        searchResultList.revalidate();

        for(UserModel user : searchedUsers)
        {
            JButton userPanelButton = new JButton();
            userPanelButton.setLayout(new BoxLayout(userPanelButton, BoxLayout.X_AXIS));
            userPanelButton.setBackground(java.awt.Color.WHITE);
            userPanelButton.setMaximumSize(new Dimension(800, 40));
            userPanelButton.setAlignmentX(CENTER_ALIGNMENT);

            JLabel name = new JLabel(user.name);
            name.setFont(new Font("Arial", Font.PLAIN, 15));
            name.setSize(200, 20);
            name.setLocation(0, 0);
            userPanelButton.add(name);

            JLabel id = new JLabel(" | ID: " + user.userId);
            id.setFont(new Font("Arial", Font.PLAIN, 15));
            id.setSize(200, 20);
            id.setLocation(200, 0);
            userPanelButton.add(id);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            JLabel date = new JLabel(" | Date: " + dateFormat.format(user.yelping_since));
            date.setFont(new Font("Arial", Font.PLAIN, 15));
            date.setSize(100, 20);
            date.setLocation(400, 0);
            userPanelButton.add(date);

            JLabel useful = new JLabel(" | Useful: " + (user.useful > 0 ? "yes" : "no"));
            useful.setFont(new Font("Arial", Font.PLAIN, 15));
            useful.setSize(100, 20);
            useful.setLocation(500, 0);
            userPanelButton.add(useful);

            JLabel funny = new JLabel(" | Funny: " + (user.funny > 0 ? "yes" : "no"));
            funny.setFont(new Font("Arial", Font.PLAIN, 15));
            funny.setSize(100, 20);
            funny.setLocation(600, 0);
            userPanelButton.add(funny);

            JLabel cool = new JLabel(" | Cool: " + (user.cool > 0 ? "yes" : "no"));
            cool.setFont(new Font("Arial", Font.PLAIN, 15));
            cool.setSize(100, 20);
            cool.setLocation(700, 0);
            userPanelButton.add(cool);

            searchResultList.add(userPanelButton);
        }

        searchResultList.repaint();
    }

    public void display()
    {
        if(title == null) { setupPanel(); }
        guiHandler.panelSwap(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == searchButton)
        {
            String name = nameField.getText();
            boolean useful = usefulYes.isSelected();
            boolean funny = funnyYes.isSelected();
            boolean cool = coolYes.isSelected();
            this.result.setText("Searching...");
            String result = DatabaseHandler.getInstance().searchUser(name, useful, funny, cool);
            this.result.setText(result);
            updateSearchResultList();
        }
        else if(e.getSource() == clearButton)
        {
            nameField.setText("");
            usefulNo.setSelected(true);
            funnyNo.setSelected(true);
            coolNo.setSelected(true);
            result.setText("");
        }
    }
}
