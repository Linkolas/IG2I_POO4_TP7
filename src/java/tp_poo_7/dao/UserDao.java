/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo_7.dao;

import java.util.Collection;
import tp_poo4_4.dao.Dao;
import tp_poo_7.metier.Users;

/**
 *
 * @author Nicolas
 */
public interface UserDao extends Dao<Users> {
    
    public Users verifDanger(String login, String password);
}
