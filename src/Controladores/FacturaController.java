/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.FacturasBD;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;

/**
 *
 * @author Caja1
 */
public class FacturaController extends FacturasBD{

    ProductosController productos;
    ClientesController clientes;
    
    public FacturaController() {
        productos = new ProductosController();
        clientes = new ClientesController();
    }

    public void CargarClientes(JComboBox clientes) {
        this.clientes.NombreCliente(clientes);
    }

    public String[][] Cliente_CedulaNombre() {
        return this.clientes.CedulaNombre();
    }

    
    public String Fecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        System.out.println(formato1.format(fechaActual));
       return formato1.format(fechaActual);
    }
    
    public String[] BuscarProducto(String codigo) {
        return productos.consultar(codigo);
    }
    
    public int NumeroFactura(){
        return NumeroUltimaFactura();
    }
    
    public String CreditoCliente(String cedula){
        String[] cliente= clientes.consultar(cedula);
        if(cliente.length > 2){
            return cliente[5];
        }else{
            return "No";
        }
    }
}
