/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.CustomerPoint;
import Entity.Detallefactura;
import Entity.Facturas;
import Entity.Product;
import Modelos.Conexion;
import Modelos.FacturasBD;
import Modelos.ProductosDAO;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;

/**
 *
 * @author Caja1
 */
public class GuardarDetalleFactura extends Conexion implements Runnable {
    DefaultTableModel detalles;
    int num_invoice;
    ProductosDAO producto;
    FacturasBD facturabd;

    public GuardarDetalleFactura(DefaultTableModel detalles, int num_invoice) {
        this.detalles = detalles;
        this.num_invoice = num_invoice;
        producto = new ProductosDAO();
        facturabd = new FacturasBD();
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
            this.iniciaOperacion(); 
            this.sesion.save(detail); 
            this.tx.commit(); 
        }catch(HibernateException he) { 
            this.manejaExcepcion(he);
        }finally { 
            this.sesion.flush();
            this.sesion.close(); 
        }  
    }
    
}
