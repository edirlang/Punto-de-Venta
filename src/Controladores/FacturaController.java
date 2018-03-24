/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.Clientes;
import Entity.Config;
import Entity.CustomerPoint;
import Entity.Facturas;
import Entity.Product;
import Modelos.ClientesBD;
import Modelos.FacturasBD;
import Vistas.MenuUsuario;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class FacturaController{

    private ProductosController productos;
    public ClientesBD clientes;
    private FacturasBD invoicesBD;
    
    public FacturaController() {
        productos = new ProductosController();
        clientes = new ClientesBD();
        invoicesBD = new FacturasBD();
    }

    public void CargarClientes(JComboBox clientes) {
        List<Clientes> consumers = this.clientes.getAllConsumers();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(Clientes customer : consumers){
            model.addElement(customer);
        }
        clientes.setModel(model);
        clientes.setSelectedIndex(0);
    }
    
    public String[][] getConsumerDocumentName() {
        return this.clientes.getConsumerNameDocument();
    }
    
    public Product BuscarProducto(String codigo) {
        Product product = productos.getProduct(codigo.trim());
        if(product == null){
            JOptionPane.showMessageDialog(null,"Producto no registrado.");
        }
        return product;
    }
    
    public int newInvoice(String[] data_invoice){
        Clientes consumer = this.clientes.getConsumerById(data_invoice[0]);
        Facturas invoice = new Facturas();
        invoice.setClientes(consumer);
        invoice.setFecha(new Date());
        invoice.setHora(new Date());
        invoice.setTotal(Long.parseLong(data_invoice[1]));
        invoice.setCreditoFactura(this.invoicesBD.Credito(data_invoice[2]));
        
        int num_invoice = this.invoicesBD.newInvoice(invoice);
        if(num_invoice != 0){
            this.newCustomerPoints(invoice);
        }
        if(Integer.parseInt(data_invoice[3]) < 0){
            this.invoicesBD.saveDescountPoints(invoice.getClientes(), Integer.parseInt(data_invoice[3]));
        }
        return num_invoice;
    }
    
    private void newCustomerPoints(Facturas invoice){
        if(invoice.getClientes().getIsPoints()){
            Config config_points = MenuUsuario.mainCtrl.getConfigByName("price_point");
            CustomerPoint point = new CustomerPoint();
            point.setCustomer(invoice.getClientes());
            point.setQuantity((int) (invoice.getTotal()/Integer.parseInt(config_points.getValue())));
            this.invoicesBD.saveCustomerPoints(point);
        }
    }
    
    public void saveDetailsInvoice(DefaultTableModel detalles, int num_invoice){
        this.invoicesBD.saveDetailInvoice(detalles, num_invoice);
    }
    
    public String CreditoCliente(String cedula){
        Clientes consumer = clientes.getConsumerById(cedula);
        return consumer.getTextIsCredit();
    }
    
    public String[] ReporteDia(String Fecha) {
        return this.invoicesBD.ReporteDia(Fecha);
    }
    
    public String Fecha() {
       Date fechaActual = new Date();
       SimpleDateFormat formato1 = new SimpleDateFormat("yyy-MM-dd");
       Calendar cal1 = Calendar.getInstance(); 
       return formato1.format(fechaActual);
    }
    
    public List<Product> getAllProducts(){
        return this.productos.getAllProductsOrderByName();
    }
    
    public Long getPointsCliente(String cedula){
        long totalPoints = 0;
        for(CustomerPoint point : this.clientes.getCustomerPoints(cedula)){
            totalPoints += point.getQuantity();
        }
        return totalPoints;
    }
}
