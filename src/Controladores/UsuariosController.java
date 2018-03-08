/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.HibernateUtil;
import Entity.Usuarios;
import Modelos.UsuariosBD;
import Vistas.Login;
import java.awt.Event;
import java.awt.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

/**
 *
 * @author Caja1
 */
public class UsuariosController {

    UsuariosBD Usuario;
    private static EntityManagerFactory entityManagerFactory =
          Persistence.createEntityManagerFactory("example-unit");

    public UsuariosController() {
        Usuario = new UsuariosBD();
    }

    public Boolean IniciarSession(String[] usuario) {
        Usuarios cajero = null;
        cajero = this.Usuario.getUserByUsername(usuario[0]);
        
         EntityManager em = entityManagerFactory.createEntityManager();
         
        //String[] cajero = Usuario.consultarSeccion(usuario[0]);
        try {
            if (cajero.getContrasena().equalsIgnoreCase(usuario[1])) {
                Login.NombreUsuario = cajero.getNombre();
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException ex) {
            return false;
        }
        
    }
}
