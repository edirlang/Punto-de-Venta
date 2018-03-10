package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="facturas")
public class Facturas  implements java.io.Serializable {

    @Id
    @GeneratedValue
    @Column(name="NumeroFactura")
    private int numeroFactura;
    
    @ManyToOne
    @JoinColumn(name="Cedula", nullable=false)
    private Clientes clientes;
    
    @Column(name="Fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name="Hora")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date hora;
    
    @Column(name="Total")
    private long total;
    
    @Column(name="CreditoFactura")
    private boolean creditoFactura;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="factura")
    private Collection<Detallefactura> detallefacturas;

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
    
    public String getDateText(){
        return new SimpleDateFormat("yyyy-MM-dd").format(this.fecha);
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    public long getTotal() {
        return this.total;
    }
    
    public String getHourText(){
        return new SimpleDateFormat("hh:mm:ss").format(this.hora);
    }
    
    public void setTotal(long total) {
        this.total = total;
    }
    
    public boolean getCreditoFactura() {
        return this.creditoFactura;
    }
    
    public String getCreditoFacturaText() {
        if (this.creditoFactura) {
            return "SI";
        } else {
            return "NO";
        }
    }
    
    public void setCreditoFactura(boolean creditoFactura) {
        this.creditoFactura = creditoFactura;
    }
    
    public Collection<Detallefactura> getDetallefacturas() {
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


