/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Facturas;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Caja1
 */
public class FacturaController extends Facturas{

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

    public void Hora(JTextField hora, int dia) {
        Hora horaActual = new Hora(hora, dia);
        horaActual.start();
    }

    public void Fecha(JTextField fecha) {
        Date fechaActual = new Date();

        SimpleDateFormat formato1 = new SimpleDateFormat("dd/MM/yyy");

        Calendar cal1 = Calendar.getInstance();

        fecha.setText(formato1.format(fechaActual));
        fecha.setEditable(false);
    }

    public String[] BuscarProducto(String codigo) {
        return productos.consultar(codigo);
    }
    
    public int NumeroFactura(){
        return NumeroUltimaFactura();
    }
}
