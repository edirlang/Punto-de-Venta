package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="egresos")
public class Egresos  implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @Column(name="valor")
    private long valor;

    public Egresos() {
    }

    public Egresos(int id, Date fecha, String descripcion, long valor) {
       this.id = id;
       this.fecha = fecha;
       this.descripcion = descripcion;
       this.valor = valor;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public long getValor() {
        return this.valor;
    }
    
    public void setValor(long valor) {
        this.valor = valor;
    }
}