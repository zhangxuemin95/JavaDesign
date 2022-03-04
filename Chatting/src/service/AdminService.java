package service;

import db.ConnectDB;
import entity.UserInfo;

import javax.swing.*;
import java.sql.*;

public class AdminService extends Service{
    public AdminService(UserInfo user) {
        super(user);
        initConnectDB();
        setting();
    }

    public void setting()
    {
        String sql = "set @@autocommit = 0";
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteUser()
    {
        String id = JOptionPane.showInputDialog(null, "请输入要删除的账号", "删除用户", JOptionPane.INFORMATION_MESSAGE);
        try {
            String sql = "select accountnum from useraccount where type = 0 and accountnum = " + id;
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next())
            {
                rs.close();
                statement.close();
                CallableStatement cs = con.prepareCall("{ call delete_user(?) }");
                cs.setString(1, id);
                cs.execute();
                cs.close();
                JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                con.commit();
                rs.close();
                statement.close();
                JOptionPane.showMessageDialog(null, "该用户不存在", "提示", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initConnectDB()
    {
        connectDB = new ConnectDB(user.getAccountnum(), user.getPwd());
        con = connectDB.getConection();
    }

    public int alterLevel(String isolationlevel)
    {
        String sql = "set session transaction isolation level " + isolationlevel;
        String sql2 = "select @@transaction_isolation";
        try {
            Statement statement = con.createStatement();
            statement.execute(sql);
            ResultSet rs = statement.executeQuery(sql2);
            rs.next();
            String isolationnow = rs.getString(1);
            JOptionPane.showMessageDialog(null, "现在的隔离等级为: " + isolationnow, "提示", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void restore(String filePath, String tableName)
    {
        String sql = "LOAD DATA INFILE '" + filePath + "' IGNORE INTO TABLE " + tableName +
                " FIELDS TERMINATED BY \'\\t\' " +
                "LINES TERMINATED BY \'\\r\\n\'";
        Statement statement = null;
        try {
            statement = con.createStatement();
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
