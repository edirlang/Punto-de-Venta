/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.PurchaseOrder;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;


/**
 *
 * @author Mis-Dark
 */
public class PurchaseOrderBD extends Conexion {

    
    public PurchaseOrderBD() {
    
    }

    public int newOrder(PurchaseOrder purchaseOrder) throws HibernateException {
        int order_id = 0;
        try { 
            iniciaOperacion(); 
            order_id = (int) sesion.save(purchaseOrder); 
            tx.commit(); 
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }finally { 
            sesion.close(); 
        }
        return order_id;
    }
    
    public PurchaseOrder getPurchaseOrder(int id) throws HibernateException {
        PurchaseOrder purchaseOrder = null;  
        try{
            this.iniciaOperacion();
            purchaseOrder = (PurchaseOrder) this.sesion.get(PurchaseOrder.class, id);
        } finally {
            this.sesion.close();
        }
        return purchaseOrder; 
    }
}