/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Mis-Dark
 */

public class Conexion {
    public ResultSet tabla;
    private Connection conexion;
    private Statement s;
    
    public Conexion(String tabla) {
        /*
        File f;
        String server="jdbc:mysql://localhost:3306/surtialiss2,root,pogramacion2";
        
        BufferedReader entrada;
        try {
            f = new File("base.txt");
            entrada = new BufferedReader(new FileReader(f));
            while (entrada.ready()) {
                server = entrada.readLine();
            }
            entrada.close();
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
            server="jdbc:mysql://localhost:3306/surtialiss2,root,pogramacion2";
        }
        */
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/surtialiss2","root","programacion2");
            s = conexion.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            this.tabla = s.executeQuery("SELECT * FROM " + tabla);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro conectar a la BD debido: \n" + ex);
        }

    }

    public void close() {
        try {
            tabla.close();
            conexion.close();
            s.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "NO se ha podido almacenar" + ex);
        }
    }
}
