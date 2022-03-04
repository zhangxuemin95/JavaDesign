package UI;

import service.AdminService;
import service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterLevel extends JFrame {//设置隔离级别
    int width = 400, height = 200;//定义窗口长度和宽度
    JComboBox isolationLevel = new JComboBox(new String[]{"read uncommitted", "read committed", "repeatable read", "serializable"});
    JButton button = new JButton("确定");
    AdminService adminService;
    public AlterLevel(AdminService adminService){
        super("修改隔离级别");
        this.adminService = adminService;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();//获取当前电脑屏幕大小
        //this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
        this.setBounds(screenSize.width/2-width/2,screenSize.height/2-height/2,width,height);//让窗口居中
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBackground(Color.green);
        jPanel.add(isolationLevel, BorderLayout.CENTER);
        jPanel.add(button, BorderLayout.SOUTH);
        this.setContentPane(jPanel);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminService.alterLevel((String) isolationLevel.getSelectedItem());
            }
        });
    }

    public static void main(String[] args) {
    }
}
