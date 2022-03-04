package db;

import java.sql.*;

public class ConnectDB {
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://10.139.107.212:3306/chat?useUnicode=true&characterEncoding=utf8&useSSL=true";
    private String user;
    private String pwd;
    private Connection con;
    public ConnectDB(String user, String pwd)
    {
        this.user = user;
        this.pwd = pwd;
        con = getConection();
    }

    public ConnectDB()
    {
        user = "server";
        pwd = "123456";
    }

    public Connection getConection()
    {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("第三方库未加载");
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            System.out.println("连接数据库失败");
        }
        return connection;
    }

    public void close()
    {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("连接关闭失败");
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectDB().getConection();
        Statement statement = con.createStatement();
        String sql4 = "SELECT * FROM view_3333chat INTO OUTFILE 'test.txt' " +
                "FIELDS TERMINATED BY '\t' " +
                "LINES TERMINATED BY '\r\n'";
        statement.execute(sql4);
        statement.close();
        con.close();
    }

}
