/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;


import Entity.Facturas;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class FacturasAll extends Thread{
    DefaultTableModel facturas;
    FacturasBD facturasEntity;
    Conexion con;
    public FacturasAll(DefaultTableModel tableModel){
        this.facturas = tableModel;
        facturasEntity = new FacturasBD();
        con = new Conexion();
    }
    @Override
    public void run(){
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);

        List<Facturas> invoices = this.facturasEntity.getAllInvoices();
        for(Facturas invoice : invoices){
            
            String[] fila = {
                invoice.getNumeroFactura()+"",
                invoice.getClientes().getFullName(),
                invoice.getDateText(),
                invoice.getHourText(),
                invoice.getTotal()+"",
                invoice.getCreditoFacturaText()
            };
            this.facturas.addRow(fila);
        }
    }
}