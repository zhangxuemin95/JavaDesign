import org.w3c.dom.Text;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Practise {
    public static void main(String[] args) {
        Frame frame = new Frame("我的第一个GUI界面");//申请内存后默认是不可见的
        //设置可见性
        frame.setVisible(true);
        //设置窗口大小
        frame.setSize(200,200);
        //设置窗口颜色
        frame.setBackground(new Color(198, 17, 17));//传递三原色的数值，创建Color对象
        //frame.setBackground(Color.cyan);,或直接调用静态变量
        //设置初始位置
        frame.setLocation(200, 200);
        //设置大小固定
        frame.setResizable(false);//默认为true，Resizable大小可变；
        TextField textField = new TextField();
        TextArea textArea = new TextArea();
        textField.setEchoChar('a');
        //frame.setBounds();//可同时设置窗口大小和窗口位置
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }
}
