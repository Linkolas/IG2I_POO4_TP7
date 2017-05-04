/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import java.util.Collection;
import tp_poo4_4.metier.Tache;

/**
 *
 * @author Nicolas
 */
public interface TacheDao extends Dao<Tache> {
    public Collection<Tache> findAllNotScheduled();
}
