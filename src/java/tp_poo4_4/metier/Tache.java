/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nicolas
 */
@Entity
@Table(name = "TACHE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tache.findAll", query = "SELECT t FROM Tache t")
    , @NamedQuery(name = "Tache.findById", query = "SELECT t FROM Tache t WHERE t.id = :id")
    , @NamedQuery(name = "Tache.findByTempsprod", query = "SELECT t FROM Tache t WHERE t.tempsprod = :tempsprod")
    , @NamedQuery(name = "Tache.findByDatedebut", query = "SELECT t FROM Tache t WHERE t.datedebut = :datedebut")
    , @NamedQuery(name = "Tache.findByDatelimite", query = "SELECT t FROM Tache t WHERE t.datelimite = :datelimite")
    , @NamedQuery(name = "Tache.findByPenaliteretard", query = "SELECT t FROM Tache t WHERE t.penaliteretard = :penaliteretard")})
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "TEMPSPROD")
    private int tempsprod = 0;
    @Column(name = "DATEDEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datedebut = Date.from(Instant.now());
    @Basic(optional = false)
    @Column(name = "DATELIMITE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datelimite = Date.from(Instant.now());
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PENALITERETARD")
    private Double penaliteretard = 0.0;
    @JoinColumn(name = "NMACHINE", referencedColumnName = "ID")
    @ManyToOne
    private Machine nmachine = null;

    public Tache() {
    }
    
    public Tache(int tempsprod, Date datelimite) {
        this.tempsprod = tempsprod;
        this.datelimite = datelimite;
    }
    
    public Tache(int tempsprod, Date datelimite, Double penaliteretard) {
        this.tempsprod = tempsprod;
        this.datelimite = datelimite;
        this.penaliteretard = penaliteretard;
    }

    public Integer getId() {
        return id;
    }

    public int getTempsprod() {
        return tempsprod;
    }

    public void setTempsprod(int tempsprod) {
        this.tempsprod = tempsprod;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatelimite() {
        return datelimite;
    }

    public void setDatelimite(Date datelimite) {
        this.datelimite = datelimite;
    }

    public Double getPenaliteretard() {
        return penaliteretard;
    }

    public void setPenaliteretard(Double penaliteretard) {
        this.penaliteretard = penaliteretard;
    }

    public Machine getNmachine() {
        return nmachine;
    }

    public void setNmachine(Machine nmachine) {
        this.nmachine = nmachine;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + this.tempsprod;
        hash = 53 * hash + Objects.hashCode(this.datedebut);
        hash = 53 * hash + Objects.hashCode(this.datelimite);
        hash = 53 * hash + Objects.hashCode(this.penaliteretard);
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
        final Tache other = (Tache) obj;
        if (this.tempsprod != other.tempsprod) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datedebut, other.datedebut)) {
            return false;
        }
        if (!Objects.equals(this.datelimite, other.datelimite)) {
            return false;
        }
        if (!Objects.equals(this.penaliteretard, other.penaliteretard)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tp_poo4_4.metier.Tache[ id=" + id + " ]";
    }
    
}
