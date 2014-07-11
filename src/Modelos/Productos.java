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
 * @author Mis-Dark
 */
public class Productos extends Thread {

    public Conexion producto;

    public Productos() {
        producto = new Conexion("Productos");
    }

    public void nuevo(String codigo, String descripcion, int cantidad, long precio, long comprado, boolean jueves) {
        try {
            producto.tabla.moveToInsertRow();
            producto.tabla.updateString("Codigo", codigo);
            producto.tabla.updateString("Descripcion", descripcion);
            producto.tabla.updateString("Precio", "" + precio);
            producto.tabla.updateString("PrecioCompra", "" + comprado);
            producto.tabla.updateString("Cantidad", "" + cantidad);
            producto.tabla.updateBoolean("Jueves", jueves);
            producto.tabla.insertRow();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
    }

    public String[] consultar(String codigo) {
        String[] fila = null;
        try {
            while (producto.tabla.next()) {
                if (producto.tabla.getString("Codigo").equalsIgnoreCase(codigo)) {
                    fila = new String[]{producto.tabla.getString("codigo"), producto.tabla.getString("Descripcion"), producto.tabla.getString("Precio"), producto.tabla.getString("PrecioCompra"),
                        producto.tabla.getString("Cantidad"), producto.tabla.getString("Jueves")};
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        return fila;
    }

    public void editar(String codigo, String descripcion, int cantidad, long precio, long comprado, boolean jueves) {
        try {
            while (producto.tabla.next()) {
                if (producto.tabla.getString("codigo").equalsIgnoreCase(codigo)) {
                    producto.tabla.updateString("descripcion", descripcion);
                    producto.tabla.updateString("cantidad", "" + cantidad);
                    producto.tabla.updateString("precio", "" + precio);
                    producto.tabla.updateString("comprado", "" + comprado);
                    producto.tabla.updateBoolean("jueves", jueves);
                    producto.tabla.updateRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
    }

    public void eliminar(String codigo) {
        try {
            while (producto.tabla.next()) {
                if (producto.tabla.getString("codigo").equalsIgnoreCase(codigo)) {
                    producto.tabla.deleteRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
    }
    
    public ArrayList todos() {
        ArrayList<String[]> productos = null;
        try {
            while (producto.tabla.next()) {
                productos.add(consultar(producto.tabla.getString("codigo")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        return productos;
    }
}
