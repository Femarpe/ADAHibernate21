package test;

import javax.persistence.*;

@Entity
@Table(name = "Spartan")
public class Spartan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private int codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apodo")
    private String apodo;

    public Spartan() {
        codigo = 0;
        nombre = "";
        apodo = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    @Override
    public String toString() {
        return "Spartan [codigo= " + codigo + ", nombre= " + nombre + ", apodo= " + apodo + "]";
    }
}
