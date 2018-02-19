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
 * @author Caja1
 */
public class FacturasAll extends Thread{
    DefaultTableModel facturas;
    Conexion con;
    public FacturasAll(DefaultTableModel t){
        this.facturas=t;
        con = new Conexion();
    }
    public void run(){
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);

        try {
            con.conexion("facturas");
            while (con.tabla.next()) {
                String[] fila = {
                    con.tabla.getString("NumeroFactura"),
                    con.tabla.getString("Cedula"),
                    con.tabla.getString("Fecha"),
                    con.tabla.getString("Hora"),
                    con.tabla.getString("Total"),
                    con.Credito(con.tabla.getBoolean("CreditoFactura"))
                };
                facturas.addRow(fila);
            }
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
    }
}