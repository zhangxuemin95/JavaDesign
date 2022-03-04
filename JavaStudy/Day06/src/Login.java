import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JRadioButton underGraduate;
    private JRadioButton graduated;
    private JRadioButton teacher;
    private JRadioButton libraryManager;
    private JRadioButton systemManager;
    private JLabel jl_userid;
    private JTextField txt_userid;
    private JPasswordField jp_password;
    private JLabel jl_password;
    private JButton ensure;
    private JButton register;
    public Login(){
        initial();
    }

    public void initial() {
        setTitle("图书管理系统");
        setVisible(true);
        setBounds(500, 500, 800, 600);
//        setLocation();
//        setSize();
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setView();
        pack();
        setResizable(false);
    }

    public void setView()
    {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        underGraduate = new JRadioButton("本科生");
        graduated = new JRadioButton("研究生");
        teacher = new JRadioButton("教师");
        libraryManager = new JRadioButton("图书管理员");
        systemManager = new JRadioButton("系统管理员");
        JPanel usertype = new JPanel();
        JPanel hello = new JPanel();
        usertype.setLayout(new FlowLayout());
        hello.setLayout(new BorderLayout());
        contentPane.add(hello, BorderLayout.NORTH);
        ButtonGroup type = new ButtonGroup();
        type.add(underGraduate);
        type.add(graduated);
        type.add(teacher);
        type.add(libraryManager);
        type.add(systemManager);
        JLabel jl_hello = new JLabel("欢迎使用图书管理系统");
        jl_hello.setHorizontalAlignment(SwingConstants.CENTER);
        hello.add(jl_hello, BorderLayout.NORTH);
        hello.add(usertype, BorderLayout.CENTER);
        usertype.add(underGraduate);
        usertype.add(graduated);
        usertype.add(teacher);
        usertype.add(libraryManager);
        usertype.add(systemManager);
        JPanel login = new JPanel();
        contentPane.add(login, BorderLayout.SOUTH);
        login.setLayout(new GridLayout(3, 2));
        jl_userid = new JLabel("账号 :");
        txt_userid = new JTextField("");
        jp_password = new JPasswordField();
        jp_password.setEchoChar('*');
        jl_password = new JLabel("密码 :");
        ensure = new JButton("登录");
        register = new JButton("注册");
        login.add(jl_userid);
        login.add(txt_userid);
        login.add(jl_password);
        login.add(jp_password);
        login.add(ensure);
        login.add(register);
    }

    private class ensure_ActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class regisetr_ActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
