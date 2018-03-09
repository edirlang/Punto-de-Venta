/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.Inventory;
import Entity.Product;
import Entity.Provider;
import Entity.PurchaseOrder;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Mis-Dark
 */
public class ProductosBD extends Conexion {

    private Conexion utilsSession;
    private PurchaseOrderBD order;
    private InventoryBD inventory;
    private String table_name;

    public ProductosBD() {
        utilsSession = new Conexion();
        order = new PurchaseOrderBD();
        inventory = new InventoryBD();
        table_name = "product";
    }

    private String saveProduct(Product product) throws HibernateException{
        String id = "0";
        try { 
            iniciaOperacion(); 
            id = (String) sesion.save(product); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
            throw he; 
        }finally { 
            sesion.close(); 
        }
        
        return id;
    }
    
    protected void nuevo(String[] pro) throws HibernateException {
        Product product = new Product();
        product.setBarCode(pro[0]);
        product.setName(pro[1]);
        product.setSalePrice(Integer.parseInt(pro[2]));
        product.setQuantity(Long.parseLong(pro[4]));
        String id = this.saveProduct(product);
        newInventory(pro);
        
        if(id != "0"){
            JOptionPane.showMessageDialog(null, "Producto Creado");
        }else{
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto");
        }
    }
    
    protected int newPurchaseOrder(String[] pro){
        PurchaseOrder po = new PurchaseOrder();
        double total = (Double.parseDouble(pro[4]) * Double.parseDouble(pro[3]));
        
        po.setProvider(this.getProvider(1));
        po.setTotal(total+"");
        return order.newOrder(po);
    }
    
    private Provider getProvider(int id){
        Provider provider = new Provider();  
        try { 
            iniciaOperacion(); 
            provider = (Provider) sesion.get(Provider.class, id);
        } finally { 
            sesion.close(); 
        }  
        return provider; 
    }
    
    protected void newInventory(String[] pro){
        int order_id = newPurchaseOrder(pro);
        Inventory inventory = new Inventory();
        inventory.setPurchaseOrder(this.order.getPurchaseOrder(order_id));
        inventory.setProduct(this.getProduct(pro[0]));
        inventory.setQuantity(Long.parseLong(pro[2]));
        inventory.setPrice(Long.parseLong(pro[3]));
        
        this.inventory.newInventory(inventory);
    }
    
    public Product getProduct(String bar_code) throws HibernateException {
        Product product = null;  
        try{
            this.iniciaOperacion();
            product = (Product) this.sesion.get(Product.class, bar_code);
        } finally {
            this.sesion.close();
        }
        return product; 
    }

    protected Product editar(Product product) throws HibernateException {
        Boolean confirmar=false;
        try { 
            iniciaOperacion(); 
            sesion.update(product); 
            tx.commit(); 
            confirmar = true;
        } catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar producto");
        } finally { 
            sesion.close(); 
        } 
        return product;
    }

    protected void eliminar(String bar_code) throws HibernateException {
        Product product = this.getProduct(bar_code);
        try { 
            iniciaOperacion(); 
            sesion.delete(product); 
            tx.commit(); 
        } catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "Error al eleminar el producto.");
        } finally { 
            sesion.close(); 
        }        
    }

    public List<Product> getAllProducts() throws HibernateException {
        List<Product> products = null;  
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Product Order BY quantity ASC");
            products = query.list();
        } finally { 
            sesion.close(); 
        }  
        return products; 
    }
    
    protected DefaultTableModel todos() {
        DefaultTableModel productos = new DefaultTableModel();
        String[] columnas = {"Codigo", "Descripción", "Precio", "Cantidad"};
        productos.addColumn(columnas[0]);
        productos.addColumn(columnas[1]);
        productos.addColumn(columnas[2]);
        productos.addColumn(columnas[3]);
        
        for(Product product : this.getAllProducts()){
            String[] fila = {
                product.getBarCode(),
                product.getName(),
                product.getSalePrice()+"",
                product.getQuantity()+""
            };
            productos.addRow(fila);
        }
        
        return productos;
    }
    
    public void RestarProducto(String bar_code, int vendidos) throws HibernateException{
        Product product = this.getProduct(bar_code);
        product.setQuantity(product.getQuantity() - vendidos);
        this.editar(product);
    }
}