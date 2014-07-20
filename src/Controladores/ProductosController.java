/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Modelos.ProductosBD;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ProductosController extends ProductosBD implements Runnable{
    
    
    public ProductosController(){
        super();
    }
    
    public void nuevoPro(String[] producto){
        
        this.nuevo(producto);
    }
    public DefaultTableModel Productos(){
        return todos();
    }
    
    public void Actualizar(){
        
    }
    
    public void Eliminar(){
        
    }
    
    public String[] Buscar(String codigo){
        return this.consultar(codigo);
    }
    
    public void EditarProducto(String[] pro){
        this.editar(pro);
    }
}
