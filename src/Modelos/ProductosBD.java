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
import java.util.List;
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
    private PurchaseOrderBD orderEnity;
    private InventoryBD inventoryEntity;
    

    public ProductosBD() {
        utilsSession = new Conexion();
        orderEnity = new PurchaseOrderBD();
        inventoryEntity = new InventoryBD();
        
    }

    private String saveProduct(Product product) throws HibernateException{
        String id = "0";
        if(this.getProduct(product.getBarCode()) != null){
            JOptionPane.showMessageDialog(null, "El producto ya existe.");
            return id;
        }
        
        try { 
            iniciaOperacion(); 
            id = (String) sesion.save(product); 
            tx.commit(); 
            this.sesion.flush();
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se pudo almacenar producto.");
            System.out.println(he.getMessage());
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
        if(pro[5] == "1"){
            product.setIsPayPoints(true);
        }else{
            product.setIsPayPoints(false);
        }
        
        String id = this.saveProduct(product);
        
        if(id != "0"){
            this.newInventory(pro, product);
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
        return orderEnity.newOrder(po);
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
    
    protected void newInventory(String[] pro, Product product){
        int order_id = newPurchaseOrder(pro);
        Inventory inventory = new Inventory();
        inventory.setPurchaseOrder(this.orderEnity.getPurchaseOrder(order_id));
        inventory.setProduct(product);
        inventory.setQuantity(Long.parseLong(pro[2]));
        inventory.setPrice(Long.parseLong(pro[3]));
        System.out.println(inventory.getProduct().getName());
        this.inventoryEntity.newInventory(inventory);
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
    
    public List<Product> getLikeNameProducts(String name) throws HibernateException {
        List<Product> products = null;  
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Product where name like :name")
                    .setParameter("name", "%"+name+"%");
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