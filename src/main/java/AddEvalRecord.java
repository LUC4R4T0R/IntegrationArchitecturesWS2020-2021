import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEvalRecord extends JFrame{
    private JTextField value;
    private JButton addButton;
    private JPanel addEvalPanel;

    public AddEvalRecord(SalesMan sm){
        super("Add Record - " + sm.getFirstname() + " " + sm.getLastname());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(addEvalPanel);

        this.pack();


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!value.getText().isBlank()){
                    Main.mP.addPerformanceRecord(new EvaluationRecord(value.getText()), sm.getId());
                    kill();
                }
            }
        });
    }

    private void kill(){
        this.dispose();
    }
}
