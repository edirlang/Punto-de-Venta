package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventory")
public class Inventory  implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private PurchaseOrder purchaseOrder;
    
    @Column(name="quantity")
    private long quantity;
    
    @Column(name="price")
    private long price;

    public Inventory() {
    }

    public Inventory(int id, Product product, PurchaseOrder purchaseOrder, long quantity, long price) {
       this.id = id;
       this.product = product;
       this.purchaseOrder = purchaseOrder;
       this.quantity = quantity;
       this.price = price;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    public PurchaseOrder getPurchaseOrder() {
        return this.purchaseOrder;
    }
    
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    public long getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public long getPrice() {
        return this.price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }




}


