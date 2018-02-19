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
 * @author Caja1
 */
public class Egresos extends Conexion{
    public Egresos(){
        super();
    }
    
    public void NuevoEgreso(String[] datos){
        try {
            this.conexion("egresos");
            tabla.moveToInsertRow();
            tabla.updateString("Fecha", datos[0]);
            tabla.updateString("Descripcion", datos[1]);
            tabla.updateString("Valor", datos[2]);
            tabla.insertRow();
            close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Pago  no registrado" + ex);
        }    
    }
    
    public String ConsultarValor(String Fecha) {
        conexion("egresos");
        try {
            long factura = 0;
            tabla.first();
            while (tabla.next()) {
                if (Fecha.equalsIgnoreCase(tabla.getString("Fecha"))) {
                    factura += Long.parseLong(tabla.getString("Valor"));
                }
            }
            close();
            return factura+"";
        } catch (SQLException ex) {
            return "0";
        }
    }
}