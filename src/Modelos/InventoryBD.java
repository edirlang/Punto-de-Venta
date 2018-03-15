/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.Inventory;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;


/**
 *
 * @author Mis-Dark
 */
public class InventoryBD extends Conexion {

    public InventoryBD() {
    }

    public void newInventory(Inventory inventory) throws HibernateException {
        try { 
            this.iniciaOperacion(); 
            sesion.save(inventory); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            JOptionPane.showMessageDialog(null, "No se pudo almacenar en inventario");
        }finally { 
            //this.sesion.flush();
            this.sesion.close(); 
        }
    }
}