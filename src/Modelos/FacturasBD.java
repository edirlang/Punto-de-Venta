/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.GuardarDetalleFactura;
import Entity.Clientes;
import Entity.CustomerPoint;
import Entity.Detallefactura;
import Entity.Egresos;
import Entity.Facturas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Caja1
 */
public class FacturasBD extends Conexion {

    public FacturasBD() {
        super();
    }

    public int newInvoice(Facturas invoice) throws HibernateException {
        int id = 0;  
        try { 
            iniciaOperacion(); 
            id = (int)sesion.save(invoice); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            JOptionPane.showMessageDialog(null, "No se puedo crear la factura.");
            throw he; 
        }finally { 
            this.sesion.flush();
            sesion.close(); 
        }  
        return id; 
    }
    
    public void saveDescountPoints(Clientes customer, int points){
       CustomerPoint point = new CustomerPoint();
       point.setCustomer(customer);
       point.setQuantity(points);
       this.saveCustomerPoints(point);
    }
    
    public void saveCustomerPoints(CustomerPoint point){
        try {
            this.iniciaOperacion(); 
            this.sesion.save(point); 
            this.tx.commit(); 
        }catch(HibernateException he) { 
            this.manejaExcepcion(he);
        }finally { 
            this.sesion.close(); 
        }  
    }
    
    public void saveDetailInvoice(DefaultTableModel detalles, int num_invoice) {
        GuardarDetalleFactura nuevo = new GuardarDetalleFactura(detalles, num_invoice);
        nuevo.start();
    }

    public Facturas findInvoice(String num_invoice) throws HibernateException {
        Facturas invoice = null;  
        try{
            this.iniciaOperacion();
            invoice = (Facturas) this.sesion.get(Facturas.class, Integer.parseInt(num_invoice));
        } finally {
            this.sesion.close();
        }
        return invoice; 
    }
    
