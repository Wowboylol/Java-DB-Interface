/*  
 *  SearchBusiness.java
 *  
 *  Description: Displays GUI for searching businesses.
 *
*/

package gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.GuiHandler;
import main.DatabaseHandler;
import models.BusinessModel;

public class SearchBusiness extends JPanel implements ActionListener
{
    private GuiHandler guiHandler;
    private NavigationBar navBar;
    private JLabel title;
    private JLabel name;
    private JTextField nameField;
    private JLabel city;
    private JTextField cityField;
    private String stars[] = {"1", "2", "3", "4", "5"};
    private JLabel minStars;
    private JComboBox<String> minStarsSelect;
    private JLabel maxStars;
    private JComboBox<String> maxStarsSelect;
    private JButton searchButton;
    private JButton clearButton;
    private JLabel result;
    private JPanel searchResultList;

    public SearchBusiness(GuiHandler guiHandler, NavigationBar navBar)
    {
        this.guiHandler = guiHandler;
        this.navBar = navBar;
    }

    private void setupPanel()
    {
        this.setLayout(null);

        title = new JLabel("Search Business");
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

        city = new JLabel("City");
        city.setFont(new Font("Arial", Font.PLAIN, 20));
        city.setSize(100, 20);
        city.setLocation(100, 150);
        this.add(city);

        cityField = new JTextField();
        cityField.setFont(new Font("Arial", Font.PLAIN, 15));
        cityField.setSize(190, 20);
        cityField.setLocation(200, 150);
        this.add(cityField);

        minStars = new JLabel("Min Stars");
        minStars.setFont(new Font("Arial", Font.PLAIN, 20));
        minStars.setSize(100, 20);
        minStars.setLocation(100, 200);
        this.add(minStars);

        minStarsSelect = new JComboBox<String>(stars);
        minStarsSelect.setFont(new Font("Arial", Font.PLAIN, 15));
        minStarsSelect.setSize(100, 20);
        minStarsSelect.setLocation(200, 200);
        this.add(minStarsSelect);

        maxStars = new JLabel("Max Stars");
        maxStars.setFont(new Font("Arial", Font.PLAIN, 20));
        maxStars.setSize(100, 20);
        maxStars.setLocation(100, 250);
        this.add(maxStars);

        maxStarsSelect = new JComboBox<String>(stars);
        maxStarsSelect.setFont(new Font("Arial", Font.PLAIN, 15));
        maxStarsSelect.setSize(100, 20);
        maxStarsSelect.setLocation(200, 250);
        this.add(maxStarsSelect);

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
        searchResults.setAlignmentX(LEFT_ALIGNMENT);
        searchResults.getVerticalScrollBar().setUnitIncrement(25);
        this.add(searchResults);

        searchResultList = new JPanel();
        searchResultList.setLayout(new BoxLayout(searchResultList, BoxLayout.Y_AXIS));
        searchResultList.setBackground(java.awt.Color.WHITE);
        searchResults.setViewportView(searchResultList);
    }

    private void updateSearchResultList()
    {
        ArrayList<BusinessModel> businesses = DatabaseHandler.getInstance().getSearchedBusinesses();
        searchResultList.removeAll();
        searchResultList.revalidate();

        for(BusinessModel business : businesses)
        {
            JButton businessPanelButton = new ReviewBusinessButton(business, navBar);
            businessPanelButton.setLayout(new BoxLayout(businessPanelButton, BoxLayout.X_AXIS));
            businessPanelButton.setBackground(java.awt.Color.WHITE);
            businessPanelButton.setMaximumSize(new Dimension(1050, 40));
            businessPanelButton.setAlignmentX(LEFT_ALIGNMENT);

            JLabel name = new JLabel(business.name);
            name.setFont(new Font("Arial", Font.PLAIN, 15));
            name.setSize(200, 20);
            name.setLocation(0, 0);
            businessPanelButton.add(name);

            JLabel stars = new JLabel(" | Stars: " + business.stars);
            stars.setFont(new Font("Arial", Font.PLAIN, 15));
            stars.setSize(50, 20);
            stars.setLocation(200, 0);
            businessPanelButton.add(stars);

            JLabel id = new JLabel(" | ID: " + business.business_id);
            id.setFont(new Font("Arial", Font.PLAIN, 15));
            id.setSize(200, 20);
            id.setLocation(250, 0);
            businessPanelButton.add(id);

            JLabel address = new JLabel(" | Address: " + business.address);
            address.setFont(new Font("Arial", Font.PLAIN, 15));
            address.setSize(400, 20);
            address.setLocation(450, 0);
            businessPanelButton.add(address);

            JLabel city = new JLabel(" | City: " + business.city);
            city.setFont(new Font("Arial", Font.PLAIN, 15));
            city.setSize(200, 20);
            city.setLocation(850, 0);
            businessPanelButton.add(city);

            searchResultList.add(businessPanelButton);
        }
        searchResultList.repaint();
    }

    public void display()
    {
        if(title == null) { setupPanel(); }
        guiHandler.panelSwap(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == searchButton)
        {
            String name = nameField.getText();
            String city = cityField.getText();
            int minStars = Integer.parseInt((String)minStarsSelect.getSelectedItem());
            int maxStars = Integer.parseInt((String)maxStarsSelect.getSelectedItem());

            if(minStars > maxStars)
            {
                this.result.setText("Min stars must be less than or equal to max stars.");
                return;
            }

            this.result.setText("Searching...");
            String result = DatabaseHandler.getInstance().searchBusiness(name, city, minStars, maxStars);
            this.result.setText(result);
            updateSearchResultList();
        }
        else if(e.getSource() == clearButton)
        {
            nameField.setText("");
            cityField.setText("");
            minStarsSelect.setSelectedIndex(0);
            maxStarsSelect.setSelectedIndex(0);
            result.setText("");
            searchResultList.removeAll();
            searchResultList.revalidate();
            searchResultList.repaint();
        }
    }
}
