package de.hbrs.ai.swing;

import de.hbrs.ai.SwingUI;
import de.hbrs.ai.model.SalesMan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSalesMan extends JFrame{
    private JTextField id;
    private JTextField firstname;
    private JTextField lastname;
    private JButton addButton;
    private JPanel addPanel;

    /**
     * Form for adding a new salesman
     */
    public AddSalesMan(Overview parent){
        super("Add Salesman"); //setting title
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //only close this window
        this.setContentPane(addPanel);
        this.pack();



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//"Add" button pressed
                SalesMan newSalesMan = new SalesMan(firstname.getText(), lastname.getText(), Integer.parseInt(id.getText())); //create new salesman
                try {
                    SwingUI.mP.createSalesMan(newSalesMan); //store salesman in DB
                    parent.loadStaff();
                    kill(); //close window
                }catch (IllegalArgumentException ex){
                    JOptionPane.showMessageDialog(new JFrame(), "A salesman with that ID already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    /**
     * function for closing the current window
     */
    private void kill(){
        this.dispose();
    }
}
