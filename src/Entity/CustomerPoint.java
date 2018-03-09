package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1


import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "customer_point")
public class CustomerPoint  implements Serializable {
    
    @Id
    private long id;
    
    @Column(name="quantity")
    private int quantity;
    
    @Column(name = "created_at")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdAt;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Clientes customer;

    public CustomerPoint() {
        this.createdAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Clientes getCustomer() {
        return customer;
    }

    public void setCustomer(Clientes customer) {
        this.customer = customer;
    }
}


