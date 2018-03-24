/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Entity.Provider;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author surtialiss
 */
public class ProviderDAO extends Conexion{
    
    public List<Provider> getAllProdider(){
        List<Provider> clientesList = null;  
        try { 
            iniciaOperacion(); 
            clientesList = sesion.createQuery("from Provider ORDER BY name").list(); 
        }finally { 
            sesion.close(); 
        }  
        return clientesList; 
    }
    
    public int save(Provider provider) throws HibernateException
    { 
        int id = 0;  
        try { 
            iniciaOperacion(); 
            id = (int)sesion.save(provider); 
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
    
    public Provider getByNit(String nit) throws HibernateException{ 
        Provider provider = null;  
        try { 
            iniciaOperacion(); 
            provider = (Provider) sesion.createQuery("from Provider where rut = :rut")
                    .setParameter("rut", nit)
                    .uniqueResult();
        } finally { 
            sesion.close(); 
        }  
        return provider; 
    }
    
    public Boolean updateClient(Provider provider) throws HibernateException {
        Boolean isSave = false;
        try { 
            iniciaOperacion(); 
            sesion.update(provider); 
            tx.commit(); 
            isSave = true;
        } catch (HibernateException he) { 
            manejaExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        } 
        
        return isSave;
    }
}
