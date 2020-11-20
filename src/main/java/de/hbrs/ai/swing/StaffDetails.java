package de.hbrs.ai.swing;

import de.hbrs.ai.SwingUI;
import de.hbrs.ai.model.EvaluationRecord;
import de.hbrs.ai.model.SalesMan;

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
    SalesMan salesman;

    /**
     * window for displaying details about a salesman
     * @param sm specifies the salesman, whose details are shown
     */
    public StaffDetails(SalesMan sm, Overview parent){
        super();
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

        update(sm);

        StaffDetails current = this;

        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Add Record" button pressed
                JFrame addEvalFrame = new AddEvalRecord(salesman, current);//creating window
                addEvalFrame.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Save" button pressed
                SwingUI.mP.updateSalesMen(salesman.getId(), firstname.getText(), lastname.getText());
                parent.loadStaff();
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recordsTable.getSelectedRow() >= 0) { //only execute if a salesman has been selected
                    SwingUI.mP.deleteEvaluationRecord(salesman.getId(), Integer.parseInt(recordsTable.getValueAt(recordsTable.getSelectedRow(), 0).toString()));
                    update();
                }
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

        for(EvaluationRecord record : SwingUI.mP.readEvaluationRecords(sm.getId())){ //iterating through the records to add them all
            int[] performance = record.getPerformance();
            //System.out.println(performance[0]);
            tm.addRow(new Object[]{record.getYear(), performance[0], performance[1], performance[2], performance[3], performance[4], performance[5]});
        }
    }

    /**
     * refreshes the information in the window
     */
    public void update(){
        update(SwingUI.mP.readSalesMan(this.salesman.getId()));
    }

    /**
     * refreshes the information in the window with the given salesman
     * @param sm salesman
     */
    public void update(SalesMan sm){
        this.salesman = sm;
        this.setTitle("Salesman - " + this.salesman.getFirstname() + " " + this.salesman.getLastname());
        //filling in the information about the salesman:
        id.setText(Integer.toString(this.salesman.getId()));
        firstname.setText(this.salesman.getFirstname());
        lastname.setText(this.salesman.getLastname());
        loadRecords(this.salesman); //loading the evaluation records
    }
}
