/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "MACHINE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Machine.findAll", query = "SELECT m FROM Machine m")
    , @NamedQuery(name = "Machine.findById", query = "SELECT m FROM Machine m WHERE m.id = :id")
    , @NamedQuery(name = "Machine.findByDatedispo", query = "SELECT m FROM Machine m WHERE m.datedispo = :datedispo")
    , @NamedQuery(name = "Machine.findByPenalitetotale", query = "SELECT m FROM Machine m WHERE m.penalitetotale = :penalitetotale")})
public class Machine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DATEDISPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedispo = Date.from(Instant.now());
    @Basic(optional = false)
    @Column(name = "PENALITETOTALE")
    private double penalitetotale = 0;
    @JoinColumn(name = "NATELIER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Atelier natelier;
    @OneToMany(mappedBy = "nmachine")
    private Collection<Tache> tacheCollection = new ArrayList<>();

    public Machine() {
    }

    public Machine(Atelier atelier) {
        this.natelier = atelier;
    }

    public Integer getId() {
        return id;
    }

    public Date getDatedispo() {
        return datedispo;
    }

    public void setDatedispo(Date datedispo) {
        this.datedispo = datedispo;
    }

    public double getPenalitetotale() {
        return penalitetotale;
    }

    public void setPenalitetotale(double penalitetotale) {
        this.penalitetotale = penalitetotale;
    }

    public Atelier getNatelier() {
        return natelier;
    }

    public void setNatelier(Atelier natelier) {
        this.natelier = natelier;
    }

    @XmlTransient
    public Collection<Tache> getTacheCollection() {
        return tacheCollection;
    }

    public void setTacheCollection(Collection<Tache> tacheCollection) {
        this.tacheCollection = tacheCollection;
    }
    
    public boolean addTache(Tache t) {
        if (tacheCollection.contains(t) || t == null) {
            return false;
        }
        
        // Update date début de la tache
        t.setDatedebut(getDatedispo());
        
        // Update la date de dispo à l'ajout de la tâche
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDatedispo());
        cal.add(Calendar.MINUTE, t.getTempsprod());
        datedispo = cal.getTime();
        
        // Update la pénalité si nécessaire
        if (getDatedispo().after(t.getDatelimite())) {
            penalitetotale = getPenalitetotale() + t.getPenaliteretard();
        }
        
        // Update date dispo atelier
        if (getDatedispo().after(getNatelier().getDatedispo())) {
            getNatelier().setDatedispo(getDatedispo());
        }
        
        t.setNmachine(this);
        return tacheCollection.add(t);
    }
    
    protected boolean delTache(Tache t) {
        // Not yet implemented
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.datedispo);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.penalitetotale) ^ (Double.doubleToLongBits(this.penalitetotale) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.natelier);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Machine other = (Machine) obj;
        if (Double.doubleToLongBits(this.penalitetotale) != Double.doubleToLongBits(other.penalitetotale)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datedispo, other.datedispo)) {
            return false;
        }
        if (!Objects.equals(this.natelier, other.natelier)) {
            return false;
        }
        return true;
    }

    
    
    @Override
    public String toString() {
        return "tp_poo4_4.metier.Machine[ id=" + id + " ]";
    }
    
}
