/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Products;

/**
 *
 * @author havie
 */
public interface IProductDAO {
    List<Products> getAllProducts();
    List<Products> getProductsByCategoryID(int categoryID);
    Products getProductByID(int productID);
    List<Products> searchProductByName(String text);
    void addProduct(Products product);
    void editProduct(int productID, Products updatedProduct);
    void deleteProduct(int productID);
}
