/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Caja1
 */
public class UsuariosBD {

    public Conexion usuario;

    public UsuariosBD() {
        usuario = new Conexion("usuarios");
    }

    public void nuevo(String[] usuarioNuevo) {
        try {
            usuario.tabla.moveToInsertRow();
            usuario.tabla.updateString("Cedula", usuarioNuevo[0]);
            usuario.tabla.updateString("Nombre", usuarioNuevo[1]);
            usuario.tabla.updateString("Apellido", usuarioNuevo[2]);
            usuario.tabla.updateString("Telefono", usuarioNuevo[3]);
            usuario.tabla.updateString("Usuario", usuarioNuevo[4]);
            usuario.tabla.updateString("Contrasena", usuarioNuevo[5]);
            usuario.tabla.updateString("Rol", usuarioNuevo[6]);
            usuario.tabla.insertRow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
    }

    public String[] consultar(String cedula) {
        String[] fila = null;
        try {
            while (usuario.tabla.next()) {
                if (usuario.tabla.getString("Cedula").equalsIgnoreCase(cedula)) {
                    fila = new String[]{usuario.tabla.getString("Cedula"),
                        usuario.tabla.getString("Nombre"),
                        usuario.tabla.getString("Apellido"),
                        usuario.tabla.getString("Telefono"),
                        usuario.tabla.getString("Usuario"),
                        usuario.tabla.getString("Contrasena"),
                        usuario.tabla.getString("Rol")
                    };
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return fila;
    }
    
    public String[] consultarSeccion(String user) {
        String[] fila = null;
        try {
            while (usuario.tabla.next()) {
                if (usuario.tabla.getString("Usuario").equalsIgnoreCase(user)) {
                    fila = new String[]{
                        usuario.tabla.getString("Cedula"),
                        usuario.tabla.getString("Nombre"),
                        usuario.tabla.getString("Apellido"),
                        usuario.tabla.getString("Telefono"),
                        usuario.tabla.getString("Usuario"),
                        usuario.tabla.getString("Contrasena"),
                        usuario.tabla.getString("Rol")
                    };
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return fila;
    }

    public void editar(String[] usuarioEditar) {
        try {
            while (usuario.tabla.next()) {
                if (usuario.tabla.getString("Cedula").equalsIgnoreCase(usuarioEditar[0])) {
                    usuario.tabla.moveToInsertRow();
                    usuario.tabla.updateString("Cedula", usuarioEditar[0]);
                    usuario.tabla.updateString("Nombre", usuarioEditar[1]);
                    usuario.tabla.updateString("Apellido", usuarioEditar[2]);
                    usuario.tabla.updateString("Telefono", usuarioEditar[3]);
                    usuario.tabla.updateString("Usuario", usuarioEditar[4]);
                    usuario.tabla.updateString("Contrasena", usuarioEditar[5]);
                    usuario.tabla.updateString("Rol", usuarioEditar[6]);
                    usuario.tabla.updateRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
    }

    public void eliminar(String cedula) {
        try {
            while (usuario.tabla.next()) {
                if (usuario.tabla.getString("Cedula").equalsIgnoreCase(cedula)) {
                    usuario.tabla.deleteRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
    }

    public ArrayList todos() {
        ArrayList<String[]> usuarios = null;
        try {
            while (usuario.tabla.next()) {
                usuarios.add(consultar(usuario.tabla.getString("Cedula")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        return usuarios;
    }
}
