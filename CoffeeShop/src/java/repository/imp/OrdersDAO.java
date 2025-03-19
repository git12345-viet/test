package repository.imp;

import model.Orders;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;
import repository.IOdersDAO;

public class OrdersDAO extends MyDAO implements IOdersDAO{

    public static void main(String[] args) {
        OrdersDAO od = new OrdersDAO();
        System.out.println(od.getAllOrders());
    }

    // lay ra nhung don hang cua 1 khach hang
    @Override
    public Vector<Orders> getOrderByUserID(int userID) {
        Vector<Orders> orderList = new Vector<>();
        xSql = "SELECT o.*, u.username, os.status_name FROM Orders o\n"
                + "                join Users u on o.UserID = u.ID\n"
                + "                join OrderStatus os on o.StatusID = os.orderstatus_id \n"
                + "                WHERE UserID = ?;";
        try {
            int id, totalAmount, StatusID;
            String username, statusName, name, phoneNumber, address;
            LocalDate orderDate;
            Orders order;
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                name = rs.getString("Name");
                phoneNumber = rs.getString("PhoneNumber");
                address = rs.getString("Address");
                orderDate = rs.getDate("OrderDate").toLocalDate();
                totalAmount = rs.getInt("TotalAmount");
                StatusID = rs.getInt("StatusID");
                username = rs.getString("username");
                statusName = rs.getString("status_name");
                order = new Orders(id, userID, name, phoneNumber, address, orderDate, totalAmount, StatusID, username, statusName);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public Orders getOrderByOdId(int odid) {
        Orders order = null;

        try {
            String sql = "SELECT o.*, u.username, os.status_name FROM Orders o\n"
                    + "join Users u on o.UserID = u.ID\n"
                    + "join OrderStatus os on o.StatusID = os.orderstatus_id \n"
                    + "WHERE o.ID = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, odid);
            rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("ID");
                int userID = rs.getInt("UserID");
                String name = rs.getString("Name");
                String phoneNumber = rs.getString("PhoneNumber");
                String address = rs.getString("Address");
                LocalDate orderDate = rs.getDate("OrderDate").toLocalDate();
                int totalAmount = rs.getInt("TotalAmount");
                int statusID = rs.getInt("StatusID");
                String username = rs.getString("username");
                String statusName = rs.getString("status_name");

                order = new Orders(id, userID, name, phoneNumber, address, orderDate, totalAmount, statusID, username, statusName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return order;
    }

    // lay ra tat ca nhung don hang
    public Vector<Orders> getAllOrders() {
        Vector<Orders> orderList = new Vector<>();
        xSql = "SELECT o.*, u.username, os.status_name FROM Orders o\n"
                + "join Users u on o.UserID = u.ID\n"
                + "join OrderStatus os on o.StatusID = os.orderstatus_id ";

        try {
            int userID, id, totalAmount, StatusID;
            String username, statusName, name, phoneNumber, address;
            LocalDate orderDate;
            Orders order;
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                userID = rs.getInt("UserID");
                name = rs.getString("Name");
                phoneNumber = rs.getString("phonenumber");
                address = rs.getString("Address");
                orderDate = rs.getDate("OrderDate").toLocalDate();
                totalAmount = rs.getInt("TotalAmount");
                StatusID = rs.getInt("StatusID");
                username = rs.getString("username");
                statusName = rs.getString("status_name");
                order = new Orders(id, userID, name, phoneNumber, address, orderDate, totalAmount, StatusID, username, statusName);
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // tao 1 don hàng
    public void createOrder(Orders order) {
        try {
            xSql = "INSERT INTO Orders (UserID, Name, phonenumber, Address, OrderDate, TotalAmount, StatusID) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?);";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, order.getUserID());
            ps.setString(2, order.getName());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getAddress());
            ps.setDate(5, Date.valueOf(order.getOrderDate()));
            ps.setDouble(6, order.getTotalAmount());
            ps.setInt(7, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nextStatus(int orderId, int currentStatus) {
        try {
            if (currentStatus < 3) {
                int newStatus = currentStatus + 1;

                // Cập nhật `StatusID`
                String updateStatusSql = "UPDATE Orders SET StatusID = ? WHERE ID = ?";
                ps = con.prepareStatement(updateStatusSql);
                ps.setInt(1, newStatus);
                ps.setInt(2, orderId);
                ps.executeUpdate();
            } else {
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
