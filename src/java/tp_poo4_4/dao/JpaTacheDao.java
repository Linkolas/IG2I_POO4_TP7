/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import java.util.Collection;
import javax.persistence.Query;
import tp_poo4_4.metier.Tache;

/**
 *
 * @author Nicolas
 */
public class JpaTacheDao extends JpaDao<Tache> implements TacheDao {

    private static JpaTacheDao instance = null;
    
    protected static JpaTacheDao getInstance() {
        if (instance == null) {
            instance = new JpaTacheDao();
        }
        
        return instance;
    }
    
    private JpaTacheDao() {
        super(Tache.class);
    }

    @Override
    public Collection<Tache> findAllNotScheduled() {
        Query query = em.createQuery("select t from Tache t where t.nmachine is null");
        return query.getResultList();
    }
    
}
