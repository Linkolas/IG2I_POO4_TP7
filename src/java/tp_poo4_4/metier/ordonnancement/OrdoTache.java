/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier.ordonnancement;

import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Nicolas
 */
public class OrdoTache {
    private int id = -1;
    private int temps = 0;
    private double penalite = 0.0;
    private Date dateLimite = Date.from(Instant.now());

    public OrdoTache(int id, int temps, Date dateLimite, double penalite) {
        this.id = id;
        this.temps = temps;
        this.dateLimite = dateLimite;
        this.penalite = penalite;
    }

    public int getId() {
        return id;
    }

    public int getTemps() {
        return temps;
    }

    public double getPenalite() {
        return penalite;
    }

    public Date getDateLimite() {
        return dateLimite;
    }

    @Override
    public String toString() {
        return "T" + id + " - lim " + dateLimite + " - temps " + temps + " - pén " + penalite;
    }
}
