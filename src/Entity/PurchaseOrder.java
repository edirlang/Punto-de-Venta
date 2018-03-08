package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.io.Serializable;
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
@Table(name = "purchase_order")
public class PurchaseOrder implements Serializable  {
    @Id
    @GeneratedValue  
    private int id;
    
    @ManyToOne
    @JoinColumn(name="provider_id", nullable=false)
    private Provider provider;
    
    @Column(name = "total")
    private String total;
    
    @Column(name = "file")
    private byte[] file;
    
    @Column(name = "createdAt")
    private Date createdAt;
    
    @OneToMany(mappedBy="purchaseOrder")
    //@JoinColumn(name="order_id", nullable=true)
    private Set<Inventory> inventories;
    
    public PurchaseOrder() {
    }

	
    public PurchaseOrder(int id, Provider provider, String total, byte[] file, Date createdAt) {
        this.id = id;
        this.provider = provider;
        this.total = total;
        this.file = file;
        this.createdAt = createdAt;
    }
    public PurchaseOrder(int id, Provider provider, String total, byte[] file, Date createdAt, Set inventories) {
       this.id = id;
       this.provider = provider;
       this.total = total;
       this.file = file;
       this.createdAt = createdAt;
       this.inventories = inventories;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Provider getProvider() {
        return this.provider;
    }
    
    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    public String getTotal() {
        return this.total;
    }
    
    public void setTotal(String total) {
        this.total = total;
    }
    public byte[] getFile() {
        return this.file;
    }
    
    public void setFile(byte[] file) {
        this.file = file;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }
    
    public void addInventory(Inventory inventory){
        this.inventories.add(inventory);
    }
    
    public void removeInventory(Inventory inventory){
        this.inventories.remove(inventory);
    }
}


