/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Entity.Product;
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
    
    public Product findProduct(String codigo){
        return this.getProduct(codigo);
    }
    
    public void EditarProducto(String[] pro){
        Product product = this.findProduct(pro[0]);
        product.setName(pro[0]);
        product.setSalePrice(Integer.parseInt(pro[2]));
        product.setQuantity(Long.parseLong(pro[3]));
        this.editar(product);
    }
    
}
