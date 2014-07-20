/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Modelos.ClientesBD;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ClientesController extends ClientesBD{
    public ClientesController(){
        super();
    }
    
    public void NuevoCliente(String[] cliente){
        this.nuevo(cliente);
    }
    public DefaultTableModel Clientes(){
        return todos();
    }
    
    public void Actualizar(){
        
    }
    
    public void Eliminar(){
        
    }
    
    public String[] Buscar(String cedula){
        return this.consultar(cedula);
    }
    
    public void EditarCliente(String[] cliente){
        this.editar(cliente);
    }

//   
}