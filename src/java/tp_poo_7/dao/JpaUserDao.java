/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo_7.dao;

import tp_poo4_4.dao.JpaDao;
import tp_poo_7.metier.User;

/**
 *
 * @author Nicolas
 */
public class JpaUserDao extends JpaDao<User> implements UserDao {
    
    private static JpaUserDao instance = null;
    
    public static JpaUserDao getInstance() {
        if (instance == null) {
            instance = new JpaUserDao();
        }
        
        return instance;
    }
    
    public JpaUserDao() {
        super(User.class);
    }

    @Override
    public User verifDanger(String login, String password) {
        String str = "SELECT u FROM \"User\" u WHERE u.login = ’" + login + "’ AND u.password = ’" + password + "’";
        User users = (User) em.createQuery(str).getSingleResult();
        return users; 
    }
}
