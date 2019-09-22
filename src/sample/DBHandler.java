package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class DBHandler extends Configs{

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:sqlserver://DESKTOP-492H46H:1433;database=" + dbName;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection dbConnection = DriverManager.getConnection(url, dbUser, dbPass);

        return dbConnection;
    }

    void checkUser(String userName){
        String select = "SELECT * from Users where UserName = '" + userName + "'";
        try {
            PreparedStatement stmt = getDbConnection().prepareStatement(select);

            System.out.println(stmt.execute());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    void singUp(String userName, String password) {
        String insert = "INSERT INTO " + Consts.USERS_TABLE + "(" + Consts.USERS_UserName + "," + Consts.USERS_PASS + ")VALUES(?, ?)";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);


            if (preparedStatement.executeUpdate() > 0){
                System.out.println("New user added successfully!");
            } else {
                System.out.println("Something went wrong!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
