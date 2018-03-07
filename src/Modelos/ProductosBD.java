/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mis-Dark
 */
public class ProductosBD extends Thread {

    private Conexion producto;

    public ProductosBD() {
        producto = new Conexion();
    }

    protected void nuevo(String[] pro) {
        try {
            producto.conexion("productos");
            producto.tabla.moveToInsertRow();
            producto.tabla.updateString("Codigo", pro[0]);
            producto.tabla.updateString("Nombre", pro[1]);
            producto.tabla.updateString("Precio", "" + pro[2]);
            producto.tabla.updateString("PrecioCompra", "" + pro[3]);
            producto.tabla.updateString("Cantidad", "" + pro[4]);
            producto.tabla.updateString("PrecioPromocion", pro[5]);
            producto.tabla.insertRow();
            JOptionPane.showMessageDialog(null, "Producto Creado");
            producto.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
    }

    public String[] consultar(String codigo) {
        String[] fila = null;
        try {
            producto.conexion("productos");
            while (producto.tabla.next()) {
                if (producto.tabla.getString("Codigo").equalsIgnoreCase(codigo)) {
                    fila = new String[]{
                        producto.tabla.getString("Codigo"),
                        producto.tabla.getString("Nombre"),
                        producto.tabla.getString("Precio"),
                        producto.tabla.getString("PrecioCompra"),
                        producto.tabla.getString("Cantidad"),
                        producto.tabla.getString("PrecioPromocion")
                    };
                    return fila;
                }
            }
            producto.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        if (fila == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
        return fila;
    }

    protected void editar(String[] pro) {
        Boolean confirmar=false;
        try {
            producto.conexion("productos");
            while (producto.tabla.next()) {
                if (producto.tabla.getString("codigo").equalsIgnoreCase(pro[0])) {
                    producto.tabla.updateString("Codigo", pro[0]);
                    producto.tabla.updateString("Nombre", pro[1]);
                    producto.tabla.updateString("Precio",pro[2]);
                    producto.tabla.updateString("PrecioCompra",pro[3]);
                    producto.tabla.updateString("Cantidad",pro[4]);
                    producto.tabla.updateString("PrecioPromocion",pro[5]);
                    producto.tabla.updateRow();
                    confirmar=true;
                }
            }
            producto.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto"+ ex);
        }
        if(confirmar){
            JOptionPane.showMessageDialog(null,"Producto Actualizado");
        }else{
            JOptionPane.showMessageDialog(null,"No se logro actualizar");
        }
    }

    protected void eliminar(String codigo) {
        try {
            producto.conexion("productos");
            while (producto.tabla.next()) {
                if (producto.tabla.getString("codigo").equalsIgnoreCase(codigo)) {
                    producto.tabla.deleteRow();
                }
            }
            producto.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
    }

    protected DefaultTableModel todos() {
        DefaultTableModel productos = new DefaultTableModel();
        String[] columnas = {"Codigo", "Descripción", "Precio", "Precio de Compra", "Cantidad", "Precio de Promocion"};
        productos.addColumn(columnas[0]);
        productos.addColumn(columnas[1]);
        productos.addColumn(columnas[2]);
        productos.addColumn(columnas[3]);
        productos.addColumn(columnas[4]);
        productos.addColumn(columnas[5]);
        
        try {
            producto.conexion("productos ORDER BY Cantidad ASC");
            while (producto.tabla.next()) {
                String[] fila ={
                        producto.tabla.getString("Codigo"),
                        producto.tabla.getString("Nombre"),
                        producto.tabla.getString("Precio"),
                        producto.tabla.getString("PrecioCompra"),
                        producto.tabla.getString("Cantidad"),
                        producto.tabla.getString("PrecioPromocion")
                    };
                productos.addRow(fila);
            }
            producto.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        return productos;
    }
    
    public void RestarProducto(String Codigo, int vendidos){
        producto.conexion("productos");
        try {
            while(producto.tabla.next()){
               if(producto.tabla.getString("Codigo").equalsIgnoreCase(Codigo)){
                   int cantidad=Integer.parseInt(producto.tabla.getString("Cantidad"));
                   cantidad-=vendidos;
                   producto.tabla.updateString("Cantidad", cantidad+"");
                   producto.tabla.updateRow();
               } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}