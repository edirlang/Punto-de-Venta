package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detallefactura")
public class Detallefactura  implements Serializable {
    
    @Id
    @GeneratedValue 
    private int id;
    
    @ManyToOne
    @JoinColumn(name="NumeroFactura", nullable=true)
    private Facturas factura;
    
    @ManyToOne
    @JoinColumn(name="Codigo", nullable=true)
    private Product product;
    
    @Column(name="valor")
    private double valor;
    
    @Column(name="cantidad")
    private double cantidad;
    
    @Column(name="total")
    private double total;

    public Detallefactura() {
    }

    public Detallefactura(int id, Facturas facturas, Product product, double valor, double cantidad, double total) {
       this.id = id;
       this.factura = facturas;
       this.product = product;
       this.valor = valor;
       this.cantidad = cantidad;
       this.total = total;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Facturas getFacturas() {
        return this.factura;
    }
    
    public void setFacturas(Facturas facturas) {
        this.factura = facturas;
    }
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public long getValor() {
        return (long)this.valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    public long getCantidad() {
        return (long)this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public long getTotal() {
        return (long)this.total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }




}


