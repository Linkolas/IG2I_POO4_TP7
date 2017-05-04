/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_tp4.tests;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tp_poo4_4.dao.AtelierDao;
import tp_poo4_4.dao.DaoFactory;
import tp_poo4_4.dao.DaoFactoryJpa;
import tp_poo4_4.dao.JpaAtelierDao;
import tp_poo4_4.dao.JpaMachineDao;
import tp_poo4_4.dao.JpaTacheDao;
import tp_poo4_4.dao.MachineDao;
import tp_poo4_4.dao.TacheDao;
import tp_poo4_4.metier.Atelier;
import tp_poo4_4.metier.Machine;
import tp_poo4_4.metier.Tache;

/**
 *
 * @author Nicolas
 */
public class Tests {
    
    EntityManagerFactory emf = null;
    EntityManager em = null;
    EntityTransaction et = null;
    
    public Tests() {
        emf = Persistence.createEntityManagerFactory("TP_POO4_4PU");
        em = emf.createEntityManager();
        et = em.getTransaction();
        
        et.begin();
        Atelier a1 = new Atelier();
        em.persist(a1);
        et.commit();
        
        et.begin();
        Atelier a2 = new Atelier();
        em.persist(a2);
        et.commit();
        
        et.begin();
        Machine m1 = new Machine(a1);
        em.persist(m1);
        et.commit();
        
        et.begin();
        Machine m2 = new Machine(a2);
        em.persist(m2);
        et.commit();
        
        em.close();
        emf.close();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TP_POO4_4PU");
        em = emf.createEntityManager();
        et = em.getTransaction();
        
        et.begin();
    }
    
    @After
    public void tearDown() {
        et.commit();
        
        em.close();
        emf.close();
    }
    
    //@Test
    public void question4() {
        Atelier a1 = em.find(Atelier.class, 1);
        Atelier a2 = em.find(Atelier.class, 2);
        
        Machine m1 = em.find(Machine.class, 1);
        Machine m2 = em.find(Machine.class, 2);
        
        assertTrue(a1 != null);
        assertTrue(a1.getMachineCollection() != null);
        assertTrue(a1.getMachineCollection().size() == 1);
        
        assertTrue(a2 != null);
        assertTrue(a2.getMachineCollection() != null);
        assertTrue(a2.getMachineCollection().size() == 1);
        
        assertTrue(m1 != null);
        assertTrue(m2 != null);
        
        a1.addMachine(m2);
        
        assertTrue(a1.getMachineCollection().size() == 2);
        assertTrue(a2.getMachineCollection().isEmpty());
    }
    
    //@Test
    public void question6() {
        Atelier a = new Atelier();
        Machine m1 = new Machine();
        Machine m2 = new Machine();
        
        System.out.println("a datedispo " + a.getDatedispo());
        
        long timeMillis = System.currentTimeMillis();
        Tache t1 = new Tache(45, new Date(timeMillis+60000*120), 5.0);
        Tache t2 = new Tache(120, new Date(timeMillis+60000*150), 10.0);
        Tache t3 = new Tache(70, new Date(timeMillis+60000*180), 4.0);
        Tache t4 = new Tache(60, new Date(timeMillis+60000*300), 12.0);
        
        a.addMachine(m1);
        a.addMachine(m2);
        m1.addTache(t1);
        m1.addTache(t2);
        m2.addTache(t3);
        m2.addTache(t4);
        
        
        System.out.println("a datedispo " + a.getDatedispo());
        System.out.println("m1 datedispo " + m1.getDatedispo());
        System.out.println("m2 datedispo " + m2.getDatedispo());
        System.out.println("date " + new Date(timeMillis + 60000 * (45 + 120)));
        
        assertTrue(a.getMachineCollection().size() == 2);
        assertTrue(a.getMachineCollection().contains(m1));
        assertTrue(a.getMachineCollection().contains(m2));
        assertTrue(m1.getNatelier() == a);
        assertTrue(m2.getNatelier() == a);
        
        assertTrue(m1.getTacheCollection().size() == 2);
        assertTrue(m1.getTacheCollection().contains(t1));
        assertTrue(m1.getTacheCollection().contains(t2));
        assertTrue(t1.getNmachine() == m1);
        assertTrue(t2.getNmachine() == m1);
        
        assertTrue(a.getDatedispo() == m1.getDatedispo());
        assertTrue(a.getDatedispo().toString().equals(new Date(timeMillis + 60000 * (45 + 120)).toString()));
    }
    
    @Test
    public void question13() {
        Atelier a = new Atelier();
        Machine m1 = new Machine();
        Machine m2 = new Machine();
        
        long timeMillis = System.currentTimeMillis();
        Tache t1 = new Tache(45, new Date(timeMillis+60000*120), 5.0);
        Tache t2 = new Tache(120, new Date(timeMillis+60000*150), 10.0);
        Tache t3 = new Tache(70, new Date(timeMillis+60000*180), 4.0);
        Tache t4 = new Tache(60, new Date(timeMillis+60000*300), 12.0);
        
        AtelierDao atelierManager = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getAtelierDao();
        MachineDao machineManager = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getMachineDao();
        TacheDao tacheManager = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getTacheDao();
        
        tacheManager.deleteAll();
        machineManager.deleteAll();
        atelierManager.deleteAll();
        
        a.addMachine(m1);
        a.addMachine(m2);
        
        atelierManager.create(a);
        tacheManager.create(t1);
        tacheManager.create(t2);
        tacheManager.create(t3);
        tacheManager.create(t4);
        
        // doit afficher les 4 taches
        for(Tache t : tacheManager.findAllNotScheduled()) {
            System.out.println(t);
        }
        
        m1.addTache(t1);
        m1.addTache(t2);
        
        m2.addTache(t3);
        m2.addTache(t4);
        
        tacheManager.update(t1);
        tacheManager.update(t2);
        tacheManager.update(t3);
        tacheManager.update(t4);
        
        // On constate que les tâches sont à jour mais les temps des machines non.
        // Il faut penser à mettre à jour les machines après ça.
        
        
        // --- QUESTION 14
        // Dans mon cas cela provoque une erreur...
        
        TacheDao tacheManager2 = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getTacheDao();
        
        tacheManager2.create(t1);
        tacheManager.create(t1);
        System.out.println(tacheManager.findAll().size());
    }
}
