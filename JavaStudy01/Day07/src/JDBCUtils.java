import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String driver = null;
    private static String url = null;
    private static String password = null;
    private static String user = null;

    static {
        InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            password = properties.getProperty("password");
            user = properties.getProperty("user");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }

    public static void releaseResources(ResultSet result, Statement statement, Connection connc)  {
        try {
            result.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connc.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
