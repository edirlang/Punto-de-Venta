/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import java.sql.SQLException;

/**
 *
 * @author Caja1
 */
public class Facturas extends Conexion{
    
    public Facturas(){
        super();
    }
    
    protected int NumeroUltimaFactura(){
        try {
            this.conexion("facturas");
            tabla.last();
            int numero = tabla.getInt("NumeroFactura");
            close();
            return numero;
        } catch (SQLException ex) {
            return 0;
        }
    }
}
