package Entity;
// Generated 8/03/2018 08:04:03 AM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuarios  implements java.io.Serializable {

    @Id
    @Column(name = "Cedula")
    private String cedula;
    
    @Column(name="Nombre")
    private String nombre;
    
    @Column(name="Apellido")
    private String apellido;
    
    @Column(name="Telefono")
    private String telefono;
    
    @Column(name="Usuario")
    private String usuario;
    
    @Column(name="Contrasena")
    private String contrasena;
    
    @Column(name="Rol")
    private String rol;

    public Usuarios() {
    }

    public Usuarios(String cedula, String nombre, String apellido, String telefono, String usuario, String contrasena, String rol) {
       this.cedula = cedula;
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.usuario = usuario;
       this.contrasena = contrasena;
       this.rol = rol;
    }
   
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }




}


