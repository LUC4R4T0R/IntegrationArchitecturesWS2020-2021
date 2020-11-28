package de.hbrs.ia.swing;

import de.hbrs.ia.SwingUI;
import de.hbrs.ia.model.EvaluationRecord;
import de.hbrs.ia.model.EvaluationRecordEntry;
import de.hbrs.ia.model.SalesMan;
import de.hbrs.ia.model.SalesManRecord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jbrill2s, lringh2s
 * <p>
 * This class reacts to the actions on the EvalRecord-UI.
 */
public class EvalRecord extends JFrame {
    private JPanel evalRecordPanel;
    private JTable recordTable;
    private JButton saveButton;
    private JButton removeEntryButton;
    private JButton abortButton;
    private JButton addEntryButton;
    private JTextField yearInput;
    private JButton createButton;
    private DefaultTableModel tm;
    private EvaluationRecord evaluationRecord;
    private SalesMan salesMan;
    private StaffDetails parent;

    public EvalRecord(SalesMan salesMan, StaffDetails parent) {
        this(salesMan, new EvaluationRecord(), parent);
    }

    public EvalRecord(SalesMan salesMan, EvaluationRecord evaluationRecord, StaffDetails parent) {
        super("Evaluation Record");
        this.evaluationRecord = evaluationRecord;
        this.salesMan = salesMan;
        this.parent = parent;
        if (this.evaluationRecord.getYear() == 0) {
            createButton.setEnabled(true);
            yearInput.setEnabled(true);
            createButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        pushRecord();
                        createButton.setEnabled(false);
                        yearInput.setEnabled(false);
                        enableLower();
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "This salesman has already a record for that year!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        } else {
            yearInput.setText(Integer.toString(this.evaluationRecord.getYear()));
            enableLower();
        }
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //close only this window
        this.setContentPane(evalRecordPanel);
        this.pack();


        tm = new DefaultTableModel();
        recordTable.setModel(tm);
        tm.addColumn("Description");
        tm.addColumn("Target Value");
        tm.addColumn("Actual Value");
        recordTable.getTableHeader().resizeAndRepaint();

        loadEntries();

        addEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });

        removeEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recordTable.getSelectedRow() >= 0) {
                    int n = JOptionPane.showOptionDialog(new JFrame(), "Do you really want wo delete this entry?", "Evaluation Record Entry Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "No");
                    if (n == 0) {
                        tm.removeRow(recordTable.getSelectedRow());
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecord();
                kill();
            }
        });

        abortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kill();
            }
        });
    }

    private void loadEntries() {
        for (int i = tm.getRowCount() - 1; i >= 0; i--) { //first emptying the table
            tm.removeRow(i);
        }

        for (EvaluationRecordEntry entry : this.evaluationRecord.getPerformance()) { //iterating through the entries to add them all
            tm.addRow(new Object[]{entry.getName(), entry.getTarget(), entry.getActual()});
        }
    }

    private void saveRecord() {
        if (!yearInput.getText().isBlank()) {
            List<EvaluationRecordEntry> temp = new ArrayList<>();
            for (int i = 0; i < recordTable.getRowCount(); i++) {
                temp.add(new EvaluationRecordEntry(
                        Integer.parseInt(recordTable.getValueAt(i, 1).toString()),
                        Integer.parseInt(recordTable.getValueAt(i, 2).toString()),
                        recordTable.getValueAt(i, 0).toString()
                ));
            }
            this.evaluationRecord.setPerformance(temp);
            this.evaluationRecord.setYear(Integer.parseInt(yearInput.getText()));
            SwingUI.mP.updateEvaluationRecord(new SalesManRecord(this.salesMan.getId(), this.evaluationRecord));
        }
    }

    private void addEntry() {
        tm.addRow(new Object[]{});
    }

    private void kill() {
        this.dispose();
    }

    private void enableLower() {
        addEntryButton.setEnabled(true);
        removeEntryButton.setEnabled(true);
        saveButton.setEnabled(true);
    }

    private void pushRecord() throws IllegalArgumentException {
        this.evaluationRecord.setYear(Integer.parseInt(yearInput.getText()));
        SwingUI.mP.addPerformanceRecord(this.evaluationRecord, this.salesMan.getId());
        this.parent.update();
    }
}
