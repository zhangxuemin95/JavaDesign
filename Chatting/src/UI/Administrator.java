package UI;

import entity.Sendor;
import entity.UserInfo;
import intnet.Tool;
import service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Administrator extends JFrame {
    UserInfo user = null;
    AdminService adminService = null;
    int width = 400, height = 150;//定义窗口长度和宽度
    JPanel panel;
    JButton delete,alterlevel,restore;
    public static void main(String[] arg){
        //new Administrator("管理员界面");
    }
    public Administrator(String s, UserInfo user){
        super(s);
        this.user = user;
        panel=new JPanel();
        delete=new JButton("删除用户");
        alterlevel=new JButton("修改隔离级别");
        restore = new JButton("恢复");
        panel.add(delete);
        panel.add(alterlevel);
        panel.add(restore);

        //删除用户
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new deleteuser();
                adminService.deleteUser();
            }
        });

        //修改隔离级别
        alterlevel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AlterLevel(adminService);
            }
        });

        //恢复
        restore.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Restore();
                adminService.restore("wushuaikang.txt", "chattingrecord");
                JOptionPane.showMessageDialog(null, "恢复成功!", "提示", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();//获取当前电脑屏幕大小
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
        this.add(panel,BorderLayout.NORTH);
        this.setBounds(screenSize.width/2-width/2,screenSize.height/2-height/2,width,height);//让窗口居中
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        iniService(user);
        //下线
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                try {
                    Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
                    Sendor sendor = new Sendor();
                    String username = user.getAccountnum();
                    sendor.setUserInfo(user);
                    sendor.setType(6);
                    Tool.sendSender(socket, sendor);
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    public void iniService(UserInfo user)
    {
        adminService = new AdminService(user);
    }
}
