package repository.imp;

import model.OrderItems;
import java.util.*;
import repository.IOrderItemsDAO;

public class OrderItemsDAO extends MyDAO implements IOrderItemsDAO{

    // lay ra thong tin cua don hàng
    @Override
    public Vector<OrderItems> getOdDetailByOdId(int odId) {
        Vector<OrderItems> vector = new Vector<>();
        xSql = "select oi.*, p.Name, p.Price from OrderItems oi join Products p\n"
                + "on oi.ProductID = p.ID where oi.OrderID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, odId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("ProductID");
                String name = rs.getString("Name");
                int quantity = rs.getInt("Quantity");
                double price = rs.getInt("Price");
                OrderItems detail = new OrderItems(odId, productId, name, quantity, price);
                vector.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vector;
    }

    // thêm 1 orderItems vào order moi
    @Override
    public void insert(int ProductID, int Quantity) {
        try {
            xSql = "INSERT INTO OrderItems (OrderID, ProductID, Quantity)\n"
                    + "SELECT MAX(id), ?, ?\n"
                    + "FROM Orders;";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductID);
            ps.setInt(2, Quantity);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
