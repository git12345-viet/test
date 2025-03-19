/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.OrderItems;

/**
 *
 * @author havie
 */
public interface IOrderItemsDAO {
    List<OrderItems> getOdDetailByOdId(int odid);
    void insert(int ProductID, int Quantity);
}
