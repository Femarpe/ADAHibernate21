package domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tramite")
public class Tramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtramite")
    private int idtramite;
    @Column(name = "tipoTramite")
    private String tipoTramite;
    @Column(name = "fechaTramite")
    private Timestamp fechaTramite;

    /**
     @OneToOne(mappedBy = "Tramite", cascade = CascadeType.REMOVE)
     private Presupuesto presupuesto;
     m*/
    public Tramite() {
    }

    public int getIdtramite() {
        return idtramite;
    }

    public void setIdtramite(int idtramite) {
        this.idtramite = idtramite;
    }


    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    public Timestamp getFechaTramite() {
        return fechaTramite;
    }

    public void setFechaTramite(Timestamp fechaTramite) {
        this.fechaTramite = fechaTramite;
    }

    @Override
    public String toString() {
        return "Tramite [idtramite= " + idtramite + ", tipoTramite= " + tipoTramite + ", fechaTramite =" + fechaTramite + "]";
    }
}