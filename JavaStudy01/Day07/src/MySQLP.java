import java.net.ConnectException;
import java.net.URL;
import java.sql.*;

public class MySQLP {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
            Connection connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            String sql1 = "update student set name = '赵中' where id = 4";
            statement.executeUpdate(sql1);
            String sql2 = "update student set name = '黄彻' where id = 1";
            statement.executeUpdate(sql2);
            String sql3 = "select * FROM student";
            ResultSet resultSet = statement.executeQuery(sql3);
            connection.commit();

            while (resultSet.next())
            {
                System.out.println("id=" + resultSet.getInt(1) + " name=" + resultSet.getString(2));
            }
            JDBCUtils.releaseResources(resultSet, statement, connection);
        //6、释放连接

    }
}
