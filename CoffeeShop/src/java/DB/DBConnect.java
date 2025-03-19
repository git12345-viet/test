/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

/**
 *
 * @author DELL
 */
import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {

    public Connection conn = null;

    public DBConnect(String URL, String userName, String password) {
        try {
            //get Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // com.microsoft.sqlserver.jdbc.SQLServerDriver
            conn = DriverManager.getConnection(URL, userName, password);
            System.out.println("connected");
//connection
            //conn=DriverManager
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DBConnect() {
        this("jdbc:sqlserver://localhost:1433;databaseName=CoffeeShop",
                "sa", "sa");
    }

    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }

    public static void main(String[] args) {
        new DBConnect();
    }
}
