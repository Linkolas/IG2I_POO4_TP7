/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_poo4_4.metier;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "ATELIER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atelier.findAll", query = "SELECT a FROM Atelier a")
    , @NamedQuery(name = "Atelier.findById", query = "SELECT a FROM Atelier a WHERE a.id = :id")
    , @NamedQuery(name = "Atelier.findByDatedispo", query = "SELECT a FROM Atelier a WHERE a.datedispo = :datedispo")})
public class Atelier implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "natelier")
    private Collection<Machine> machineCollection = new ArrayList<>();

    public Atelier() {
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

    @XmlTransient
    public Collection<Machine> getMachineCollection() {
        return machineCollection;
    }

    public void setMachineCollection(Collection<Machine> machineCollection) {
        this.machineCollection = machineCollection;
    }
    
    public boolean addMachine(Machine machine) {
        if (machineCollection.contains(machine) || machine == null) {
            return false;
        }
        
        if (machine.getNatelier() != null) {
            machine.getNatelier().delMachine(machine);
        }
        
        machine.setNatelier(this);
        return machineCollection.add(machine);
    }
    
    protected boolean delMachine(Machine m) {
        return machineCollection.remove(m);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.datedispo);
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
        final Atelier other = (Atelier) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.datedispo, other.datedispo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "tp_poo4_4.metier.Atelier[ id=" + id + " ]";
    }
    
}
