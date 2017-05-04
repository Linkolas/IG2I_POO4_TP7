/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import tp_poo4_4.dao.DaoFactory;
import tp_poo4_4.metier.ordonnancement.OrdoAtelier;

/**
 *
 * @author Nicolas
 */
public class Ordonnanceur {
    
    public static boolean ordonnancer() {
        
        Collection<Tache> taches =
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getTacheDao()
                    .findAllNotScheduled();
        
        if(taches.isEmpty()) {
            return true;
        }
        
        Collection<Atelier> ateliers = 
                DaoFactory
                    .getDaoFactory(DaoFactory.PersistenceType.JPA)
                    .getAtelierDao()
                    .findAll();
        
        if(ateliers.isEmpty()) {
            return false;
        }
        
        // choisir un atelier dont la dispo est minimale
        // ordonnancer les tâches :
        //      temps max minimal
        //      pénalités max
        
        Atelier atelierFirst = null;
        for(Atelier a: ateliers) {
            if(a.getMachineCollection().isEmpty()) {
                continue;
            }
            
            if(atelierFirst == null) {
                atelierFirst = a;
                continue;
            }
            
            if(atelierFirst.getDatedispo().after(a.getDatedispo())) {
                atelierFirst = a;
            }
        }
        
        if(atelierFirst == null) {
            return false;
        }
        
        List<Tache> listeTaches = new ArrayList<>(taches);
        Collections.sort(listeTaches, new Comparator<Tache>() {
            @Override
            public int compare(Tache o1, Tache o2) {
                
                int retour = o2.getTempsprod() - o1.getTempsprod();
                
                if(retour != 0) {
                    return retour;
                }
                
                retour = o1.getDatelimite().compareTo(o2.getDatelimite());
                
                if(retour != 0) {
                    return retour;
                }
                
                double d = o2.getPenaliteretard() - o1.getPenaliteretard();
                if(d < 0) {
                    return -1;
                } else if (d > 0) {
                    return 1;
                }
                
                return retour;
            }
        });
        
        for(Tache t: listeTaches) {
            System.out.println("Duree " + t.getTempsprod() + " + Limite " + t.getDatelimite() + " + Penalite " + t.getPenaliteretard());
            
            // On constate qu'on ordonnance ainsi :
            //  - Durée de la plus grande à petite
            //  - Date limite de la plus petite à plus grande
            //  - Pénalité de la plus élevée à plus faible
        }
        
        // Grâce à ce tri, on peut assigner les tâches aux machines
        // en assignant la tâche suivante à la machine pour
        // laquelle la date de dispo est la plus faible et obtenir
        // un temps de mobilisation de l'atelier le plus court.
        // Le tri réalisé permet de prioriser les tâches à forte
        // pénalité quand plusieurs tâches ont la même durée.
        
        int count = 0;
        List<Machine> machines = new ArrayList<>(atelierFirst.getMachineCollection());
        for(Tache t: listeTaches) {
            
            if(count >= machines.size()) {
                count = 0;
            }
            
            machines.get(count).addTache(t);
            
            count++;
        }
        
        
        // Améliorations à apporter : 
        // 1 - Trier les tâches assignées à une machine pour réaliser
        //  en priorité les tâches qui pourraient finir trop tard.
        // 2 - Interchanger les tâches assingnées entre les amchines
        //  si cela permet d'éviter une pénalité sans augmenter le
        //  temps d'indisponibilité de l'atelier.
        
        
        
        return true;
    }
    
    public static void JeuDeTest() {
        OrdoAtelier oa = new OrdoAtelier();
        oa.JeuDeTest();
    }
    
    public static Date DateAdd(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        cal.add(Calendar.MINUTE, minutes);
        
        return cal.getTime();
    }
}
