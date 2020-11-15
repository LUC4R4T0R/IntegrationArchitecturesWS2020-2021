import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffDetails extends JFrame{
    private JTable recordsTable;
    private JPanel detailsPanel;
    private JLabel id;
    private JTextField firstname;
    private JTextField lastname;
    private JButton addRecord;
    private JButton saveButton;
    private JButton deleteRecordButton;
    private DefaultTableModel tm;

    /**
     * window for displaying details about a salesman
     * @param sm specifies the salesman, whose details are shown
     */
    public StaffDetails(SalesMan sm){
        super("Salesman - " + sm.getFirstname() + " " + sm.getLastname());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //close only this window
        this.setContentPane(detailsPanel);

        this.pack();

        tm = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        }; //make table read-only
        recordsTable.setModel(tm);
        tm.addColumn("Year");
        tm.addColumn("Leadership Competence");
        tm.addColumn("Openness to Employee");
        tm.addColumn("Social Behaviour to Employee");
        tm.addColumn("Attitude towards Client");
        tm.addColumn("Communication Skills");
        tm.addColumn("Integrity to Company");
        recordsTable.getTableHeader().resizeAndRepaint();

        //filling in the information about the salesman:
        id.setText(Integer.toString(sm.getId()));
        firstname.setText(sm.getFirstname());
        lastname.setText(sm.getLastname());

        loadRecords(sm); //loading the evaluation records

        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Add Record" button pressed
                JFrame addEvalFrame = new AddEvalRecord(sm);//creating window
                addEvalFrame.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Save" button pressed
                System.out.println("updating");
                Main.mP.updateSalesMen(sm.getId(), firstname.getText(), lastname.getText());
            }
        });
    }

    /**
     * function for loading the evaluation records of a salesman
     * @param sm specifies the salesman, whose records are loaded
     */
    private void loadRecords(SalesMan sm){
        for (int i = tm.getRowCount() - 1; i >= 0; i--) { //first emptying the table
            tm.removeRow(i);
        }

        for(EvaluationRecord record : Main.mP.readEvaluationRecords(sm.getId())){ //iterating through the records to add them all
            int[] performance = record.getPerformance();
            //System.out.println(performance[0]);
            tm.addRow(new Object[]{record.getYear(), performance[0], performance[1], performance[2], performance[3], performance[4], performance[5]});
        }
    }
}
