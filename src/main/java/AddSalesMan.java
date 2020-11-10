import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSalesMan extends JFrame{
    private JTextField id;
    private JTextField firstname;
    private JTextField lastname;
    private JButton addButton;
    private JPanel addPanel;

    public AddSalesMan(){
        super("Add Salesman");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setContentPane(addPanel);
        this.pack();



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesMan newSalesMan = new SalesMan(firstname.getText(), lastname.getText(), Integer.parseInt(id.getText()));
                System.out.println(newSalesMan);
                Main.mP.createSalesMan(newSalesMan);
                kill();
            }
        });
    }

    private void kill(){
        this.dispose();
    }
}
