/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Conexion;
import Modelos.ProductosBD;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class GuardarDetalleFactura extends Thread {

    Conexion conexion;
    DefaultTableModel detalles;
    String datos;
    ProductosBD producto;

    public GuardarDetalleFactura(DefaultTableModel detalles, String datos) {
        this.detalles = detalles;
        this.datos = datos;
        producto = new ProductosBD();
        conexion = new Conexion();
    }

    @Override
    public void run() {
        conexion.conexion("detallefactura");
        try {
            for (int i = 0; i < detalles.getRowCount(); i++) {

                conexion.tabla.moveToInsertRow();
                conexion.tabla.updateString("NumeroFactura", datos);
                conexion.tabla.updateString("Codigo", detalles.getValueAt(i, 0).toString());
                conexion.tabla.updateString("Valor", detalles.getValueAt(i, 3).toString());
                conexion.tabla.updateString("Cantidad", detalles.getValueAt(i, 2).toString());
                conexion.tabla.updateString("Total", detalles.getValueAt(i, 4).toString());
                conexion.tabla.insertRow();
                producto.RestarProducto(detalles.getValueAt(i, 0).toString(), Integer.parseInt(detalles.getValueAt(i, 2).toString()));
            }
            conexion.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro almecenar " + ex);
            conexion.close();
        }
    }
}
