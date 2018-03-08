/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mis-Dark
 */
public class Conexion extends Thread {

    public ResultSet tabla;
    private Connection conexion;
    public Statement s;
    private String userDB;
    private String passwordDB;

    public Conexion() {
        this.userDB = "root";
        this.passwordDB = "1994";
    }
    public void conexion(String tabla) {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/puntoventa", this.userDB, this.passwordDB);
            s = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            this.tabla = s.executeQuery("SELECT * FROM " + tabla);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro conectar a la BD debido: \n" + ex);
        }
    }
    
    public int excecuteSQLAutoIncrement(String sql){
        int id = 0;
        
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/puntoventa", this.userDB, this.passwordDB);
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  
            ps.execute();
            tabla = ps.getGeneratedKeys();
            if(tabla.next()){
                 id = tabla.getInt(1);
             }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro insertar el valor: \n" + ex);
        }
        return id;
    }
    
    public void close() {
        try {
            tabla.close();
            conexion.close();
            s.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo terminar la Conexion a la BD" + ex);
        }
    }
    
    protected Boolean Credito(String credito) {
        System.out.println(credito);
        if ("Si".equals(credito) || "si".equals(credito) || "SI".equals(credito)) {
            return true;
        } else {
            return false;
        }
    }
    
    protected String Credito(Boolean credito) {
        if (credito) {
            return "SI";

        } else {
            return "NO";
        }
    }
}
