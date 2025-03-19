/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Orders;

/**
 *
 * @author havie
 */
public interface IOdersDAO {
    List<Orders> getOrderByUserID(int userId);
    Orders getOrderByOdId(int odid);
}
