/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import javax.swing.JComboBox;

/**
 *
 * @author Caja1
 */
public class FacturaController {
    ProductosController productos;
    ClientesController clientes;
    
    public FacturaController(){
        productos = new ProductosController();
        clientes = new ClientesController();
    }
    
    public JComboBox CargarClientes(){
        return this.clientes.NombreCliente();
    }
    
    public String[][] Cliente_CedulaNombre(){
        return this.clientes.CedulaNombre();
    }
}
