import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StaffDetails extends JFrame{
    private JTable recordsTable;
    private JPanel detailsPanel;
    private JLabel id;
    private JLabel firstname;
    private JLabel lastname;
    private JButton addRecod;
    private DefaultTableModel tm;

    public StaffDetails(SalesMan sm){
        super("Salesman - " + sm.getFirstname() + " " + sm.getLastname());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(detailsPanel);

        this.pack();

        tm = new DefaultTableModel(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        recordsTable.setModel(tm);
        tm.addColumn("Wert");
        recordsTable.getTableHeader().resizeAndRepaint();

        id.setText(Integer.toString(sm.getId()));
        firstname.setText(sm.getFirstname());
        lastname.setText(sm.getLastname());
        loadRecords(sm);

        addRecod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addEvalFrame = new AddEvalRecord(sm);
                addEvalFrame.setVisible(true);
            }
        });
    }

    private void loadRecords(SalesMan sm){
        for (int i = tm.getRowCount() - 1; i >= 0; i--) {
            tm.removeRow(i);
        }
        for(EvaluationRecord record : Main.mP.readEvaluationRecords(sm.getId())){
            tm.addRow(new Object[]{record.getPerformance()});
        }
    }
}
