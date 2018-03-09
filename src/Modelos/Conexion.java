/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.HibernateUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public Session sesion;
    public Transaction tx;

    public Conexion() {
        this.userDB = "root";
        this.passwordDB = "";
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
    
    public Boolean Credito(String credito) {
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
    
    public void iniciaOperacion() throws HibernateException
    {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }
    
    public void manejaExcepcion(HibernateException he) throws HibernateException
    {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }
}
