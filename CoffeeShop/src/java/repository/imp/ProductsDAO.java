package repository.imp;

import model.Products;
import java.util.*;
import java.sql.PreparedStatement;
import repository.IProductDAO;

public class ProductsDAO extends MyDAO implements IProductDAO{

    //lay ra danh sách tat ca san pham
    @Override
    public Vector<Products> getAllProducts() {
        Vector<Products> vectorP = new Vector<>();
        xSql = "SELECT * FROM Products";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");
                int categoryID = rs.getInt("CategoryID");
                Products p = new Products(id, name, description, price, image, categoryID);
                vectorP.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vectorP;
    }

    // lay ra san pham theo category
    @Override
    public Vector<Products> getProductsByCategoryID(int categoryID) {
        Vector<Products> vectorP = new Vector<>();
        xSql = "SELECT * FROM Products WHERE CategoryID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, categoryID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");
                Products product = new Products(id, name, description, price, image, categoryID);
                vectorP.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vectorP;
    }

    // lay san pham bang id
    @Override
    public Products getProductByID(int productID) {
        xSql = "SELECT * FROM Products WHERE ID = ?";
        try {
            Products product;
            ps = con.prepareStatement(xSql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                double price = rs.getDouble("Price");
                String image = rs.getString("Image");
                int categoryID = rs.getInt("CategoryID");
                product = new Products(id, name, description, price, image, categoryID);
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // hàm search san pham
    @Override
    public Vector<Products> searchProductByName(String text) {
        Vector<Products> vectorP = new Vector<>();
        xSql = "SELECT * FROM Products WHERE Name LIKE ?";
        try {
            ;
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + text + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                int price = rs.getInt("Price");
                String image = rs.getString("Image");
                int categoryID = rs.getInt("CategoryID");
                Products product = new Products(id, name, description, price, image, categoryID);
                vectorP.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vectorP;
    }

    // hàm thêm moi 1 san pham
    @Override
    public void addProduct(Products product) {
        xSql = "INSERT INTO Products (Name, Description, Price, Image, CategoryID) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getImage());
            ps.setInt(5, product.getCategoryID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm sua san pham
    @Override
    public void editProduct(int productID, Products updatedProduct) {
        xSql = "UPDATE Products SET Name = ?, Description = ?, Price = ?, Image = ?, CategoryID = ? WHERE ID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, updatedProduct.getName());
            ps.setString(2, updatedProduct.getDescription());
            ps.setDouble(3, updatedProduct.getPrice());
            ps.setString(4, updatedProduct.getImage());
            ps.setInt(5, updatedProduct.getCategoryID());
            ps.setInt(6, productID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // hàm xóa san pham
    @Override
    public void deleteProduct(int productID) {
        try {
            // Cập nhật các mặt hàng liên quan trong bảng OrderItems, đặt ProductID thành null
            String updateOrderItemsSql = "UPDATE OrderItems SET ProductID = null WHERE ProductID = ?";
            PreparedStatement updateOrderItemsPs = con.prepareStatement(updateOrderItemsSql);
            updateOrderItemsPs.setInt(1, productID);
            updateOrderItemsPs.executeUpdate();

            // Xóa sản phẩm từ bảng Products
            String deleteProductSql = "DELETE FROM Products WHERE ID = ?";
            PreparedStatement deleteProductPs = con.prepareStatement(deleteProductSql);
            deleteProductPs.setInt(1, productID);
            deleteProductPs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
