package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="facturas")
public class Facturas  implements java.io.Serializable {

    @Id
    @GeneratedValue
    private int numeroFactura;
    
    @ManyToOne
    @JoinColumn(name="Cedula", nullable=false)
    private Clientes clientes;
    
    @Column(name="Fecha")
    private Date fecha;
    
    @Column(name="Hora")
    private Date hora;
    
    @Column(name="Total")
    private long total;
    
    @Column(name="CreditoFactura")
    private boolean creditoFactura;
    
    @OneToMany(mappedBy="factura")
    //@JoinColumn(name="NumeroFactura", nullable=true)
    private Set<Detallefactura> detallefacturas;

    public Facturas() {
    }

	
    public Facturas(int numeroFactura, Clientes clientes, Date fecha, Date hora, long total, boolean creditoFactura) {
        this.numeroFactura = numeroFactura;
        this.clientes = clientes;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.creditoFactura = creditoFactura;
    }
    public Facturas(int numeroFactura, Clientes clientes, Date fecha, Date hora, long total, boolean creditoFactura, Set detallefacturas) {
       this.numeroFactura = numeroFactura;
       this.clientes = clientes;
       this.fecha = fecha;
       this.hora = hora;
       this.total = total;
       this.creditoFactura = creditoFactura;
       this.detallefacturas = detallefacturas;
    }
   
    public int getNumeroFactura() {
        return this.numeroFactura;
    }
    
    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public long getTotal() {
        return this.total;
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    public boolean isCreditoFactura() {
        return this.creditoFactura;
    }
    
    public void setCreditoFactura(boolean creditoFactura) {
        this.creditoFactura = creditoFactura;
    }
    public Set getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(Set detallefacturas) {
        this.detallefacturas = detallefacturas;
    }
    
    public void addDetalleFactura(Detallefactura detalle){
        this.detallefacturas.add(detalle);
    }
    
    public void removeDetalleFactura(Detallefactura detalle){
        this.detallefacturas.remove(detalle);
    }
}


