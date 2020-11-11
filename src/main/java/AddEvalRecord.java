import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEvalRecord extends JFrame{
    private JTextField value;
    private JButton addButton;
    private JPanel addEvalPanel;

    /**
     * Form for adding a new evaluation record
     * @param sm specifies the salesman, who is assessed
     */
    public AddEvalRecord(SalesMan sm){
        super("Add Record - " + sm.getFirstname() + " " + sm.getLastname()); //specify window title
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //only close this window
        this.setContentPane(addEvalPanel);

        this.pack();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//"Add" button pressed
                if(!value.getText().isBlank()){
                    Main.mP.addPerformanceRecord(new EvaluationRecord(value.getText()), sm.getId()); //save record
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
