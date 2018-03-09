/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.HibernateUtil;
import Entity.Usuarios;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Caja1
 */
public class UsuariosBD extends Conexion{

    public Conexion usuario;
    public UsuariosBD() {
        usuario = new Conexion();
        
    }
    
    public void nuevo(Usuarios user) {
        try { 
            iniciaOperacion(); 
            sesion.save(user); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            JOptionPane.showMessageDialog(null, "No se puedo crear el usuario.");
            throw he; 
        }finally { 
            sesion.close(); 
        }  
    }

    public Usuarios consultar(String cedula) {
        Usuarios usuario = null;  
        try{
            this.iniciaOperacion();
            usuario = (Usuarios) this.sesion.get(Usuarios.class, cedula);
        } finally {
            this.sesion.close();
        }
        return usuario; 
    }
    
    public Usuarios consultarSeccion(String user) {
        return this.getUserByUsername(user);
    }

    public void editar(Usuarios user) {
        
        try { 
            iniciaOperacion(); 
            sesion.update(user); 
            tx.commit(); 
        } catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el usuario");
        } finally { 
            sesion.close(); 
        } 
    }

    public void eliminar(String cedula) {
        Usuarios product = this.consultar(cedula);
        try { 
            iniciaOperacion(); 
            sesion.delete(product); 
            tx.commit(); 
        } catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "Error al eleminar el usuario.");
        } finally { 
            sesion.close(); 
        }
    }

    public List<Usuarios> todos() {
        List<Usuarios> users = null;  
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Usuarios");
            users = query.list();
        } finally { 
            sesion.close(); 
        }  
        return users; 
    }
    
    public Usuarios getUserByUsername(String username) throws HibernateException{ 
        Usuarios usuario = new Usuarios();  
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Usuarios WHERE Usuario = :username")
                    .setParameter("username", username);
            usuario = (Usuarios) query.uniqueResult();
        } finally { 
            sesion.close(); 
        }  
        return usuario; 
    }
}