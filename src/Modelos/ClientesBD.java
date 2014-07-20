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
            tabla.updateString("Cedula", clientebd[0]);
            tabla.updateString("Nombre", clientebd[1]);
            tabla.updateString("Apellido", "" + clientebd[2]);
            tabla.updateString("Telefono", "" + clientebd[3]);
            tabla.updateString("Direccion", "" + clientebd[4]);
            tabla.updateBoolean("CreditoCliente", Credito(clientebd[5]));
            tabla.insertRow();
            JOptionPane.showMessageDialog(null, "El cliente a sido Registrado");
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se logro registrar el cliente");
        }
    }

    public String[] consultar(String cedula) {
        String[] fila = null;
        try {
            conexion("clientes");
            while (tabla.next()) {
                if (tabla.getString("Cedula").equalsIgnoreCase(cedula)) {
                    fila = new String[]{
                        tabla.getString("Cedula"),
                        tabla.getString("Nombre"),
                        tabla.getString("Apellido"),
                        tabla.getString("Telefono"),
                        tabla.getString("Dirrecion"),
                        Credito(tabla.getBoolean("PrecioPromocion"))
                    };
                    return fila;
                }
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo realizar la consulta");
        }
        if (fila == null) {
            JOptionPane.showMessageDialog(null, "El cliente no ha sido Registrado");
        }
        return fila;
    }

    public void editar(String[] clientebd) {
        Boolean confirmar = false;
        try {
            conexion("clientes");
            while (tabla.next()) {
                if (tabla.getString("Cedula").equalsIgnoreCase(clientebd[0])) {
                    tabla.updateString("Cedula", clientebd[0]);
                    tabla.updateString("Nombre", clientebd[1]);
                    tabla.updateString("Apellido", "" + clientebd[2]);
                    tabla.updateString("Telefono", "" + clientebd[3]);
                    tabla.updateString("Direccion", "" + clientebd[4]);
                    tabla.updateBoolean("CreditoCliente", Credito(clientebd[5]));
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
                if (tabla.getString("Cedula").equalsIgnoreCase(cedula)) {
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
        String[] columnas = {"Cedula", "Nombre", "Apellido", "Telefono"};
        clientes.addColumn(columnas[0]);
        clientes.addColumn(columnas[1]);
        clientes.addColumn(columnas[2]);
        clientes.addColumn(columnas[3]);

        try {
            conexion("clientes");
            while (tabla.next()) {
                String[] fila = {
                    tabla.getString("Cedula"),
                    tabla.getString("Nombre"),
                    tabla.getString("Apellido"),
                    tabla.getString("Telefono")
                };
                clientes.addRow(fila);
                for (int i = 0; i < fila.length; i++) {
                    System.out.println(fila[i] + " , ");
                }
            }
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return clientes;
    }

    public JComboBox NombreCliente() {
        JComboBox nombre = new JComboBox();
        String[][] Clientes = this.CedulaNombre();
        for(int i = 0; i < Clientes.length; i++){
            nombre.addItem(Clientes[i][1]);
        }
        return nombre;
    }

    public String[][] CedulaNombre() {
        String[][] clientes = new String[TamañoTabla()][2];
        try {
            conexion("clientes");
            int fila=0;
            while (tabla.next()) {
                clientes[fila][0]=tabla.getString("Cedula");
                clientes[fila][1] = tabla.getString("Nombre")+" "+ tabla.getString("Apellido");
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
