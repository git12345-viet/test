/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Categories;

/**
 *
 * @author havie
 */
public interface ICategoriesDAO {
    List<Categories> getCategories();
    Categories getCategoryByProductId(int productId);
}
