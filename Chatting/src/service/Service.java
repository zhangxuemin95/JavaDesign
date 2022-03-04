package service;

import db.ConnectDB;
import entity.Sendor;
import entity.UserInfo;
import intnet.Server;
import intnet.Tool;

import javax.swing.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public abstract class Service {

    protected UserInfo user = null;
    protected ConnectDB connectDB = null;
    protected Connection con = null;
    public Service(UserInfo user)
    {
        this.user = user;
    }
    public int verifyAccount() {
        Sendor sendor = new Sendor();
        sendor.setType(0);
        sendor.setUserInfo(user);
        int result = -1;
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
            Tool.sendSender(socket, sendor);
            sendor = Tool.acceptSender(socket);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = sendor.getResult();
        return result;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public static void main(String[] args) {
        UserInfo user = new UserInfo("1056530521", "1253");

    }

}
