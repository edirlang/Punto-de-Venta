/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ClientesBD extends Conexion {

    public ClientesBD() {
        super();
    }

    public void nuevo(String[] clientebd) {
        try {
            conexion("clientes");
            tabla.moveToInsertRow();
            tabla.updateString("document_number", clientebd[0]);
            tabla.updateString("first_name", clientebd[1]);
            tabla.updateString("last_name", "" + clientebd[2]);
            tabla.updateString("phone_number", "" + clientebd[3]);
            tabla.updateString("address", "" + clientebd[4]);
            tabla.updateString("date_birth", "" + clientebd[5]);
            tabla.updateBoolean("isCredit", Credito(clientebd[6]));
            tabla.insertRow();
            JOptionPane.showMessageDialog(null, "El cliente a sido Registrado");
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro registrar el cliente "+ex.getMessage());
        }
    }

    public String[] consultar(String cedula) {
        String[] fila = null;
        try {
            conexion("clientes where document_number = '"+cedula+"'");
            while (tabla.next()) {
                if (tabla.getString("document_number").equalsIgnoreCase(cedula)) {
                    fila = new String[]{
                        tabla.getString("document_number"),
                        tabla.getString("first_name"),
                        tabla.getString("last_name"),
                        tabla.getString("phone_number"),
                        tabla.getString("address"),
                        tabla.getString("date_birth"),
                        Credito(tabla.getBoolean("isCredit"))
                    };
                    return fila;
                }
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta "+ex.getMessage());
        }
        if (fila == null) {
            fila = new String[]{"0","0"};
        }
        close();
        return fila;
    }

    public void editar(String[] clientebd) {
        Boolean confirmar = false;
        try {
            conexion("clientes");
            while (tabla.next()) {
                if (tabla.getString("document_number").equalsIgnoreCase(clientebd[0])) {
                    tabla.updateString("document_number", clientebd[0]);
                    tabla.updateString("first_name", clientebd[1]);
                    tabla.updateString("last_name", "" + clientebd[2]);
                    tabla.updateString("phone_number", "" + clientebd[3]);
                    tabla.updateString("address", "" + clientebd[4]);
                    tabla.updateBoolean("isCredit", Credito(clientebd[5]));
                    tabla.updateRow();
                    confirmar = true;
                }
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar los datos del cliente" + ex);
        }
        if (confirmar) {
            JOptionPane.showMessageDialog(null, "Cliente actualizado");
        } else {
            JOptionPane.showMessageDialog(null, "No se logro actualizar");
        }
    }

    public void eliminar(String cedula) {
        try {
            conexion("clientes");
            while (tabla.next()) {
                if (tabla.getString("document_number").equalsIgnoreCase(cedula)) {
                    tabla.deleteRow();
                    JOptionPane.showMessageDialog(null, "El cliente a sido eliminado");
                }
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eleminar el cliente " + ex);
        }
    }

    public DefaultTableModel todos() {
        DefaultTableModel clientes = new DefaultTableModel();
        String[] columnas = {"Cedula", "Nombre", "Apellido", "Telefono","Fecha de Nacimento", "Credito"};
        clientes.addColumn(columnas[0]);
        clientes.addColumn(columnas[1]);
        clientes.addColumn(columnas[2]);
        clientes.addColumn(columnas[3]);
        clientes.addColumn(columnas[4]);
        clientes.addColumn(columnas[5]);

        try {
            conexion("clientes ORDER BY first_name");
            while (tabla.next()) {
                String credit = "Si";
                if(tabla.getBoolean("isCredit") == false){
                    credit = "No";
                }
                String[] fila = {
                    tabla.getString("document_number"),
                    tabla.getString("first_name"),
                    tabla.getString("last_name"),
                    tabla.getString("phone_number"),
                    tabla.getString("date_birth"),
                    credit
                };
                clientes.addRow(fila);
             }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return clientes;
    }

    public void NombreCliente(JComboBox nombre) {
        String[][] Clientes = this.CedulaNombre();
        for(int i = 0; i < Clientes.length; i++){
            nombre.addItem(Clientes[i][1]);
           
        }
    }

    public String[][] CedulaNombre() {
        String[][] clientes = new String[TamañoTabla()][2];
        try {
            conexion("clientes");
            int fila=0;
            while (tabla.next()) {
                clientes[fila][0]=tabla.getString("document_number");
                clientes[fila][1] = tabla.getString("first_name")+" "+ tabla.getString("last_name");
                fila++;
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return clientes;
    }
    
    private int TamañoTabla(){
        int clientes = 0;
        try {
            conexion("clientes");
            while (tabla.next()) {
                clientes++;
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return clientes;
    }
}