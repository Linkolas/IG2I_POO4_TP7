/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo_7.metier;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Users.findAll", query = "SELECT a FROM Users a")
    , @NamedQuery(name = "Users.findById", query = "SELECT a FROM Users a WHERE a.id = :id")})
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "LOGIN")
    private String login;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "USER_ROLE")
    private String user_role;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    static public enum Role {
        USER("USER"),
        ADMIN("ADMIN");
        
        private String name = "";
        
        Role(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    
}
