/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.Clientes;
import Entity.Facturas;
import Entity.Product;
import Modelos.ClientesBD;
import Modelos.FacturasBD;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class FacturaController{

    private ProductosController productos;
    private ClientesBD clientes;
    private FacturasBD invoicesBD;
    
    public FacturaController() {
        productos = new ProductosController();
        clientes = new ClientesBD();
        invoicesBD = new FacturasBD();
    }

    public void CargarClientes(JComboBox clientes) {
        String[][] consumers = this.clientes.getConsumerNameDocument();
        for(int i = 0; i < consumers.length; i++){
            clientes.addItem(consumers[i][1]);
           
        }
    }
    
    public String[][] getConsumerDocumentName() {
        return this.clientes.getConsumerNameDocument();
    }
    
    public String Fecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        System.out.println(formato1.format(fechaActual));
       return formato1.format(fechaActual);
    }
    
    public Product BuscarProducto(String codigo) {
        Product product = productos.getProduct(codigo);
        if(product == null){
            JOptionPane.showMessageDialog(null,"Producto no registrado.");
        }
        return product;
    }
    
    public int newInvoice(String[] data_invoice){
        System.out.println(data_invoice[2]);
        Facturas invoice = new Facturas();
        invoice.setFecha(new Date());
        invoice.setHora(new Date());
        invoice.setTotal(Long.parseLong(data_invoice[1]));
        invoice.setCreditoFactura(this.invoicesBD.Credito(data_invoice[2]));
        
        int num_invoice = this.invoicesBD.newInvoice(invoice);
        return num_invoice;
    }
    
    public void saveDetailsInvoice(DefaultTableModel detalles, int num_invoice){
        
    }
    
    public String CreditoCliente(String cedula){
        Clientes consumer = clientes.getConsumerById(cedula);
        return consumer.getTextIsCredit();
    }
    
    public String[] ReporteDia(String Fecha) {
        return this.invoicesBD.ReporteDia(Fecha);
    }
    
}
