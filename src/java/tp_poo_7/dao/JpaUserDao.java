/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo_7.dao;

import javax.persistence.NoResultException;
import tp_poo4_4.dao.JpaDao;
import tp_poo_7.metier.Users;

/**
 *
 * @author Nicolas
 */
public class JpaUserDao extends JpaDao<Users> implements UserDao {
    
    private static JpaUserDao instance = null;
    
    public static JpaUserDao getInstance() {
        if (instance == null) {
            instance = new JpaUserDao();
        }
        
        return instance;
    }
    
    public JpaUserDao() {
        super(Users.class);
    }

    @Override
    public Users verifDanger(String login, String password) {
        String str = "SELECT u FROM Users u WHERE u.login = '" + login + "' AND u.password = '" + password + "'";
        Users users = null;
        try {
            users = (Users) em.createQuery(str).getSingleResult();
        } catch(NoResultException e) {
            
        }
        return users; 
    }
}
