/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author Mis-Dark
 */
public class InventoryBD extends Thread {

    private Conexion conexion;
    private String table_name;
    
    public InventoryBD() {
        conexion = new Conexion();
        table_name = "inventory";
    }

    public void newInventory(String[] pro) {
        conexion.conexion(table_name);
        try {
            conexion.tabla.moveToInsertRow();
            conexion.tabla.updateString("product_id", pro[0]);
            conexion.tabla.updateString("order_id", pro[1]);
            conexion.tabla.updateString("quantity", pro[2]);
            conexion.tabla.updateString("price", pro[3]);
            conexion.tabla.insertRow();
            conexion.close();          
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar en inventario "+ex.getMessage());
        }
    }
}