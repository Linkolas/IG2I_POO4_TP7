/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import javax.persistence.Query;
import tp_poo4_4.metier.Atelier;

/**
 *
 * @author Nicolas
 */
public class JpaAtelierDao extends JpaDao<Atelier> implements AtelierDao {

    private static JpaAtelierDao instance = null;
    
    protected static JpaAtelierDao getInstance() {
        if (instance == null) {
            instance = new JpaAtelierDao();
        }
        
        return instance;
    }
    
    private JpaAtelierDao() {
        super(Atelier.class);
    }

    @Override
    public Atelier findFirstAvailable() {
        Query query = em.createQuery("select t from Atelier t order by t.datedispo ASC");
        query.setMaxResults(1);
        return (Atelier) query.getSingleResult();
    }
    
}
