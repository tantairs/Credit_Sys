package credit_sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url;
    private String user;
    private String password;
    private String driver;

    public DBConnection() {
        ConfigUtil config = ConfigUtil.getInstance();
        this.url = config.getProperty("DB_URL");
        this.user = config.getProperty("DB_USER");
        this.password = config.getProperty("DB_PASSWORD");
        this.driver = config.getProperty("DB_DRIVER_CLASS");
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("DBConnection getConnection: load driver error");
        }

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("DBConnection getConnection: connect server error");
        }
        return conn;
    }
}
