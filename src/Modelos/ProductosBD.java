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
    private PurchaseOrderBD order;
    private InventoryBD inventory;
    private String table_name;

    public ProductosBD() {
        producto = new Conexion();
        order = new PurchaseOrderBD();
        inventory = new InventoryBD();
        table_name = "product";
    }

    protected void nuevo(String[] pro) {
        
        try {
            producto.conexion("product");
            producto.tabla.moveToInsertRow();
            producto.tabla.updateString("bar_code", pro[0]);
            producto.tabla.updateString("name", pro[1]);
            producto.tabla.updateString("sale_price", "" + pro[2]);
            producto.tabla.updateString("quantity", "" + pro[4]);
            producto.tabla.insertRow();
            
            newInventory(pro);
            JOptionPane.showMessageDialog(null, "Producto Creado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
        producto.close();
    }
    
    protected int newPurchaseOrder(String[] pro){
        double total = (Double.parseDouble(pro[4]) * Double.parseDouble(pro[3]));
        String[] data = {"1", ""+total};
        return order.newOrder(data);
    }
    
    protected void newInventory(String[] pro){
        int order_id = newPurchaseOrder(pro);
        String[] data = { pro[0], ""+order_id, pro[4], pro[3] };
        inventory.newInventory(data);
    }
    
    public String[] consultar(String codigo) {
        String[] fila = null;
        try {
            producto.conexion(table_name);
            while (producto.tabla.next()) {
                if (producto.tabla.getString("bar_code").equalsIgnoreCase(codigo)) {
                    fila = new String[]{
                        producto.tabla.getString("bar_code"),
                        producto.tabla.getString("name"),
                        producto.tabla.getString("sale_price"),
                        producto.tabla.getString("quantity"),
                    };
                    return fila;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        producto.close();
        if (fila == null) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
        }
        return fila;
    }

    protected void editar(String[] pro) {
        Boolean confirmar=false;
        try {
            producto.conexion(table_name);
            while (producto.tabla.next()) {
                if (producto.tabla.getString("bar_code").equalsIgnoreCase(pro[0])) {
                    producto.tabla.updateString("bar_code", pro[0]);
                    producto.tabla.updateString("name", pro[1]);
                    producto.tabla.updateString("sale_price",pro[2]);
                    producto.tabla.updateString("quantity",pro[4]);
                    producto.tabla.updateRow();
                    confirmar=true;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto"+ ex);
        }
        producto.close();
        if(confirmar){
            JOptionPane.showMessageDialog(null,"Producto Actualizado");
        }else{
            JOptionPane.showMessageDialog(null,"No se logro actualizar");
        }
    }

    protected void eliminar(String codigo) {
        try {
            producto.conexion(table_name);
            while (producto.tabla.next()) {
                if (producto.tabla.getString("bar_code").equalsIgnoreCase(codigo)) {
                    producto.tabla.deleteRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        producto.close();
    }

    protected DefaultTableModel todos() {
        DefaultTableModel productos = new DefaultTableModel();
        String[] columnas = {"Codigo", "Descripción", "Precio", "Cantidad"};
        productos.addColumn(columnas[0]);
        productos.addColumn(columnas[1]);
        productos.addColumn(columnas[2]);
        productos.addColumn(columnas[3]);
        
        try {
            producto.conexion(table_name+" ORDER BY quantity ASC");
            while (producto.tabla.next()) {
                String[] fila ={
                        producto.tabla.getString("bar_code"),
                        producto.tabla.getString("name"),
                        producto.tabla.getString("sale_price"),
                        producto.tabla.getString("quantity"),
                    };
                productos.addRow(fila);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        }
        producto.close();
        return productos;
    }
    
    public void RestarProducto(String Codigo, int vendidos){
        producto.conexion(table_name);
        try {
            while(producto.tabla.next()){
               if(producto.tabla.getString("bar_code").equalsIgnoreCase(Codigo)){
                   int cantidad=Integer.parseInt(producto.tabla.getString("quantity"));
                   cantidad-=vendidos;
                   producto.tabla.updateString("quantity", cantidad+"");
                   producto.tabla.updateRow();
               } 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        producto.close();
    }
}