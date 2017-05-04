/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

/**
 *
 * @author Nicolas
 */
public abstract class DaoFactory {
    
    public abstract AtelierDao getAtelierDao();
    
    public abstract MachineDao getMachineDao();
    
    public abstract TacheDao getTacheDao();
    
    public enum PersistenceType {
        JPA;
    }
    
    public static DaoFactory getDaoFactory(PersistenceType type) {
        switch(type) {
            case JPA:
            default:
                return new DaoFactoryJpa();
        }
    }
}
