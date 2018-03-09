/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.GuardarDetalleFactura;
import Entity.Clientes;
import Entity.Detallefactura;
import Entity.Egresos;
import Entity.Facturas;
import Entity.Product;
import Vistas.Facturacion.FacturasView;
import java.sql.SQLException;
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

    private String table_name;
    public FacturasBD() {
        super();
        table_name = "facturas";
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
            sesion.close(); 
        }  
        return id; 
    }

    public String dateNowString() {
        Date fechaActual = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        return formato1.format(fechaActual);
    }
    
    private String hourNow(){
        Date fechaActual = new Date();
        SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm");
        Calendar cal1 = Calendar.getInstance();
        return formato2.format(fechaActual);
    }
    
    public void NuevoDetalle(DefaultTableModel detalles, int num_invoice) {
        GuardarDetalleFactura nuevo = new GuardarDetalleFactura(detalles, num_invoice);
        nuevo.start();
    }

    public Facturas findInvoice(String num_invoice) throws HibernateException {
        Facturas invoice = null;  
        try{
            this.iniciaOperacion();
            invoice = (Facturas) this.sesion.get(FacturasView.class, num_invoice);
        } finally {
            this.sesion.close();
        }
        return invoice; 
    }
    
    private List<Detallefactura> getDetailInvoice(String num_invoice){ 
        return (List<Detallefactura>) this.findInvoice(num_invoice).getDetallefacturas();
    }
    
    public DefaultTableModel ConsultarDetalleFactura(String NumeroFactura) {
        ClientesBD cliente = new ClientesBD();

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

    public DefaultTableModel FacturasCredito(String CedulaCliente) {
        ClientesBD cliente = new ClientesBD();

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);
        
        
        try {
            conexion("facturas");
            while (tabla.next()) {
                if (tabla.getString("Cedula").equalsIgnoreCase(CedulaCliente) && tabla.getBoolean("CreditoFactura")) {
                    Clientes consumer = cliente.getConsumerById(tabla.getString("Cedula"));
                    String[] fila = {
                        tabla.getString("NumeroFactura"),
                        consumer.getFullName(),
                        tabla.getString("Fecha"),
                        tabla.getString("Hora"),
                        tabla.getString("Total"),
                        Credito(tabla.getBoolean("CreditoFactura"))
                    };
                    facturas.addRow(fila);
                }
            }
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return facturas;
    }

    public void CambiarCredito(String cedula) {
        conexion("facturas");

        try {
            while (tabla.next()) {
                if ((cedula.equalsIgnoreCase(tabla.getString("Cedula"))) && (tabla.getBoolean("CreditoFactura"))) {
                    tabla.updateBoolean("CreditoFactura", false);
                    tabla.updateRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
        }
        close();
    }

    public DefaultTableModel FacturasDia() {
        ClientesBD cliente = new ClientesBD();
        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Fecha", "Total", "Egresos"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        EgresosDB egreso = new EgresosDB();

        try {
            conexion("facturas");
            long total = 0;
            tabla.first();
            String FechaActual = tabla.getString("Fecha");
            String FechaNueva;
            while (tabla.next()) {
                FechaNueva = tabla.getString("Fecha");
                
                if (FechaActual.equalsIgnoreCase(FechaNueva)) {
                    total += Long.parseLong(tabla.getString("Total"));
                } else {
                    String[] fila = {
                        FechaActual,
                        total + "",
                        egreso.ConsultarValor(FechaActual)
                    };
                    facturas.addRow(fila);
                    FechaActual = FechaNueva;
                    total = 0;
                }
            }
            String[] fila = {
                FechaActual,
                total + "",
                egreso.ConsultarValor(FechaActual)
            };
            facturas.addRow(fila);
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return facturas;
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
        try {
            conexion("facturas");
            while (tabla.next()) {
                if (tabla.getString("Fecha").equalsIgnoreCase(fecha)) {
                    Clientes consumer = cliente.getConsumerById(tabla.getString("Cedula"));
                    String[] fila = {
                        tabla.getString("NumeroFactura"),
                        consumer.getFullName(),
                        tabla.getString("Fecha"),
                        tabla.getString("Hora"),
                        tabla.getString("Total"),
                        Credito(tabla.getBoolean("CreditoFactura"))
                    };
                    facturas.addRow(fila);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        close();
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