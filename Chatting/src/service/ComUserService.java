package service;

import UI.Frame;
import entity.Sendor;
import entity.User;
import entity.UserInfo;
import intnet.Tool;

import javax.naming.ldap.SortKey;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComUserService extends Service{
    public ComUserService(UserInfo user) {
        super(user);
    }
    /*
    添加好友
     */
    public Sendor addFriend(String friendNum)
    {
        Sendor sendor = null;
        if(friendNum.equals(user.getAccountnum())) return sendor;
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            sendor = new Sendor();
            sendor.setFriendNum(friendNum);
            sendor.setType(1);
            sendor.setUserInfo(user);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sendor;
    }
    /*
    查找好友信息
     */
    public ArrayList selectFriends()
    {
        Socket socket = null;
        ArrayList rs = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor = new Sendor();
            sendor.setType(7);
            sendor.setUserInfo(user);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            rs = sendor.getArrayList();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /*
    查找聊天记录
     */
    public ArrayList selectChattingRecords()
    {
        String keyword= JOptionPane.showInputDialog(null,"请输入查找的关键字：","查询聊天记录",JOptionPane.WARNING_MESSAGE);		//输入对话框
        Socket socket = null;
        ArrayList rs = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor = new Sendor();
            sendor.setType(2);
            sendor.setKeyword(keyword);
            sendor.setUserInfo(user);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            rs = sendor.getArrayList();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /*
    删除聊天记录
     */
    public int deleteRecords()
    {
        String keyword= JOptionPane.showInputDialog(null,"请输入删除的关键字：","删除聊天记录",JOptionPane.WARNING_MESSAGE);		//输入对话框
        Socket socket = null;
        int result = -1;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor = new Sendor();
            sendor.setType(3);
            sendor.setKeyword(keyword);
            sendor.setUserInfo(user);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            result = sendor.getResult();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
/*
备份聊天记录
 */
    public void backup()
    {
        String filename = JOptionPane.showInputDialog(null,"请输入文件名称：","备份聊天记录",JOptionPane.WARNING_MESSAGE);
        Socket socket = null;
        int result = -1;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            Sendor sendor = new Sendor();
            sendor.setType(4);
            sendor.setKeyword(filename);
            sendor.setUserInfo(user);
            Tool.sendSender(socket, sendor);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
            JOptionPane.showMessageDialog(null, "备份成功", "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    public void  chatting(User sender, User accepter)
    {
        try {
            Frame senderFrame = new Frame(accepter.getName(), 9990, sender, accepter);
            Thread thread = new Thread(senderFrame);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(new ComUserService(new UserInfo("1056530521", "123")).deleteRecords());
    }
}
