import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Overview extends JFrame{
    private JButton addSalesmanButton;
    private JButton removeSalesmanButton;
    private JTable staffTable;
    private JPanel mainPanel;
    private JButton refreshButton;

    public Overview() throws IOException {
        super("Overview");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
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
    }
}
