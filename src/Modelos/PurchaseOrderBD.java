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
public class PurchaseOrderBD extends Thread {

    private Conexion order;
    private String table_name;
    public PurchaseOrderBD() {
        order = new Conexion();
        table_name = "purchase_order";
    }

    public int newOrder(String[] pro) {
        int order_id = 0;
        order_id = order.excecuteSQLAutoIncrement("Insert into "+table_name+" (provider_id, total, file) values ("
                + pro[0]+","+pro[1]+",'base')");
        return order_id;
    }
}