import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

public class Overview extends JFrame{
    private JButton addSalesmanButton;
    private JButton removeSalesmanButton;
    private JTable staffTable;
    private DefaultTableModel tm;
    private JPanel mainPanel;
    private JButton refreshButton;
    private JTextField searchBar;
    private JButton searchButton;
    private JRadioButton firstnameRadio;
    private JRadioButton lastnameRadio;

    /**
     * main window for displaying all the salesmen and basic management functionalities
     * @throws IOException
     */
    public Overview() throws IOException {
        super("Overview");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop whole program, when this window is closed
        this.setContentPane(mainPanel);

        tm = new DefaultTableModel(){
          public boolean isCellEditable(int row, int column){
              return false;
          }
        }; //specify table as not editable
        staffTable.setModel(tm);
        //setting column-headers:
        tm.addColumn("ID");
        tm.addColumn("Firstname");
        tm.addColumn("Lastname");
        staffTable.getTableHeader().resizeAndRepaint();

        this.pack();

        Icon searchIcon = new ImageIcon("src/main/icons/search.png"); //loading icon of the search button
        Icon refreshIcon = new ImageIcon("src/main/icons/refresh.png"); //loading icon of the refresh button

        searchButton.setIcon(searchIcon);
        refreshButton.setIcon(refreshIcon);

        staffTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && row > -1) { //executed if salesman is double-clicked
                    openDetails(Integer.parseInt(table.getModel().getValueAt(row, 0).toString())); //opening details-window
                }
            }
        });

        Overview current = this;

        addSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Add Salesman" button pressed
                JFrame addFrame = new AddSalesMan(current); //create window for add form
                addFrame.setVisible(true);
            }
        });

        removeSalesmanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //"Remove Salesman" button pressed
                if(staffTable.getSelectedRow() >= 0) { //only execute if a salesman has been selected
                    Main.mP.deleteSalesMen(Integer.parseInt(staffTable.getValueAt(staffTable.getSelectedRow(), 0).toString()));
                    loadStaff();
                }
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//"refresh" button pressed
                loadStaff(); //reload staff table
                searchBar.setText(""); //empty search-bar
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//"search" button pressed
                if(!searchBar.getText().isBlank()){ //check if search bar is not blank
                    displaySalesMen(Main.mP.querySalesMan(searchBar.getText(), (firstnameRadio.isSelected() ? "firstname" : "lastname"))); //search for salesman having this property
                }
            }
        });

        loadStaff(); //load staff table on first load
    }

    /**
     * function to display salesmen in the staff table
     * @param sm list of salesmen to display
     */
    public void displaySalesMen(List<SalesMan> sm){
        for (int i = tm.getRowCount() - 1; i >= 0; i--) { //first empty the table
            tm.removeRow(i);
        }
        for(SalesMan salesMan : sm){ //iterating through the list to add all salesmen
            tm.addRow(new Object[]{Integer.toString(salesMan.getId()), salesMan.getFirstname(), salesMan.getLastname()});
        }
    }

    /**
     * function for (re)loading all salesmen into the staff table
     */
    public void loadStaff(){
        displaySalesMen(Main.mP.getAllSalesMen());
    }

    /**
     * function for opening a details window
     * @param id specifies the salesman, whose details are shown
     */
    public void openDetails(int id){
        SalesMan sm = Main.mP.readSalesMan(id); //get salesman fromDB
        JFrame detailsFrame = new StaffDetails(sm, this); //create window
        detailsFrame.setVisible(true);
    }
}
