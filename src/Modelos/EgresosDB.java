/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

import Entity.Egresos;
import Entity.Usuarios;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Caja1
 */
public class EgresosDB extends Conexion{
    public EgresosDB(){
        super();
    }
    
    public void NuevoEgreso(Egresos egreso){
        try { 
            iniciaOperacion(); 
            sesion.save(egreso); 
            tx.commit(); 
        }catch(HibernateException he) { 
            manejaExcepcion(he);
            throw he; 
        }finally { 
            sesion.close(); 
        }  
    }
    
    public String ConsultarValor(String Fecha) {
        long total = 0;
        List<Egresos> egresos = null;  
        try { 
            iniciaOperacion(); 
            Query query = sesion.createQuery("from Egresos WHERE Fecha Between :start AND :end")
                    .setParameter("start", Fecha+" 00:00:00")
                    .setParameter("end", Fecha+" 23:59:59");
            egresos = query.list();
            for(Egresos egreso: egresos){
                total += egreso.getValor();
            }
        } finally { 
            sesion.close(); 
        }  
        return total+""; 
    }
}