import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GraphicP {

    public static void main(String[] args) {
        new MyFrame().init();
    }
}


class MyFrame extends JFrame {
    public MyFrame() {
        setBounds(500, 500, 200, 200);
        this.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {

        JTextField jTextField = new JTextField("afafaf", 40);
        JPanel jPanel = new JPanel();
        JPasswordField jPasswordField = new JPasswordField(10);
        jPasswordField.setEchoChar('a');
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(jPanel);
    }
}

