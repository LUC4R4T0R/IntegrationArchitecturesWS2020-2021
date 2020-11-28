package de.hbrs.ia.swing;

import de.hbrs.ia.SwingUI;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.SalesMan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * This class reacts to the actions on the StaffDetails-UI.
 */
public class StaffDetails extends JFrame {
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
     *
     * @param sm specifies the salesman, whose details are shown
     */
    public StaffDetails(SalesMan sm, Overview parent) {
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //close only this window
        this.setContentPane(detailsPanel);
        this.pack();


        tm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; //make table read-only
        recordsTable.setModel(tm);
        tm.addColumn("Year");
        tm.addColumn("hidden"); //adding column to be hidden
        recordsTable.getTableHeader().resizeAndRepaint();
        recordsTable.removeColumn(recordsTable.getColumnModel().getColumn(1)); //remove column from table to hide it

        update(sm);

        StaffDetails current = this;

        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Add Record" button pressed
                JFrame evalFrame = new EvalRecord(salesman, current);//creating window
                evalFrame.setVisible(true);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Save" button pressed
                salesman.setFirstname(firstname.getText());
                salesman.setLastname(lastname.getText());
                SwingUI.mP.updateSalesMen(salesman);
                parent.loadStaff();
            }
        });
        deleteRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recordsTable.getSelectedRow() >= 0) { //only execute if a salesman has been selected
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Do you really want wo delete the record of " + recordsTable.getValueAt(recordsTable.getSelectedRow(), 0) + "\"?", "Evaluation Record Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                    if (n == 0) {
                        SwingUI.mP.deleteEvaluationRecord(salesman.getId(), Integer.parseInt(recordsTable.getValueAt(recordsTable.getSelectedRow(), 0).toString()));
                        update();
                    }
                }
            }
        });
        recordsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table = (JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && row > -1) { //executed if salesman is double-clicked
                    showRecord((EvaluationRecord) table.getModel().getValueAt(row, 1)); //opening details-window
                }
            }
        });
    }

    /**
     * function for loading the evaluation records of a salesman
     *
     * @param sm specifies the salesman, whose records are loaded
     */
    private void loadRecords(SalesMan sm) {
        for (int i = tm.getRowCount() - 1; i >= 0; i--) { //first emptying the table
            tm.removeRow(i);
        }

        for (EvaluationRecord record : SwingUI.mP.readEvaluationRecords(sm.getId())) { //iterating through the records to add them all
            tm.addRow(new Object[]{record.getYear(), record});
        }
    }

    /**
     * refreshes the information in the window
     */
    public void update() {
        update(SwingUI.mP.readSalesMan(this.salesman.getId()));
    }

    /**
     * refreshes the information in the window with the given salesman
     *
     * @param sm salesman
     */
    public void update(SalesMan sm) {
        this.salesman = sm;
        this.setTitle("Salesman - " + this.salesman.getFirstname() + " " + this.salesman.getLastname());
        //filling in the information about the salesman:
        id.setText(Integer.toString(this.salesman.getId()));
        firstname.setText(this.salesman.getFirstname());
        lastname.setText(this.salesman.getLastname());
        loadRecords(this.salesman); //loading the evaluation records
    }


    private void showRecord(EvaluationRecord record) {
        JFrame recordFrame = new EvalRecord(this.salesman, record, this); //create window
        recordFrame.setVisible(true);
    }
}
