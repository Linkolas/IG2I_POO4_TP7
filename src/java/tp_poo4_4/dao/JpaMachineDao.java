/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import tp_poo4_4.metier.Machine;

/**
 *
 * @author Nicolas
 */
public class JpaMachineDao extends JpaDao<Machine> implements MachineDao {
    
    private static JpaMachineDao instance = null;
    
    protected static JpaMachineDao getInstance() {
        if (instance == null) {
            instance = new JpaMachineDao();
        }
        
        return instance;
    }
    
    private JpaMachineDao() {
        super(Machine.class);
    }
    
}
