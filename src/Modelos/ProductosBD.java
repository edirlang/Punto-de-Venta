/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mis-Dark
 */
public class ProductosBD extends Thread {

    private Conexion producto;

    public ProductosBD() {
        producto = new Conexion("productos");
    }

    public void nuevo(String[] pro) {
        try {
            producto.tabla.moveToInsertRow();
            producto.tabla.updateString("Codigo", pro[0]);
            producto.tabla.updateString("Descripcion", pro[1]);
            producto.tabla.updateString("Precio", "" + pro[2]);
            producto.tabla.updateString("PrecioCompra", "" + pro[3]);
            producto.tabla.updateString("Cantidad", "" + pro[4]);
            if ((pro[5].equalsIgnoreCase("si"))||(pro[5].equalsIgnoreCase("SI"))||(pro[5].equalsIgnoreCase("Si"))) {
                producto.tabla.updateBoolean("Jueves", true);
            } else {
                producto.tabla.updateBoolean("Jueves", false);
            }
            producto.tabla.insertRow();
            JOptionPane.showMessageDialog(null, "Producto Creado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
    }

    public String[] consultar(String codigo) {
        String[] fila = null;
        try {
            while (producto.tabla.next()) {
                if (producto.tabla.getString("Codigo").equalsIgnoreCase(codigo)) {
                    fila = new String[]{
                        producto.tabla.getString("Codigo"), 
                        producto.tabla.getString("Descripcion"), 
                        producto.tabla.getString("Precio"), 
                        producto.tabla.getString("PrecioCompra"),
                        producto.tabla.getString("Cantidad"), 
                        producto.tabla.getString("Jueves")
                    };
                    return fila;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        if(fila == null){
            JOptionPane.showMessageDialog(null,"Producto no encontrado");
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

    public DefaultTableModel todos() {
        DefaultTableModel productos = null;
        try {
            while (producto.tabla.next()) {
                productos.addRow(consultar(producto.tabla.getString("codigo")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        return productos;
    }
}