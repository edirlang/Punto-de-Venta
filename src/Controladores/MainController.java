/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.Config;
import Modelos.Conexion;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author surtialiss
 */
public class MainController {
    
    Conexion con = new Conexion();
    
    public List<Config> getAllConfig(){
        List<Config> configs = null;  
        try { 
            this.con.iniciaOperacion(); 
            configs = this.con.sesion.createQuery("from Config").list(); 
        }finally { 
            this.con.sesion.close(); 
        }  
        return configs; 
    }
    
    public Config getConfigByName(String name) throws HibernateException{ 
        Config config = null;  
        try { 
            this.con.iniciaOperacion(); 
            config = (Config) this.con.sesion.createQuery("from Config where name = :name")
                    .setParameter("name", name)
                    .uniqueResult();
        } finally { 
            this.con.sesion.close(); 
        }  
        return config; 
    }
}
