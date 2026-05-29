import java.sql.Connection;
import java.sql.DriverManager;

class DBConnection {

    static Connection getConnection() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentdb",
                    "root",
                    "admin"
            );
        } catch (Exception e) {

            System.out.println(e);
        }

        return con;
    }
}