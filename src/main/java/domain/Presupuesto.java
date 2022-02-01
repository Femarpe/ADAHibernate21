package domain;

import domain.Tramite;

import javax.persistence.*;

@Entity
@Table(name = "presupuesto")
public class Presupuesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpresupuesto")
    private int id;
    @Column(name = "lugarPresupuesto")
    private String lugar;


    @OneToOne
    @JoinColumn(name = "Tramite_idTramite")
    private Tramite tramite;

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public Presupuesto() {
    }

    public Presupuesto(String lugar) {
        this.lugar = lugar;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public String getLugar() {
        return lugar;
    }

    @Override
    public String toString() {
        return "Presupuesto [idPresupuesto= " + id + ", lugarPresupuesto= " + lugar + ", Tramite_idTramite= " + tramite + "]";
    }
}