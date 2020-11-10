import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Overview extends JFrame{
    private JButton addSalesmanButton;
    private JButton removeSalesmanButton;
    private JTable staffTable;
    private DefaultTableModel tm;
    private JPanel mainPanel;
    private JButton refreshButton;

    public Overview() throws IOException {
        super("Overview");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        tm = new DefaultTableModel(){
          public boolean isCellEditable(int row, int column){
              return false;
          }
        };
        staffTable.setModel(tm);
        tm.addColumn("ID");
        tm.addColumn("Firstname");
        tm.addColumn("Lastname");
        staffTable.getTableHeader().resizeAndRepaint();

        this.pack();

        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && row > -1) {
                    openDetails(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
                }
            }
        });

        addSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new AddSalesMan();
                addFrame.setVisible(true);
            }
        });

        removeSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(staffTable.getSelectedRow() >= 0) {
                    System.out.println(staffTable.getValueAt(staffTable.getSelectedRow(), 0));
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStaff();
            }
        });

        loadStaff();
    }

    public void displaySalesMen(List<SalesMan> sm){
        for(SalesMan salesMan : sm){
            tm.addRow(new Object[]{Integer.toString(salesMan.getId()), salesMan.getFirstname(), salesMan.getLastname()});
        }
    }

    public void loadStaff(){
        displaySalesMen(Arrays.asList(new SalesMan[]{new SalesMan("Jonas", "Brill", 123)}));
        displaySalesMen(Arrays.asList(new SalesMan[]{new SalesMan("Luca", "Ringhausen", 1234)}));
        displaySalesMen(Main.mP.querySalesMan("123", "id"));
    }

    public void openDetails(int id){
        System.out.println(id);

        SalesMan sm = Main.mP.readSalesMan(id); //new SalesMan("Jonas", "Brill", 123);
        JFrame detailsFrame = new StaffDetails(sm);
        detailsFrame.setVisible(true);
    }
}
