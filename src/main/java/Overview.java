import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Overview extends JFrame{
    private JButton button1;
    private JButton button2;
    private JTable table1;
    private JPanel mainPanel;

    public Overview() throws IOException {
        super("Overview");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        /*Image resource1 = ImageIO.read(new File("src//icons//add_folder.svg"));
        Icon icon1 = new ImageIcon(resource1);
        button1.setIcon(icon1);*/
    }

}
