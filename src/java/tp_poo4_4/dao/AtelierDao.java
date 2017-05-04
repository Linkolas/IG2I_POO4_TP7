/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.dao;

import tp_poo4_4.metier.Atelier;

/**
 *
 * @author Nicolas
 */
public interface AtelierDao extends Dao<Atelier> {
    public Atelier findFirstAvailable();
}
