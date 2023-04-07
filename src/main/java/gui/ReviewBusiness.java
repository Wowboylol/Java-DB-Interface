/*  
 *  ReviewBusiness.java
 *  
 *  Description: Displays GUI for submitting a review for a business.
 *
*/

package gui;

import javax.swing.*;

import main.DatabaseHandler;
import main.GuiHandler;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewBusiness extends JPanel implements ActionListener
{
    private GuiHandler guiHandler;
    private JLabel title;
    private JLabel businessId;
    private JTextField businessIdField;
    private String stars[] = {"1", "2", "3", "4", "5"};
    private JLabel reviewScore;
    private JComboBox<String> reviewScoreSelect;
    private JButton submitButton;
    private JLabel result;

    public ReviewBusiness(GuiHandler guiHandler)
    {
        this.guiHandler = guiHandler;
    }

    private void setupPanel()
    {
        this.setLayout(null);

        title = new JLabel("Review Business");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(375, 20);
        this.add(title);

        businessId = new JLabel("Business ID");
        businessId.setFont(new Font("Arial", Font.PLAIN, 20));
        businessId.setSize(150, 20);
        businessId.setLocation(100, 100);
        this.add(businessId);
        
        businessIdField = new JTextField();
        businessIdField.setFont(new Font("Arial", Font.PLAIN, 15));
        businessIdField.setSize(190, 20);
        businessIdField.setLocation(250, 100);
        this.add(businessIdField);

        reviewScore = new JLabel("Review Score");
        reviewScore.setFont(new Font("Arial", Font.PLAIN, 20));
        reviewScore.setSize(150, 20);
        reviewScore.setLocation(100, 150);
        this.add(reviewScore);

        reviewScoreSelect = new JComboBox<String>(stars);
        reviewScoreSelect.setFont(new Font("Arial", Font.PLAIN, 15));
        reviewScoreSelect.setSize(50, 20);
        reviewScoreSelect.setLocation(250, 150);
        this.add(reviewScoreSelect);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
        submitButton.setSize(100, 20);
        submitButton.setLocation(220, 200);
        submitButton.addActionListener(this);
        this.add(submitButton);

        result = new JLabel("");
        result.setFont(new Font("Arial", Font.PLAIN, 20));
        result.setSize(500, 100);
        result.setLocation(100, 250);
        this.add(result);
    }

    public void display()
    {
        if(title == null) { setupPanel(); }
        guiHandler.panelSwap(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == submitButton)
        {
            String businessId = businessIdField.getText();
            int reviewScore = Integer.parseInt((String)reviewScoreSelect.getSelectedItem());

            this.result.setText("Searching...");
            String res = DatabaseHandler.getInstance().reviewBusiness(businessId, reviewScore);
            this.result.setText("<html>" + res + "</html>");
        }
    }

    public void changeBusinessIdField(String id) { businessIdField.setText(id); }
}