    private List<Detallefactura> getDetailInvoice(String num_invoice){ 
       List<Detallefactura> details = null;
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Detallefactura WHERE NumeroFactura = :invoice")
                    .setParameter("invoice", num_invoice);
            details = query.list();
        } finally { 
            this.sesion.flush();
            sesion.close(); 
        }  
        return details;
    }
    
    public DefaultTableModel ConsultarDetalleFactura(String NumeroFactura) {
        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Codigo", "Descripcion", "Cantidad", "Valor Unidad", "SubTotal"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        
        for(Detallefactura detail: this.getDetailInvoice(NumeroFactura)){
            String[] fila = {
                detail.getProduct().getBarCode(),
                detail.getProduct().getName(),
                detail.getCantidad()+"",
                detail.getValor()+"",
                detail.getTotal()+""
            };
            facturas.addRow(fila);
        }
        return facturas;
    }

    public DefaultTableModel todos() {
        DefaultTableModel facturas = new DefaultTableModel();
        FacturasAll f = new FacturasAll(facturas);
        f.start();
        return f.facturas;
    }
    
    public List<Facturas> getAllInvoices() throws HibernateException {
        List<Facturas> invoices = null;  
        try { 
            this.iniciaOperacion(); 
            Query query = this.sesion.createQuery("from Facturas ORDER BY NumeroFactura DESC");
            invoices = query.list();
        } finally { 
            this.sesion.flush();
            this.sesion.close(); 
        }  
        return invoices; 
    }
    
    private List<Facturas> getInvoicesConsumerCredit(Clientes consumer){
        List<Facturas> invoices = null;
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Facturas WHERE cedula = :client and CreditoFactura = true")
                    .setParameter("client", consumer);
            invoices = query.list();
        } finally { 
            this.sesion.flush();
            sesion.close(); 
        }  
        return invoices; 
    }
    
    public DefaultTableModel FacturasCredito(String CedulaCliente) {
        ClientesBD cliente = new ClientesBD();
        Clientes consumer = cliente.getConsumerById(CedulaCliente);

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);
        
        List<Facturas> invoices = this.getInvoicesConsumerCredit(consumer);
        for(Facturas invoice : invoices){
            String[] fila = {
                invoice.getNumeroFactura()+"",
                consumer.getFullName(),
                invoice.getDateText(),
                invoice.getHourText(),
                invoice.getTotal()+"",
                invoice.getCreditoFacturaText()
            };
            facturas.addRow(fila);
        }
        return facturas;
    }

    private void update(Facturas invoice) throws HibernateException {
        Boolean confirmar = false;
        try { 
            iniciaOperacion(); 
            sesion.update(invoice); 
            tx.commit(); 
            confirmar = true;
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
        } finally { 
            this.sesion.flush();
            sesion.close(); 
        }
    }
    
    public void CambiarCredito(String cedula) {
        ClientesBD clienteEntity = new ClientesBD();
        
        List<Facturas> invoices = this.getInvoicesConsumerCredit(clienteEntity.getConsumerById(cedula));
        for(Facturas invoice : invoices){
            invoice.setCreditoFactura(false);
            this.update(invoice);
        }
    }
    
    private List<Object[]> getInnvoicesDay(){
        List<Object[]> invoices = null;
        try { 
            iniciaOperacion(); 
            Query query = sesion.createSQLQuery("SELECT Fecha, COUNT(*) as quantity, sum(total) as total FROM facturas GROUP BY Fecha ORDER BY Fecha");
            invoices = query.list();
        } finally { 
            this.sesion.flush();
            sesion.close(); 
        }  
        return invoices; 
    }

    public DefaultTableModel FacturasDia() {

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Fecha", "Total", "Egresos"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        EgresosDB egreso = new EgresosDB();
        
        List<Object[]> invoicesDay = this.getInnvoicesDay();
        for(Object[] invoice : invoicesDay){
            String[] fila = {
                invoice[0].toString(),
                invoice[2].toString(),
                egreso.ConsultarValor(invoice[0].toString())
            };
            facturas.addRow(fila);
        }
        return facturas;
    }
    
    private List<Facturas> getInvoicesDate(String date){
        List<Facturas> invoices = null;
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("FROM Facturas where Fecha = :date ORDER BY Fecha")
                    .setParameter("date", date);
                    
            invoices = query.list();
        } finally { 
            sesion.close(); 
        }  
        return invoices; 
    }
    public DefaultTableModel FacturasDiaDetalle(String fecha) {
        ClientesBD cliente = new ClientesBD();

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);
        
        for(Facturas invoice : this.getInvoicesDate(fecha)){
            String[] fila = {
                invoice.getNumeroFactura()+"",
                invoice.getClientes().getFullName(),
                invoice.getDateText(),
                invoice.getHourText(),
                invoice.getTotal()+"",
                invoice.getCreditoFacturaText()
            };
            facturas.addRow(fila);
        }
        return facturas;
    }

    private long getTotalInvoicesDate(String date) throws HibernateException{
        long total = 0;
        List<Facturas> invoices = null;
        try { 
            this.iniciaOperacion(); 
            Query query = this.sesion.createQuery("from Facturas where Fecha = :date")
                    .setParameter("date", date);
            invoices = query.list();
        } finally { 
            this.sesion.close(); 
        }  
        for(Facturas invoice : invoices){
            total += invoice.getTotal();
        }
        return total;
    }
    
    private long getTotalEgresosDate(String date) throws HibernateException{
        long total = 0;
        List<Egresos> egresos = null;
        try { 
            this.iniciaOperacion(); 
            Query query = this.sesion.createQuery("from Egresos where Fecha BETWEEN '"+date+" 00:00:00:000000' AND '"+date+" 23:59:59:999999'")
                    ;
            egresos = query.list();
        } finally { 
            this.sesion.close(); 
        }  
        for(Egresos invoice : egresos){
            total += invoice.getValor();
        }
        return total;
    }
    
    public String[] ReporteDia(String Fecha) {
        String[] datos = null;
        
        long total = this.getTotalInvoicesDate(Fecha);
        long egresos = this.getTotalEgresosDate(Fecha);
        
        datos = new String[]{
            total + "",
            egresos + "",
            (total - egresos) + ""
        };
        
        return datos;
    }
}