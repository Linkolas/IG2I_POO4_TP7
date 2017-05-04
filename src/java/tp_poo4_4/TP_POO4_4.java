/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import tp_poo4_4.dao.AtelierDao;
import tp_poo4_4.dao.DaoFactory;
import tp_poo4_4.dao.MachineDao;
import tp_poo4_4.dao.TacheDao;
import tp_poo4_4.metier.Atelier;
import tp_poo4_4.metier.Machine;
import tp_poo4_4.metier.Ordonnanceur;
import tp_poo4_4.metier.Tache;

/**
 *
 * @author Nicolas
 */
public class TP_POO4_4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*AtelierDao atelierManager = 
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
        
        Atelier a1 = new Atelier();
        Machine m11 = new Machine(a1);
        Machine m12 = new Machine(a1);
        
        a1.addMachine(m11);
        a1.addMachine(m12);
        
        long timeMillis = System.currentTimeMillis();
        Tache t1 = new Tache(30, new Date(timeMillis + 60000*60), 2.0);
        Tache t2 = new Tache(30, new Date(timeMillis + 60000*60), 5.0);
        Tache t3 = new Tache(30, new Date(timeMillis + 60000*120), 5.0);
        Tache t4 = new Tache(35, new Date(timeMillis + 60000*120), 5.0);
        Tache t5 = new Tache(30, new Date(timeMillis + 60000*60), 3.0);
        
        atelierManager.create(a1);
        machineManager.create(m11);
        machineManager.create(m12);
        tacheManager.create(t1);
        tacheManager.create(t2);
        tacheManager.create(t3);
        tacheManager.create(t4);
        tacheManager.create(t5);
        
        Ordonnanceur.ordonnancer();*/
        Ordonnanceur.JeuDeTest();
    }
    
}
