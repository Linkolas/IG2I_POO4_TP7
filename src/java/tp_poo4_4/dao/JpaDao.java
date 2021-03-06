/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Nicolas
 */
public abstract class JpaDao<T> implements Dao<T> {

    EntityManagerFactory emf = null;
    public EntityManager em = null;
    EntityTransaction et = null;
    
    Class<T> classe;
    
    public JpaDao(Class<T> c) {
        classe = c;
        
        emf = Persistence.createEntityManagerFactory("TP_POO4_4PU");
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
    
    @Override
    public boolean create(T obj) {
        boolean retour = true;
        
        try {
            et.begin();
            em.persist(obj);
            et.commit();
            
        } catch (Exception ex) {
            System.out.println("Error when persisting data.");
            et.rollback();
            retour = false;
        }
        
        return retour;
    }

    @Override
    public T find(Integer id) {
        return em.find(classe, id);
    }

    @Override
    public Collection<T> findAll() {
        Query createQuery = em.createQuery("select t from " + classe.getSimpleName() + " t");
        return createQuery.getResultList();
    }

    @Override
    public boolean update(T obj) {
        boolean retour = true;
        
        try {
            et.begin();
            em.merge(obj);
            et.commit();
            
        } catch (Exception ex) {
            retour = false;
        }
        
        return retour;
    }

    @Override
    public boolean delete(T obj) {
        boolean retour = true;
        
        try {
            et.begin();
            em.remove(obj);
            et.commit();
            
        } catch (Exception ex) {
            retour = false;
        }
        
        return retour;
    }

    @Override
    public boolean deleteAll() {
        boolean retour = true;
        
        try {
            et.begin();
            Query createQuery = em.createQuery("delete from " + classe.getSimpleName()+"");
            createQuery.executeUpdate();
            et.commit();
            
        } catch (Exception ex) {
            retour = false;
        }
        
        return retour;
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
    
}
