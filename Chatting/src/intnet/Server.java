package intnet;

import UI.Frame;
import db.ConnectDB;
import entity.ChattingRecord;
import entity.Sendor;
import entity.UserInfo;
import service.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class Server implements Runnable{
    private Connection con = new ConnectDB().getConection();
    private ServerSocket ss = new ServerSocket(9999);
    private Frame accepterFrame = null;
    public Server() throws IOException {

    }
    @Override
    public void run() {
        while (true)
        {
            System.out.println("服务器正在运行");
            try {
                Socket socket = ss.accept();
                Sendor sender = Tool.acceptSender(socket);
                switch (sender.getType())
                {
                    case 0://处理登录
                        UserInfo user = sender.getUserInfo();
                        PreparedStatement ps = null;
                        try {
                            String sql = "select useraccount.isonline, userinfo.name from useraccount, userinfo where useraccount.accountnum = userinfo.accountnum and useraccount.accountnum = ? and pwd = ? and type = ?";
                            ps = con.prepareStatement(sql);
                            ps.setString(1, user.getAccountnum());
                            ps.setString(2, user.getPwd());
                            ps.setInt(3, user.getType());
                        } catch (SQLException e) {
                            System.out.println("sql语句执行工具获得失败");
                        }
                        ResultSet rs = null;
                        try {
                            rs = ps.executeQuery();
                        } catch (SQLException e) {
                            System.out.println("验证账号sql语句执行失败");
                        }
                        if(rs.next())
                        {
                            if(rs.getInt(1) == 0) {
                                sender.setResult(0);//能成功登录
                                sender.setKeyword(rs.getString(2));
                                try {
                                    ps.close();
                                    ps  = con.prepareStatement("update useraccount set isonline = 1 where accountnum = ?");
                                    ps.setString(1, user.getAccountnum());
                                    ps.executeUpdate();
                                    ps.close();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            else sender.setResult(1);//不能登录成功,已上线
                        }
                        else sender.setResult(2);//账号后密码不正确
                        Tool.sendSender(socket, sender);
                        break;

                    case 1://处理加好友
                        PreparedStatement ps1 = null;
                        UserInfo user1 = null;
                        try {
                            user1 = sender.getUserInfo();
                            String sql = "select userinfo.name from useraccount, userinfo where useraccount.accountnum = ? and type = 0 and useraccount.accountnum = userinfo.accountnum";
                            ps1 = con.prepareStatement(sql);
                            ps1.setString(1, sender.getFriendNum());
                        } catch (SQLException e) {
                            System.out.println("sql语句执行工具获得失败");
                        }
                        ResultSet rs1 = null;
                        try {
                            rs1 = ps1.executeQuery();
                        } catch (SQLException e) {
                            System.out.println("验证账号sql(String)语句执行失败");
                        }
                        if(rs1.next())
                        {
                            String friendname = "";
                            try {
                                friendname = rs1.getString(1);
                                ps1.close();
                                String sql1 = "insert into friend values(?, ?, ?)";
                                ps1 = con.prepareStatement(sql1);
                                ps1.setString(1, user1.getAccountnum());
                                ps1.setString(2, sender.getFriendNum());
                                ps1.setDate(3, new Date(new java.util.Date().getTime()));
                            } catch (SQLException exception) {
                                System.out.println("sql语句执行工具获得失败");
                            }
                            int i = 0;
                            try {
                                i =  ps1.executeUpdate();
                                ps1.setString(1, sender.getFriendNum());
                                ps1.setString(2, user1.getAccountnum());
                                ps1.setDate(3, new Date(new java.util.Date().getTime()));
                                ps1.executeUpdate();
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                            ps1.close();
                            if(i == 0) sender.setResult(2);//用户已是好友
                            else
                            {
                                sender.setFriendNum(friendname);
                                sender.setResult(0);//添加成功
                            }
                        }
                        else
                        {
                            sender.setResult(1);//该用户不存在
                        }
                        Tool.sendSender(socket, sender);
                        break;
                    case 2://查询(和某人的)聊天记录
                        UserInfo user2 = sender.getUserInfo();
                        String keyword = sender.getKeyword();
                        String view_name = "view_" + user2.getAccountnum() + "chat";
                        String condition = "%";
                        if(keyword == "")
                        {

                        }
                        else
                        {
                            condition = condition + keyword + "%";
                        }
                        String sql = "select * from " + view_name + " where friendnum like ? or accountnum like ?";
                        PreparedStatement ps2 = null;
                        ResultSet rs2 = null;
                        try {
                            ps2 = con.prepareStatement(sql);
                            ps2.setString(1, condition);
                            ps2.setString(2, condition);
                            rs2 = ps2.executeQuery();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        sender.setArrayList(Tool.transformChattingRecord(rs2));
                        Tool.sendSender(socket, sender);
                        rs2.close();
                        ps2.close();
                        socket.close();
                        break;
                    case 3://删除聊天记录
                        UserInfo user3 = sender.getUserInfo();
                        String keyword3 = sender.getKeyword();
                        String sql3 = "delete from chattingrecord where (accountnum = ? and friendnum like ?) or (accountnum like ? and friendnum = ?)";
                        String condition3 = "%";
                        if(keyword3 == "")
                        {

                        }
                        else
                        {
                            condition3 = condition3+ keyword3 + "%";
                        }
                        PreparedStatement ps3 = null;
                        int result = 0;
                        try {
                            ps3 = con.prepareStatement(sql3);
                            ps3.setString(1, user3.getAccountnum());
                            ps3.setString(2, condition3);
                            ps3.setString(3, condition3);
                            ps3.setString(4, user3.getAccountnum());
                            result = ps3.executeUpdate();
                            ps3.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        sender.setResult(result);
                        Tool.sendSender(socket, sender);
                        break;
                    case 4://聊天记录备份
                        UserInfo user4 = sender.getUserInfo();
                        String view_name2 = "view_" + user4.getAccountnum() + "chat";
                        String sql4 = "SELECT * FROM " + view_name2 + " INTO OUTFILE ? " +
                                "FIELDS TERMINATED BY \'\\t\' " +
                                "LINES TERMINATED BY \'\\r\\n\'";
                        PreparedStatement ps4 = null;
                        int result4 = 0;
                        try {
                            ps4 = con.prepareStatement(sql4);
                            ps4.setString(1, sender.getKeyword());
                            ps4.execute();
                            ps4.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 5://注册
                        UserInfo user5 = sender.getUserInfo();
                        PreparedStatement ps5 = null;
                        String sql5 = null;
                        try {
                            sql5 = "select accountnum from useraccount where accountnum = ? and type = 0";
                            ps5 = con.prepareStatement(sql5);
                            ps5.setString(1, user5.getAccountnum());
                        } catch (SQLException e) {
                            System.out.println("sql语句执行工具获得失败");
                        }
                        ResultSet rs5 = null;
                        try {
                            rs5 = ps5.executeQuery();
                        } catch (SQLException e) {
                            System.out.println("验证账号sql语句执行失败");
                        }
                        if(rs5.next()) sender.setResult(1); //该账号已存在
                        else {
                            sql5 = "insert into useraccount values(?, ?, 0, 0)";
                            ps5.close();
                            ps5 = con.prepareStatement(sql5);
                            ps5.setString(1, user5.getAccountnum());
                            ps5.setString(2, user5.getPwd());
                            ps5.executeUpdate();
                            sql5 = "insert into userinfo values(?, ?, ?, ?)";
                            ps5.close();
                            ps5 = con.prepareStatement(sql5);
                            ps5.setString(1, user5.getAccountnum());
                            ps5.setString(2, user5.getName());
                            ps5.setInt(3, user5.getAge());
                            ps5.setString(4, user5.getAddress());
                            ps5.executeUpdate();
                            sql5 = "create view view_" + user5.getAccountnum() + "chat(accountnum, accuontname, friendnum, friendname, content, time) as"
                                    + " select * from chattingrecord where accountnum = ? or friendnum = ?";
                            ps5.close();
                            ps5 = con.prepareStatement(sql5);
                            ps5.setString(1, user5.getAccountnum());
                            ps5.setString(2, user5.getAccountnum());
                            ps5.execute();
                            sql5 = "create view view_" + user5.getAccountnum() + "friend(accountnum, friendnum, buildtime) as"
                                    + " select * from friend where accountnum = ?";
                            ps5.close();
                            ps5 = con.prepareStatement(sql5);
                            ps5.setString(1, user5.getAccountnum());
                            ps5.execute();
                            sender.setResult(0);//注册成功
                        }
                        Tool.sendSender(socket, sender);
                        break;
                    case 6://下线
                        UserInfo user6 = sender.getUserInfo();
                        PreparedStatement ps6;
                        ps6  = con.prepareStatement("update useraccount set isonline = 0 where accountnum = ?");
                        ps6.setString(1, user6.getAccountnum());
                        ps6.executeUpdate();
                        ps6.close();
                        break;
                    case 7://查找好友
                        UserInfo user7 = sender.getUserInfo();
                        PreparedStatement ps7;
                        ResultSet resultSet7 = null;
                        String table = "view_" + user7.getAccountnum() + "friend";
                        String sql7 = "select " + table + ".friendnum, userinfo.name from " + table + ", userinfo where " + table + ".friendnum = userinfo.accountnum " +
                                "and " + table + ".accountnum = ?";
                        ps7 = con.prepareStatement(sql7);
                        ps7.setString(1, user7.getAccountnum());
                        resultSet7 = ps7.executeQuery();
                        sender.setArrayList(Tool.transformFriend(resultSet7));
                        Tool.sendSender(socket, sender);
                        ps7.close();
                        break;
                    case 8://聊天
                        ChattingRecord chattingRecord = sender.getChatting();
                        if(accepterFrame == null)
                        {
                            accepterFrame = new Frame(chattingRecord.getSender().getName(), 10000, chattingRecord.getAccepter(), chattingRecord.getSender());
                            Thread thread = new Thread(accepterFrame);
                            thread.start();
                        }
                        switch (sender.getChattingType())
                        {
                            case 9990://0sender->accepter;1 accepter->sender
                                Socket socket8 = new Socket(InetAddress.getLocalHost(), 10000);
                                Tool.sendSender(socket8, sender);
                                socket8.close();
                                break;
                            case 10000:
                                Socket socket9 = new Socket(InetAddress.getLocalHost(), 9990);
                                Tool.sendSender(socket9, sender);
                                socket9.close();
                                break;
                        }
                        String sql8 = new String("insert into chattingrecord values(?, ?, ?, ?, ?, ?)");
                        PreparedStatement ps8 = con.prepareStatement(sql8);
                        ps8.setString(1, chattingRecord.getSender().getAccountnum());
                        ps8.setString(2, chattingRecord.getSender().getName());
                        ps8.setString(3, chattingRecord.getAccepter().getAccountnum());
                        ps8.setString(4, chattingRecord.getAccepter().getName());
                        ps8.setString(5, chattingRecord.getContent());
                        ps8.setTimestamp(6, new Timestamp(new java.util.Date().getTime()));
                        ps8.executeUpdate();
                        ps8.close();
                        break;
                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new Server()).start();
    }
}
