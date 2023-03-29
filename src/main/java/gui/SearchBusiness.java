/*  
 *  SearchBusiness.java
 *  
 *  Description: Displays GUI for searching businesses.
 *
*/

package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

import main.GuiHandler;

public class SearchBusiness extends JPanel
{
    private GuiHandler guiHandler;
    private JLabel title;
    private JLabel name;
    private JTextField nameField;
    private JLabel city;
    private JTextField cityField;
    private String stars[] = {"0", "1", "2", "3", "4", "5"};
    private JLabel minStars;
    private JComboBox<String> minStarsSelect;
    private JLabel maxStars;
    private JComboBox<String> maxStarsSelect;
    private JButton searchButton;
    private JButton clearButton;
    private JLabel result;

    public SearchBusiness(GuiHandler guiHandler)
    {
        this.guiHandler = guiHandler;
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
        // searchButton.addActionListener(this);
        this.add(searchButton);

        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.PLAIN, 15));
        clearButton.setSize(100, 20);
        clearButton.setLocation(275, 300);
        // clearButton.addActionListener(this);
        this.add(clearButton);

        result = new JLabel("");
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        result.setSize(500, 25);
        result.setLocation(100, 350);
        this.add(result);
    }

    public void display()
    {
        if(title == null) { setupPanel(); }
        guiHandler.panelSwap(this);
    }
}
