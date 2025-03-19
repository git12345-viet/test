package repository.imp;

import model.Users;
import java.util.*;
import repository.IUserDAO;

public class UsersDAO extends MyDAO implements IUserDAO{
    
    public static void main(String[] args) {
        System.out.println(new UsersDAO().Login("messi", "12345"));
    }

    // hàm dang nhâp
    @Override
    public Users Login(String username, String pass) {
        xSql = "select * from Users where username = ? and password = ?";
        Users x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("ID");
                String xUsername = rs.getString("username");
                String xPass = rs.getString("password");
                int xRole = rs.getInt("role_id");
                boolean active = rs.getBoolean("active");

                x = new Users(xId, xUsername, xPass, xRole, active);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    // hàm tra vê danh sách tat ca nguoi dung
    @Override
    public Vector<Users> getAllUser() {
        Vector<Users> vector = new Vector<>();
        xSql = "select * from users";

        Users x = null;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xId = rs.getInt("ID");
                String xUsername = rs.getString("username");
                String xPass = rs.getString("password");
                int xRole = rs.getInt("role_id");
                boolean active = rs.getBoolean("active");

                x = new Users(xId, xUsername, xPass, xRole, active);
                vector.add(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }

    @Override
    public void updateStatus(int userId) {
        xSql = "UPDATE Users SET active = CASE WHEN active = 1 THEN 0 ELSE 1 END WHERE ID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm dang kí
    @Override
    public void Register(String username,  String pass) {
        xSql = "INSERT INTO Users (username, Password, role_id, active) VALUES ( ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setInt(3, 2);
            ps.setInt(4, 1);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
