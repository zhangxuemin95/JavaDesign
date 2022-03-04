package UI;

import entity.ChattingRecord;
import entity.Sendor;
import entity.User;
import entity.UserInfo;
import intnet.Tool;
import service.ComUserService;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;


class registerFrame extends JDialog{
    String[] ages = {"0","1","2","3","4","5","6","7","8","9","10"};
    JPanel jPanel = new JPanel();

    JLabel name = new JLabel("name") ;
    JLabel age =  new JLabel("age");
    JLabel address = new JLabel("address");
    JLabel accountNum = new JLabel("accountNum");
    JLabel password = new JLabel("password");
    JLabel conPassword = new JLabel("confirmPsd");

    JButton register = new JButton("register");
    JButton cancel = new JButton("cancel");
    JButton clear = new JButton("clear");

    JTextField nameText = new JTextField(20);
    JTextField ageText = new JTextField(20);
    JTextField addressText = new JTextField(20);
    JTextField accNumText = new JTextField(20);
    JPasswordField passwordText = new JPasswordField(20);
    JPasswordField conPasswordText = new JPasswordField(20);
    JComboBox ageCmb = new JComboBox(ages);


    public void registerFrame(){
        this.setTitle("用户注册");
        this.setLocation(200,100);
        this.setSize(800,800);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        //设置布局
        jPanel.setLayout(null);
        //ageCmb.addItem(ages);

        name.setBounds(200,100,100,40);
        age.setBounds(200,200,100,40);
        address.setBounds(200,300,100,40);
        accountNum.setBounds(200,400,100,40);
        password.setBounds(200,500,100,40);
        conPassword.setBounds(200,600,100,40);
        nameText.setBounds(350,100,200,40);
        ageText.setBounds(350,200,200,40);
        ageCmb.setBounds(350,200,200,40);
        addressText.setBounds(350,300,200,40);
        accNumText.setBounds(350,400,200,40);
        passwordText.setBounds(350,500,200,40);
        conPasswordText.setBounds(350,600,200,40);
        register.setBounds(150,700,100,40);
        clear.setBounds(350,700,100,40);
        cancel.setBounds(550,700,100,40);

        this.add(jPanel);
        jPanel.add(name);
        jPanel.add(age);
        jPanel.add(address);
        jPanel.add(accountNum);
        jPanel.add(password);
        jPanel.add(conPassword);
        jPanel.add(nameText);
        //jPanel.add(ageText);
        jPanel.add(ageCmb);
        jPanel.add(addressText);
        jPanel.add(accNumText);
        jPanel.add(passwordText);
        jPanel.add(conPasswordText);
        jPanel.add(register);
        jPanel.add(clear);
        jPanel.add(cancel);
        //限制密码的字符
        passwordText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char keyChar = e.getKeyChar();
                if(!((keyChar>='0'&&keyChar<='9')||(keyChar>='A'&&keyChar<='Z')||(keyChar>='a'&&keyChar<='z'))){
                    e.consume();
                }
            }
        });
        //清空输入框
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                ageText.setText("");
                addressText.setText("");
                accNumText.setText("");
                passwordText.setText("");
                conPasswordText.setText("");
            }
        });
        //取消注册
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null,"确认取消注册吗？","提示",JOptionPane.YES_NO_OPTION);
                if(res == JOptionPane.YES_OPTION){
                    nameText.setText("");
                    ageText.setText("");
                    addressText.setText("");
                    accNumText.setText("");
                    passwordText.setText("");
                    conPasswordText.setText("");
                    setDefaultCloseOperation(registerFrame.DISPOSE_ON_CLOSE);
                }
            }
        });
        //注册事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check())
                    JOptionPane.showMessageDialog(null,"恭喜注册成功","提示",JOptionPane.PLAIN_MESSAGE);
            }
        });
        //关闭窗口
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });
        this.setVisible(true);
    }
    public boolean check(){
        if(nameText.getText().equals("")||addressText.getText().equals("")||passwordText.getPassword().equals("")||conPasswordText.getPassword().equals("")){
            JOptionPane.showMessageDialog(null,"请输入完整信息","注册失败",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(nameText.getText().length()>20){
            JOptionPane.showMessageDialog(null,"你取的名字或许太长了。","注册未成功",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(ageText.getText().length()>20){
            JOptionPane.showMessageDialog(null,"请你认真填写年龄","注册失败",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(!Arrays.equals(passwordText.getPassword(), conPasswordText.getPassword())){
            JOptionPane.showMessageDialog(null,"两次输入的密码不一致","注册失败",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        UserInfo userInfo = new UserInfo(accNumText.getText(), passwordText.getText());
        userInfo.setName(nameText.getText());
        System.out.println(ageText.getText());
        userInfo.setAge(ageCmb.getItemCount());
        userInfo.setAddress(addressText.getText());
        Socket socket = null;
        int result = -1;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor = new Sendor();
            sendor.setUserInfo(userInfo);
            sendor.setType(5);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            result = sendor.getResult();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result == 1) return false;
        return true;
    }
}
class logFrame extends JFrame {
    int type = 0;
    JPanel panel = new JPanel();

    JButton logIn = new JButton("登录");
    JButton clear = new JButton("clear");
    JButton register = new JButton("注册");
    JButton swap = new JButton("切换");
    JLabel label1 = new JLabel("用户名");
    JLabel label2 = new JLabel("密码");
    JLabel label3 = new JLabel("管理员ID");
    JTextField username = new JTextField(20);
    JPasswordField password = new JPasswordField(20);
    public void lunchFrame(){
        this.setTitle("用户登录");
        this.setLocation(200,100);
        this.setSize(800,600);
        //this.setLayout(new FlowLayout());

        panel.setLayout(null);

        //TextArea  user = new TextArea(10,40);
        swap.setBounds(50,50,100,30);
        logIn.setBounds(150,300,100,30);
        clear.setBounds(350,300,100,30);
        register.setBounds(550,300,100,30);
        label1.setBounds(200,150,100,30);
        label2.setBounds(200,200,100,30);
        label3.setBounds(200,150,100,30);
        username.setBounds(300,150,200,30);
        password.setBounds(new Rectangle(300,200,200,30));

        this.add(panel);
        panel.add(logIn);
        panel.add(clear);
        panel.add(register);
        panel.add(label1);
        panel.add(label2);
        panel.add(username);
        panel.add(password);
        panel.add(swap);

        swap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (type){
                    case 0:
                        type = 1;//切换到管理员界面
                        panel.removeAll();
                        panel.add(logIn);
                        panel.add(clear);
                        panel.add(register);
                        panel.add(label3);
                        panel.add(label2);
                        panel.add(username);
                        panel.add(password);
                        panel.add(swap);
                        panel.updateUI();
                        panel.revalidate();
                        panel.repaint();
                        break;
                    case 1:
                        type = 0;//切换到用户界面
                        panel.removeAll();
                        panel.add(logIn);
                        panel.add(clear);
                        panel.add(register);
                        panel.add(label1);
                        panel.add(label2);
                        panel.add(username);
                        panel.add(password);
                        panel.add(swap);
                        panel.revalidate();
                        panel.repaint();
                        break;
                }
            }
        });
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //登录按钮监听事件，对用户得输入进行识别。
                if(username.getText().equals("") || password.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"用户名或密码为空！","你是不是2货？",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    UserInfo userInfo = new UserInfo(username.getText(), password.getText());
                    userInfo.setType(type);
                    int result = -1;
                    try {
                        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
                        Sendor sendor = new Sendor();
                        sendor.setUserInfo(userInfo);
                        sendor.setType(0);
                        Tool.sendSender(socket, sendor);
                        sendor = Tool.acceptSender(socket);
                        result = sendor.getResult();
                        userInfo.setName(sendor.getKeyword());
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //setDefaultCloseOperation(logFrame.EXIT_ON_CLOSE);
                    switch (result)
                    {
                        case 0:
                            JOptionPane.showMessageDialog(null,"登录成功!","提示",JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            switch (type)
                            {
                                case 0:
                                    mainChatFrame chatFrame = new mainChatFrame();
                                    chatFrame.mainChatFrame(userInfo);
                                    break;
                                case 1:
                                    UserInfo user = new UserInfo(username.getText(), password.getText());
                                    user.setType(1);
                                    new Administrator("管理员界面", user);
                                    break;
                            }
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null,"该用户已上线!","提示",JOptionPane.ERROR_MESSAGE);
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(null,"账号或密码不正确!","提示",JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                }
                //对用户的合法性检验
                //else if(username == ) {

                //}
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username.setText("");
                password.setText("");
            }
        });
        //注册按钮事件
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
                registerFrame registerFrame = new registerFrame();
                registerFrame.registerFrame();

            }
        });
        //关闭窗口
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    this.setVisible(true);
    }


}
class mainChatFrame extends JFrame{
    ComUserService comUserService;
    DefaultListModel<String> friends = new DefaultListModel<>();
    JPanel panel = new JPanel();
    UserInfo user;
    String[] statue = new String[]{"在线","隐身","勿扰"};
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Operate");
    JMenuItem item1 = new JMenuItem("加好友");
    JMenuItem item2 = new JMenuItem("查找聊天记录");
    JMenuItem item3 = new JMenuItem("删除聊天记录");
    JMenuItem item4 = new JMenuItem("备份聊天记录");
    JLabel name = new JLabel("我的昵称");
    JComboBox<String> box = new JComboBox<String>(statue);
    JList<String> jList = new JList<>(friends);
    JTabbedPane tab = new JTabbedPane();
    JLabel tabLabelA = new JLabel("消息");
    JLabel tabLabelB = new JLabel("联系人");
    JLabel tabLabelC = new JLabel("群组");

