package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="facturas")
public class Product  implements java.io.Serializable {
    
    @Id
    @GeneratedValue
    private String barCode;
    
    @Column(name="name")
    private String name;
    
    @Column(name="salePrice")
    private int salePrice;
    
    @Column(name="quantity")
    private long quantity;
    
    @OneToMany(mappedBy="product")
    //@JoinColumn(name="Codigo", nullable=true)
    private Set<Detallefactura> detallefacturas;
    
    @OneToMany(mappedBy="product")
    //@JoinColumn(name="product_id", nullable=true)
    private Set<Inventory> inventories;

    public Product() {
    }

	
    public Product(String barCode, String name, int salePrice, long quantity) {
        this.barCode = barCode;
        this.name = name;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }
    public Product(String barCode, String name, int salePrice, long quantity, Set detallefacturas, Set inventories) {
       this.barCode = barCode;
       this.name = name;
       this.salePrice = salePrice;
       this.quantity = quantity;
       this.detallefacturas = detallefacturas;
       this.inventories = inventories;
    }
   
    public String getBarCode() {
        return this.barCode;
    }
    
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public int getSalePrice() {
        return this.salePrice;
    }
    
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
    public long getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public Set getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(Set detallefacturas) {
        this.detallefacturas = detallefacturas;
    }
    public Set getInventories() {
        return this.inventories;
    }
    
    public void setInventories(Set inventories) {
        this.inventories = inventories;
    }
    
    public void addInventory(Inventory inventory){
        this.inventories.add(inventory);
    }
    
    public void removeInventory(Inventory inventory){
        this.inventories.remove(inventory);
    }
}


