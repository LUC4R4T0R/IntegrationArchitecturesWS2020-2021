package de.hbrs.ai.swing;

import de.hbrs.ai.SwingUI;
import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.SalesMan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AddEvalRecord extends JFrame{
    private JButton addButton;
    private JPanel addEvalPanel;
    private JTextField communicationInput;
    private JTextField attitudeInput;
    private JTextField socialInput;
    private JTextField opennessInput;
    private JTextField leadershipInput;
    private JTextField yearInput;
    private JTextField integrityInput;

    /**
     * Form for adding a new evaluation record
     * @param sm specifies the salesman, who is assessed
     */
    public AddEvalRecord(SalesMan sm, StaffDetails parent){
        super("Add Record - " + sm.getFirstname() + " " + sm.getLastname()); //specify window title
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //only close this window
        this.setContentPane(addEvalPanel);

        yearInput.setText(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        this.pack();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//"Add" button pressed
                if(
                        !(
                            communicationInput.getText().isBlank()
                            || attitudeInput.getText().isBlank()
                            || socialInput.getText().isBlank()
                            || opennessInput.getText().isBlank()
                            || leadershipInput.getText().isBlank()
                            || integrityInput.getText().isBlank()
                            || yearInput.getText().isBlank()
                        )
                ){
                    SwingUI.mP.addPerformanceRecord(
                            new EvaluationRecord(
                                    new int[]{
                                        Integer.parseInt(leadershipInput.getText()),
                                        Integer.parseInt(opennessInput.getText()),
                                        Integer.parseInt(socialInput.getText()),
                                        Integer.parseInt(attitudeInput.getText()),
                                        Integer.parseInt(communicationInput.getText()),
                                        Integer.parseInt(integrityInput.getText())
                                    },
                                    Integer.parseInt(yearInput.getText())
                            ),
                            sm.getId()
                    ); //save record
                    parent.update();
                    kill(); //close form
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
