/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Entity.Provider;
import Modelos.ProviderDAO;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author surtialiss
 */
public class ProviderController {
    private ProviderDAO providerDao;
    
    public ProviderController(){
        this.providerDao = new ProviderDAO();
    }
    
    public DefaultTableModel getAll(){
        List<Provider> providers = this.providerDao.getAllProdider();
        DefaultTableModel model = new DefaultTableModel();
        
        model.addColumn("Id");
        model.addColumn("NIT");
        model.addColumn("Nombre");
        model.addColumn("Celular");
        
        for(Provider provider : providers){
            Vector row = new Vector();
            row.addElement(provider.getId());
            row.addElement(provider.getRut());
            row.addElement(provider.getName());
            row.addElement(provider.getPhoneNumber());
            row.add(new JButton());
            model.addRow(row);
        }
        return model;
    }
    
    public int save(String[] providerData){
        Provider provider = new Provider();
        provider.setRut(providerData[0]);
        provider.setName(providerData[1]);
        provider.setPhoneNumber(providerData[2]);
        
        int id = this.providerDao.save(provider);
        return id;
    }
    
    public Provider get(String nit){
        return this.providerDao.getByNit(nit);
    }
    
    public void getProvidersJComboBox(JComboBox clientes) {
        List<Provider> providers = this.providerDao.getAllProdider();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        for(Provider provider : providers){
            model.addElement(provider);
        }
        clientes.setModel(model);
        clientes.setSelectedIndex(0);
    }
    
    public Boolean update(Provider provider){
        return this.providerDao.updateClient(provider);
    }
}
