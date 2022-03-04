package UI;

import entity.ChattingRecord;
import entity.Sendor;
import entity.User;
import entity.UserInfo;
import intnet.Tool;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Frame extends JFrame implements Runnable {//聊天界面
    ServerSocket serverSocket;
    User sender;
    User accepter;
    int port;
    int width = 800, height = 600;//定义窗口长度和宽度
    public JTextArea chat1,chat2;
    JScrollPane scrollPane1,scrollPane2;
    JPanel west;//面板
    Timer time;//动态时间显示
    JButton send;
    JLabel timeLabel;
    Date date;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");


    public  JLabel getTimeLabel(){
        JLabel timeLabel;
        timeLabel = new JLabel();
        timeLabel.setPreferredSize(new Dimension(300,100));
        timeLabel.setFont(new Font("微软雅黑",2,15));
        time = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLabel.setText(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
            }
        });
        time.start();
        return timeLabel;
    }

    public void acceptMessage(String content, Date date)
    {
        chat2.append(simpleDateFormat.format(date));
        chat2.append("\n"+content+"\n");
    }


    public void SendByEnter(JTextArea c1,JTextArea c2){
        String s = c1.getText();
        date = new Date(System.currentTimeMillis());
        c1.setText("");
        c2.append(simpleDateFormat.format(date));
        c2.append("\n"+s+"\n");
    }

    public void SendByButton(JTextArea c1,JTextArea c2){
        String s = c1.getText();
        date = new Date(System.currentTimeMillis());
        c1.setText("");
        c2.append(simpleDateFormat.format(date));
        c2.append("\n"+s+"\n\n");
        try {
            Socket socket2 = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor2 = new Sendor();
            sendor2.setType(8);
            ChattingRecord chattingRecord = new ChattingRecord();
            chattingRecord.setDate(new Date());
            chattingRecord.setSender(sender);
            chattingRecord.setAccepter(accepter);
            chattingRecord.setContent(s);
            sendor2.setChattingType(port);
            sendor2.setChatting(chattingRecord);
            Tool.sendSender(socket2, sendor2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JButton wrapJButton(JButton psend,int width,int height,Color color){
        psend.setSize(width,height);
        psend.setFont(new Font("宋体",1,20));
        psend.setForeground(color);
        psend.setFocusPainted(false);//去掉焦点边框
        psend.setBorderPainted(false);//去掉按钮边框
        psend.setContentAreaFilled(false);//去掉按钮填充
        psend.setOpaque(false);//按键透明化
        psend.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            //鼠标进入按钮内则变色，方便用户识别
            @Override
            public void mouseEntered(MouseEvent e) {
                psend.setForeground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                psend.setForeground(color);
            }
        });
        return psend;
    }

    public Frame(String title, int port, User sender, User accepter) throws IOException {
        //chatroom.setUndecorated(true);//去掉本来的窗口装饰
        //chatroom.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);//采用其他装饰风格
        super(title);//设置标题
        Container container = this.getContentPane();
        this.port = port;
        this.sender = sender;
        this.accepter = accepter;
        serverSocket = new ServerSocket(port);
        container.setBackground(Color.white);//颜色
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();//获取当前电脑屏幕大小
        this.setBounds(screenSize.width/2-width/2,screenSize.height/2-height/2,width,height);//让窗口居中
        //文本
        //初始化
        chat1 = new JTextArea();
        chat2 = new JTextArea();
        chat1.setRows(15);//统一文本区域大小
        chat1.setColumns(75);
        chat1.setLineWrap(true);//自动换行
        chat1.setOpaque(false);//使输入框透明化
        chat1.setSelectedTextColor(Color.white);//选中字体的颜色
        chat1.setSelectionColor(Color.blue);//选中时背景颜色
        chat2.setRows(15);//统一文本区域大小
        chat2.setColumns(75);
        chat2.setLineWrap(true);
        chat2.setSelectedTextColor(Color.white);//选中字体的颜色
        chat2.setSelectionColor(Color.blue);//选中时背景颜色
        scrollPane1 = new JScrollPane(chat1);
        scrollPane2 = new JScrollPane(chat2);

        //text监听类
        chat1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //检测回车
                if(e.getKeyChar() == KeyEvent.VK_ENTER){
                    SendByButton(chat1,chat2);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        chat2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 chat2.setEnabled(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 chat2.setEnabled(true);
            }
        });


        //初始化功能面板
        west = new JPanel();
        west.setBorder(BorderFactory.createLoweredSoftBevelBorder());

        //时间
        timeLabel=getTimeLabel();
        west.add(timeLabel);

        /*
        //小图标
        Image icon = kit.createImage(Chat.class.getResource("hutao.png"));
        this.setIconImage(icon);

        //背景图片
        ImageIcon background = new ImageIcon(icon);
        JLabel backgroundpicture = new JLabel(background);
        backgroundpicture.setPreferredSize(new Dimension(150,80));
        west.add(backgroundpicture);
         */

        //按钮
        send = new JButton("发送");
        send = wrapJButton(send,80,50,Color.gray);//美化
        //按键监听
        send.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!chat1.getText().equals(""))
                SendByButton(chat1,chat2);
            }
        });


        west.add(send);
        this.add(scrollPane1,BorderLayout.SOUTH);
        this.add(scrollPane2,BorderLayout.NORTH);
        this.add(west,BorderLayout.CENTER);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args) {
      // new Frame("");
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Socket socket1 =  serverSocket.accept();
                Sendor sendor1 = Tool.acceptSender(socket1);
                ChattingRecord chattingRecord = sendor1.getChatting();
                acceptMessage(chattingRecord.getContent(), chattingRecord.getDate());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
