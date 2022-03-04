package UI;

import javax.swing.*;
import java.awt.*;

public class Restore extends JFrame {//恢复界面
    int width = 400, height = 200;//定义窗口长度和宽度
    public Restore(){
        super("恢复");

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();//获取当前电脑屏幕大小
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
        this.setBounds(screenSize.width/2-width/2,screenSize.height/2-height/2,width,height);//让窗口居中
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Restore();
    }
}
