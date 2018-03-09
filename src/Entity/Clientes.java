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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "clientes")
public class Clientes  implements Serializable {
    
    @Id
    @Column(name = "document_number")
    private String documentNumber;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "date_birth")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBirth;
    
    @Column(name = "isCredit")
    private boolean isCredit;
    
    @Column(name = "isPoints")
    private boolean isPoints = true;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="clientes")
    private Set<Facturas> facturas;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
    private Set<CustomerPoint> points;

    public Clientes() {
        this.isPoints = true;
        this.points = null;
        this.isCredit = false;
    }
	
    public Clientes(String documentNumber, String firstName, String lastName, String phoneNumber, String address, Date dateBirth, boolean isCredit) {
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateBirth = dateBirth;
        this.isCredit = isCredit;
    }
    public Clientes(String documentNumber, String firstName, String lastName, String phoneNumber, String address, Date dateBirth, boolean isCredit, Set facturases) {
       this.documentNumber = documentNumber;
       this.firstName = firstName;
       this.lastName = lastName;
       this.phoneNumber = phoneNumber;
       this.address = address;
       this.dateBirth = dateBirth;
       this.isCredit = isCredit;
       this.facturas = facturases;
    }
   
    public String getDocumentNumber() {
        return this.documentNumber;
    }
    
    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getDateBirth() {
        return this.dateBirth;
    }
    
    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }
    public boolean getIsCredit() {
        return this.isCredit;
    }
    
    public void setIsCredit(boolean isCredit) {
        this.isCredit = isCredit;
    }
    
    public String getTextIsCredit(){
        String credit = "Si";
        if(this.isCredit == false){
            credit = "No";
        }
        return credit;
    }
    
    public Set getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set facturas) {
        this.facturas = facturas;
    }
    
    public void addFactura(Facturas factura){
        this.facturas.add(factura);
    }
    
    public void removeFactura(Facturas factura){
        this.facturas.remove(factura);
    }
    
    public String getFullName(){
        return this.getFirstName()+" "+this.getLastName();
    }
    
    public Set<CustomerPoint> getPoints() {
        return points;
    }

    public void setPoints(Set<CustomerPoint> points) {
        this.points = points;
    }
    
    public void addPoints(CustomerPoint customerPoint){
        this.points.add(customerPoint);
    }
    
    public void removePoints(CustomerPoint customerPoint){
        this.points.remove(customerPoint);
    }
    
    public boolean getIsPoints() {
        return isPoints;
    }

    public void setIsPoints(boolean isPoints) {
        this.isPoints = isPoints;
    }
}


