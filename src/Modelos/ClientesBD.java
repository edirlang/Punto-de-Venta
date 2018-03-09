/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.*;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;


/**
 *
 * @author Caja1
 */
public class ClientesBD extends Conexion {

    public ClientesBD() {
        super();
    }

    public String saveConsumer(Clientes consumer) throws HibernateException
    { 
        String id = "0";  
        try { 
            iniciaOperacion(); 
            id = (String)sesion.save(consumer); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            throw he; 
        }finally { 
            sesion.close(); 
        }  
        return id; 
    }
    
    public Clientes getConsumerById(String document_number) throws HibernateException{ 
        Clientes consumer = new Clientes();  
        try { 
            iniciaOperacion(); 
            consumer = (Clientes) sesion.get(Clientes.class,document_number);
            
        } finally { 
            sesion.close(); 
        }  
        return consumer; 
    }
    
    public void editar(Clientes clientebd) throws HibernateException {
        Boolean confirmar = false;
        try 
        { 
            iniciaOperacion(); 
            sesion.update(clientebd); 
            tx.commit(); 
            confirmar = true;
        } catch (HibernateException he) 
        { 
            manejaExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
        
        if (confirmar) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado");
        } else {
            JOptionPane.showMessageDialog(null, "No se logro actualizar");
        }
    }

    public void eliminar(Clientes consumer) {
        
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(consumer); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            JOptionPane.showMessageDialog(null, "Error al eleminar el cliente.");
        } finally 
        { 
            sesion.close(); 
        }
    }

    public List<Clientes> getAllConsumers() throws HibernateException {
        List<Clientes> clientesList = null;  
        try { 
            iniciaOperacion(); 
            clientesList = sesion.createQuery("from Clientes").list(); 
        }finally { 
            sesion.close(); 
        }  
        return clientesList; 
    }
    
    public String[][] getConsumerNameDocument() {
        List<Clientes> consumers = this.getAllConsumers();
        
        String[][] consumersNameDocument = new String[consumers.size()][2];
        int i = 0;
        for(Clientes consumer : consumers){
            consumersNameDocument[i][0] = consumer.getDocumentNumber();
            consumersNameDocument[i][1] = consumer.getFirstName()+" "+consumer.getLastName();
            i++;
        }
        return consumersNameDocument;
    }
}