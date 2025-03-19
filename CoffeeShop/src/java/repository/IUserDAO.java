/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Users;

/**
 *
 * @author havie
 */
public interface IUserDAO {
    Users Login(String username, String pass);
    List<Users> getAllUser();
    void updateStatus(int userId);
    void Register(String username,  String pass);
}
