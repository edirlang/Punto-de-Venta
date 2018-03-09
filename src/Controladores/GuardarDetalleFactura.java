/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.Detallefactura;
import Entity.Facturas;
import Entity.Product;
import Modelos.Conexion;
import Modelos.FacturasBD;
import Modelos.ProductosBD;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

/**
 *
 * @author Caja1
 */
public class GuardarDetalleFactura extends Thread {

    Conexion conexion;
    DefaultTableModel detalles;
    int num_invoice;
    ProductosBD producto;
    FacturasBD facturabd;

    public GuardarDetalleFactura(DefaultTableModel detalles, int num_invoice) {
        this.detalles = detalles;
        this.num_invoice = num_invoice;
        producto = new ProductosBD();
        facturabd = new FacturasBD();
        conexion = new Conexion();
    }

    @Override
    public void run() {
        Facturas invoice = this.facturabd.findInvoice(num_invoice+"");
        for (int i = 0; i < detalles.getRowCount(); i++) {
            Product product = this.producto.getProduct(detalles.getValueAt(i, 0).toString());
            Detallefactura detail = new Detallefactura();
            detail.setFacturas(invoice);
            detail.setProduct(product);
            detail.setCantidad(Double.parseDouble(detalles.getValueAt(i, 2).toString()));
            detail.setValor(Double.parseDouble(detalles.getValueAt(i, 3).toString()));
            detail.setTotal(Double.parseDouble(detalles.getValueAt(i, 4).toString()));
            this.saveDetail(detail);
            producto.RestarProducto(detalles.getValueAt(i, 0).toString(), Integer.parseInt(detalles.getValueAt(i, 2).toString()));
        }
    }
    
    private void saveDetail(Detallefactura detail){
        try { 
            this.conexion.iniciaOperacion(); 
            this.conexion.sesion.save(detail); 
            this.conexion.tx.commit(); 
        }catch(HibernateException he) { 
            this.conexion.manejaExcepcion(he);
        }finally { 
            this.conexion.sesion.close(); 
        }  
    }
}
