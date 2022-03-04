import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PractiseLayout {
    public static void main(String[] args) {
        Button button1 = new Button("east");
        Button button2 = new Button("west");
        Button button3 = new Button("north");
        Button button4 = new Button("south");
        Button button5 = new Button("center");
        Frame frame = new Frame();
        GridLayout gridLayout = new GridLayout(2, 3, 10, 3);
        frame.setLayout(gridLayout);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);

        frame.setVisible(true);
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(1);
            }
        });
    }
}
