import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        /*Image resource1 = ImageIO.read(new File("src//icons//add_folder.svg"));
        Icon icon1 = new ImageIcon(resource1);
        button1.setIcon(icon1);*/
        addSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame addFrame = new AddSalesMan();
                addFrame.setVisible(true);
            }
        });

        //displaySalesMen(Arrays.asList(new SalesMan[]{new SalesMan("Jonas", "Brill", 123)}));
        loadStaff();
    }

    public void displaySalesMen(List<SalesMan> sm){
        for(SalesMan salesMan : sm){
            tm.addRow(new Object[]{Integer.toString(salesMan.getId()), salesMan.getFirstname(), salesMan.getLastname()});
        }
    }

    public void loadStaff(){
        displaySalesMen(Main.mP.querySalesMan("123", "id"));
    }
}
