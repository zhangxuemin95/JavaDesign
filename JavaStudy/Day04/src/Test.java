import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Test {
    public static void main(String[] args) {
        Calculator calculator = new Calculator("加法计算器");
    }
}

class Calculator extends Frame {
    private TextField num1;
    private TextField num2;
    private TextField result;
    private Label sign;
    Button button;
    public Calculator(String title) {
        super(title);
        num1 = new TextField();
        num2 = new TextField();
        result = new TextField();
        button = new Button("=");
        sign = new Label("+");
        init();
    }

    public void init() {
        setLayout(new FlowLayout());
        setSize(800, 200);
        num1.setSize(200, 200);
        num2.setSize(200, 200);
        result.setSize(200, 200);
        button.setBackground(Color.BLUE);
        button.setSize(100, 200);
        sign.setBackground(Color.blue);
        sign.setSize(100, 200);
        add(num1); add(sign); add(num2); add(button); add(result);
        button.addActionListener(new EqualActionListener());
        pack();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }

    class EqualActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double a = Double.parseDouble(num1.getText());
            double b = Double.parseDouble(num2.getText());
            num1.setText("");
            num2.setText("");
            result.setText(a + b + "");
        }
    }
}



