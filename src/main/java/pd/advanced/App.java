package pd.advanced;

import pd.advanced.databaseconnection.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class App
{
    public static void main( String[] args ) throws IOException, SQLException {

        Properties properties = new Properties();
        properties.load(App.class.getClassLoader().getResourceAsStream("ds-connection.properties"));

        String pwd = properties.getProperty("db_password");
        Connection con = DatabaseConnection.getConnection(
                properties.getProperty("db_driver"),
                properties.getProperty("db_url"),
                properties.getProperty("db_user"),
                properties.getProperty("db_password")
        );

        Statement statement = null;
        ResultSet resultSet = null;

        statement = con.createStatement();
        resultSet = statement.executeQuery("select * from employeesalary");

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+
                    " "+resultSet.getString(2)+
                    " "+resultSet.getInt(3));
        }
        if(statement != null)
            statement.close();
        if (con != null)
            con.close();

    }
}
