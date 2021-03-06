/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.*;
import java.util.Calendar;
import java.util.Date;
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
            this.sesion.flush();
            sesion.close(); 
        }  
        return id; 
    }
    
    public Clientes getConsumerById(String document_number) throws HibernateException{ 
        Clientes consumer = null;  
        try { 
            iniciaOperacion(); 
            consumer = (Clientes) sesion.get(Clientes.class,document_number);
        } finally { 
            sesion.close(); 
        }  
        return consumer; 
    }
    
    public void updateClient(Clientes clientebd) throws HibernateException {
        Boolean confirmar = false;
        try { 
            iniciaOperacion(); 
            sesion.update(clientebd); 
            tx.commit(); 
            confirmar = true;
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
        
        if (confirmar) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado");
        } else {
            JOptionPane.showMessageDialog(null, "No se logro actualizar");
        }
    }

    public List<CustomerPoint> getCustomerPoints(String customer){
        List<CustomerPoint> customerPoints = null;  
        try { 
            iniciaOperacion(); 
            customerPoints = sesion.createQuery("from CustomerPoint where client_id = :customer")
                    .setParameter("customer", customer).list(); 
        }finally { 
            sesion.close(); 
        }  
        return customerPoints;
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
            clientesList = sesion.createQuery("from Clientes ORDER BY document_number").list(); 
        }finally { 
            sesion.close(); 
        }  
        return clientesList; 
    }
    
    public List<Clientes> getConsumersHappyBirthDay() throws HibernateException {
        List<Clientes> clientesList = null;  
        Calendar now = Calendar.getInstance();
        try { 
            iniciaOperacion(); 
            
            clientesList = sesion.createQuery("from Clientes where DAY(date_birth) = :day and MONTH(date_birth) = :month")
                    .setParameter("day", now.get(Calendar.DAY_OF_MONTH))
                    .setParameter("month", now.get(Calendar.MONTH)+1)
                    .list(); 
        }finally { 
            sesion.close(); 
        }  
        return clientesList; 
    }
    
    public List<Clientes> getAllConsumersOrderByName() throws HibernateException {
        List<Clientes> clientesList = null;  
        try { 
            iniciaOperacion(); 
            clientesList = sesion.createQuery("from Clientes ORDER BY first_name").list(); 
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
            consumersNameDocument[i][1] = consumer.getFullName()+" CC: "+ consumer.getDocumentNumber();
            i++;
        }
        return consumersNameDocument;
    }
}