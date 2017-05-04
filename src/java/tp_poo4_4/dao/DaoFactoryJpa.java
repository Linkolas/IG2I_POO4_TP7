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
public class DaoFactoryJpa extends DaoFactory {
    @Override
    public AtelierDao getAtelierDao() {
        return JpaAtelierDao.getInstance();
    }
    
    @Override
    public MachineDao getMachineDao() {
        return JpaMachineDao.getInstance();
    }
    
    @Override
    public TacheDao getTacheDao() {
        return JpaTacheDao.getInstance();
    }
}
