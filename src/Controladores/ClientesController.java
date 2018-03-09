/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Entity.Clientes;
import Modelos.ClientesBD;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ClientesController {
    ClientesBD clientesbd;
    public ClientesController(){
        super();
        this.clientesbd = new ClientesBD();
    }
    
    public void newConsumer(String[] cliente){
        int year = Integer.parseInt(cliente[5].split("-")[0]);
        int month = Integer.parseInt(cliente[5].split("-")[1]);
        int day = Integer.parseInt(cliente[5].split("-")[2]);
        Date date_birth = new Date(year, month, day);
        
        Clientes consumer = new Clientes();
        consumer.setDocumentNumber(cliente[0]);
        consumer.setFirstName(cliente[1]);
        consumer.setLastName(cliente[2]);
        consumer.setPhoneNumber(cliente[3]);
        consumer.setAddress(cliente[4]);
        consumer.setDateBirth(date_birth);
        consumer.setIsCredit(this.clientesbd.Credito(cliente[6]));
        String id = this.clientesbd.saveConsumer(consumer);
        if(id == "0"){
            JOptionPane.showMessageDialog(null, "No se logro registrar el cliente");
        }else{
            JOptionPane.showMessageDialog(null, "Se ha creado el cliente");
        }
    }
    
    public DefaultTableModel Clientes(){
        List<Clientes> consumers = this.clientesbd.getAllConsumers();
        
        DefaultTableModel modelConsumers = new DefaultTableModel();
        String[] columnas = {"Cedula", "Nombre", "Apellido", "Telefono","Fecha de Nacimento", "Credito"};
        
        modelConsumers.addColumn(columnas[0]);
        modelConsumers.addColumn(columnas[1]);
        modelConsumers.addColumn(columnas[2]);
        modelConsumers.addColumn(columnas[3]);
        modelConsumers.addColumn(columnas[4]);
        modelConsumers.addColumn(columnas[5]);
        
        
        for(Clientes consumer : consumers){
            
            String[] fila = {
                consumer.getDocumentNumber(),
                consumer.getFirstName(),
                consumer.getLastName(),
                consumer.getPhoneNumber(),
                consumer.getDateBirth().toString(),
                consumer.getTextIsCredit()
            };
            modelConsumers.addRow(fila);
        }
        return modelConsumers;
    }
    
    public void Actualizar(){
        
    }
    
    public void Eliminar(){
        
    }
    
    public Clientes getConsumerById(String cedula){
        return this.clientesbd.getConsumerById(cedula);
    }
    
    public void EditarCliente(String[] cliente){
        Clientes costumer = new Clientes();
        this.clientesbd.editar(costumer);
    }
}