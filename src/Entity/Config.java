package Entity;


import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Provider generated by hbm2java
 */
@Entity
@Table(name="config")
public class Config implements Serializable{
    @Id
    @GeneratedValue 
    private int id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "value")
    private String value;
    
    public Config() {
    }

	
    public Config(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}

