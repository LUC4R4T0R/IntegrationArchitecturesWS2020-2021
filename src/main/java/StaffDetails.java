import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StaffDetails extends JFrame{
    private JTable recordsTable;
    private JPanel detailsPanel;
    private JLabel id;
    private JLabel firstname;
    private JLabel lastname;
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
        tm.addColumn("ID");
        tm.addColumn("Firstname");
        tm.addColumn("Lastname");
        recordsTable.getTableHeader().resizeAndRepaint();

        id.setText(Integer.toString(sm.getId()));
        firstname.setText(sm.getFirstname());
        lastname.setText(sm.getLastname());
    }
}