    public void fillFriends() throws SQLException {
        ArrayList rs = comUserService.selectFriends();
        for(int i = 0; i < rs.size(); i++)
        {
            friends.add(i, (String) rs.get(i));
        }
    }

    public void mainChatFrame(UserInfo user){
        this.user = user;
        comUserService = new ComUserService(user);
        try {
            fillFriends();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.setTitle("用户主界面");
        this.setLocation(400,150);
        //this.setLocationRelativeTo(null);
        this.setSize(420,800);
        //this.setLayout(new FlowLayout());
        panel.setLayout(null);

        this.add(panel);
        this.setJMenuBar(menuBar);

        name.setBounds(250,15,100,30);
        box.setBounds(250, 45, 80, 20);
        // 设置选项卡透明（需放置在创建之前）
        UIManager.put("TabbedPane.contentOpaque", false);
        // 创建选项卡
        JScrollPane jsp = new JScrollPane();
        this.AddJSPaneToTab(tab,jsp,"消息");
        // 创建滚动面板
        JScrollPane jsp1 = new JScrollPane();
        this.AddJSPaneToTab(tab, jsp1, "联系人");
        jsp1.setViewportView(jList);
        JScrollPane jsp2 = new JScrollPane();
        this.AddJSPaneToTab(tab, jsp2, "群组");

        tab.setBounds(0, 100, 400, 650);

        panel.add(tab);
        panel.add(name);
        panel.add(box);

        menuBar.add(menu);
        menu.add(item1);//加好友
        menu.add(item2);//查找聊天记录
        menu.add(item3);//删除聊天记录
        menu.add(item4);//备份聊天纪录

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String friendNum = JOptionPane.showInputDialog(null, "请输入要添加好友的id", "添加好友", JOptionPane.INFORMATION_MESSAGE);
                Sendor sendor = comUserService.addFriend(friendNum);
                int result = sendor.getResult();
                switch (result)
                {
                    case -1:
                        JOptionPane.showMessageDialog(null, "你和自己已经是朋友", "提示", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        friends.add(friends.size(),friendNum + "  " + sendor.getFriendNum() + "\n");
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(null, "该用户不存在", "提示", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "该用户已是好友", "提示", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList resultSet = comUserService.selectChattingRecords();
                new chatRecord(resultSet);
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = comUserService.deleteRecords();
                if(result > 0) JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    else JOptionPane.showMessageDialog(null, "聊天记录为空", "提示", JOptionPane.WARNING_MESSAGE);
            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comUserService.backup();
            }
        });

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
        this.setVisible(true);
        jList.addMouseListener(new MouseAdapter() //列表框添加鼠标事件
        {
            public void mousePressed(MouseEvent e)
            {
                String friend = jList.getSelectedValue();
                if(friend == null) return;
                else {
                    String a[] = friend.split(  "  ");
                    String friendnum = a[0];
                    String friendname = a[1];
                    User accepter = new User();
                    accepter.setAccountnum(friendnum);
                    accepter.setName(friendname);
                    User sender = new User();
                    sender.setAccountnum(user.getAccountnum());
                    sender.setName(user.getName());
                    comUserService.chatting(sender, accepter);
                }
            }
        });
    }

    // 将滚动面板添加的选项卡里
    public void AddJSPaneToTab(JTabbedPane tab, JScrollPane jsp, String str) {
        // 将滚动面板设置透明
        //jsp.getViewport().setOpaque(false);
        jsp.setName(str);
        // 显示滚动条
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        tab.add(jsp);
    }
}
class Main {
    public static void main (String[] args){
        logFrame lunchFrame = new logFrame();
        lunchFrame.lunchFrame();
    }
}

