import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Homework {
    public static void main(String[] args) {
        Frame frame = new Frame();
        frame.setSize(200, 200);
        frame.setLocation(400, 400);
        frame.setBackground(Color.green);
        //frame.setBounds(400, 400, 200, 200);
        GridLayout gridLayout = new GridLayout(2, 1);
        frame.setLayout(gridLayout);
        Panel panel1 = new Panel();
        Panel panel2 = new Panel();
        Panel panel3 = new Panel();
        Panel panel4 = new Panel();
        Button button = new Button("button1");
        Button button1 = new Button("button2");
        Button button2 = new Button("button3");
        Button button3 = new Button("button4");
        Button button4 = new Button("button5");
        Button button5 = new Button("button6");
        Button button6 = new Button("button7");
        Button button7 = new Button("button8");
        Button button8 = new Button("button9");
        Button button9 = new Button("button10");
        frame.add(panel1);
        frame.add(panel2);
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel1.add(panel3, BorderLayout.CENTER);
        panel1.add(button1, BorderLayout.WEST);
        panel1.add(button, BorderLayout.EAST);
        panel2.add(panel4, BorderLayout.CENTER);
        panel2.add(button2, BorderLayout.WEST);
        panel2.add(button3, BorderLayout.EAST);
        panel3.setLayout(new GridLayout(2,1));
        panel3.add(button4);
        panel3.add(button5);
        panel4.setLayout(new GridLayout(2, 2));
        panel4.add(button6);
        panel4.add(button9);
        panel4.add(button7);
        panel4.add(button8);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
}
