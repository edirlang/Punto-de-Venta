/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Entity.Product;
import Modelos.ProductosDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ProductosController extends ProductosDAO implements Runnable{
    
    
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
        Product product = this.getProduct(codigo.trim());
        if(product == null){
            JOptionPane.showMessageDialog(null, "No existe el producto");
        }
        return product;
    }
    
    public Product EditarProducto(String[] pro){
        Product product = this.findProduct(pro[0]);
        product.setName(pro[1]);
        product.setSalePrice(Integer.parseInt(pro[2]));
        product.setQuantity(Long.parseLong(pro[3]));
        if(pro[4] == "1"){
            product.setIsPayPoints(true);
        }else{
            product.setIsPayPoints(false);
        }
        return this.editar(product);
    }
    
}
