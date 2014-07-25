/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.UsuariosBD;
import Vistas.Login;

/**
 *
 * @author Caja1
 */
public class Usuarios {

    UsuariosBD Usuario;

    public Usuarios() {
        Usuario = new UsuariosBD();
    }

    public Boolean IniciarSession(String[] usuario) {
        String[] cajero = Usuario.consultarSeccion(usuario[0]);
        try {
            if (cajero[5].equalsIgnoreCase(usuario[1])) {
                Login.ccUsuario=cajero[0];
                return true;
            } else {
                return false;
            }
        } catch (NullPointerException ex) {
            return false;
        }
        
    }
}
