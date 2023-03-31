/*  
 *  SearchUser.java
 *  
 *  Description: Displays GUI for searching users.
 *
*/

package gui;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.DatabaseHandler;
import main.GuiHandler;

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
