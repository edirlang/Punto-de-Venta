/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.GuardarDetalleFactura;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class FacturasBD extends Conexion {

    private String table_name;
    public FacturasBD() {
        super();
        table_name = "facturas";
    }

    protected int NumeroUltimaFactura() {
        try {
            this.conexion(this.table_name);
            tabla.last();
            int numero = tabla.getInt("NumeroFactura");
            close();
            return numero;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int newInvoice(String[] datos) {
        int number_invoice = 0;
        number_invoice = excecuteSQLAutoIncrement("Insert into "+table_name+" (Cedula, Fecha, Hora, Total, CreditoFactura) values ("
                + datos[0] +","
                + "'" + dateNowString() + "',"
                + "'" + hourNow() + "',"
                + datos[1] + ","
                + this.Credito(datos[2])
                + ")");
        return number_invoice;
    }

    public String dateNowString() {
        Date fechaActual = new Date();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyy-MM-dd");
        Calendar cal1 = Calendar.getInstance();
        return formato1.format(fechaActual);
    }
    
    private String hourNow(){
        Date fechaActual = new Date();
        SimpleDateFormat formato2 = new SimpleDateFormat("HH:mm");
        Calendar cal1 = Calendar.getInstance();
        return formato2.format(fechaActual);
    }
    
    public void NuevoDetalle(DefaultTableModel detalles, int num_invoice) {
        GuardarDetalleFactura nuevo = new GuardarDetalleFactura(detalles, num_invoice);
        nuevo.start();
    }

    public String[] Consultar(String NumeroFactura) {
        conexion("facturas");
        try {
            String[] factura = null;
            while (tabla.next()) {
                if (NumeroFactura.equals(tabla.getString("NumeroFactura"))) {
                    factura = new String[]{
                        tabla.getString("NumeroFactura"),
                        tabla.getString("Cedula"),
                        tabla.getString("Fecha"),
                        tabla.getString("Hora"),
                        tabla.getString("Total"),
                        Credito(tabla.getBoolean("CreditoFactura"))
                    };
                }
            }
            close();
            return factura;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "La factura no existe" + ex);
            close();
            return null;
        }
    }

    public DefaultTableModel ConsultarDetalleFactura(String NumeroFactura) {
        ClientesBD cliente = new ClientesBD();

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Codigo", "Descripcion", "Cantidad", "Valor Unidad", "SubTotal"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);

        try {
            conexion("detallefactura");
            while (tabla.next()) {
                if (NumeroFactura.equals(tabla.getString("NumeroFactura"))) {
                    String[] fila = {
                        tabla.getString("Codigo"),
                        "1",
                        tabla.getString("Cantidad"),
                        tabla.getString("Valor"),
                        tabla.getString("Total")
                    };
                    facturas.addRow(fila);
                }
            }
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla detalle");
        }

        try {
            conexion("product");
            for (int i = 0; i < facturas.getRowCount(); i++) {
                tabla.first();
                while (tabla.next()) {
                    if (facturas.getValueAt(i, 0).toString().equalsIgnoreCase(tabla.getString("bar_code"))) {
                        facturas.setValueAt(tabla.getString("name"), i, 1);
                    }
                }
            }
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro cargar productos");
        }
        return facturas;
    }

    public DefaultTableModel todos() {
        DefaultTableModel facturas = new DefaultTableModel();
        FacturasAll f = new FacturasAll(facturas);
        f.start();
        return f.facturas;
    }

    public DefaultTableModel FacturasCredito(String CedulaCliente) {
        ClientesBD cliente = new ClientesBD();

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);
        try {
            conexion("facturas");
            while (tabla.next()) {
                if (tabla.getString("Cedula").equalsIgnoreCase(CedulaCliente) && tabla.getBoolean("CreditoFactura")) {
                    String[] nombre = cliente.consultar(tabla.getString("Cedula"));
                    String[] fila = {
                        tabla.getString("NumeroFactura"),
                        nombre[1],
                        tabla.getString("Fecha"),
                        tabla.getString("Hora"),
                        tabla.getString("Total"),
                        Credito(tabla.getBoolean("CreditoFactura"))
                    };
                    facturas.addRow(fila);
                }
            }
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return facturas;
    }

    public void CambiarCredito(String cedula) {
        conexion("facturas");

        try {
            while (tabla.next()) {
                if ((cedula.equalsIgnoreCase(tabla.getString("Cedula"))) && (tabla.getBoolean("CreditoFactura"))) {
                    tabla.updateBoolean("CreditoFactura", false);
                    tabla.updateRow();
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error " + ex);
        }
        close();
    }

    public DefaultTableModel FacturasDia() {
        ClientesBD cliente = new ClientesBD();
        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Fecha", "Total", "Egresos"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        Egresos egreso = new Egresos();

        try {
            conexion("facturas");
            long total = 0;
            tabla.first();
            String FechaActual = tabla.getString("Fecha");
            String FechaNueva;
            while (tabla.next()) {
                FechaNueva = tabla.getString("Fecha");
                
                if (FechaActual.equalsIgnoreCase(FechaNueva)) {
                    total += Long.parseLong(tabla.getString("Total"));
                } else {
                    String[] fila = {
                        FechaActual,
                        total + "",
                        egreso.ConsultarValor(FechaActual)
                    };
                    facturas.addRow(fila);
                    FechaActual = FechaNueva;
                    total = 0;
                }
            }
            String[] fila = {
                FechaActual,
                total + "",
                egreso.ConsultarValor(FechaActual)
            };
            facturas.addRow(fila);
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return facturas;
    }

    public DefaultTableModel FacturasDiaDetalle(String fecha) {
        ClientesBD cliente = new ClientesBD();

        DefaultTableModel facturas = new DefaultTableModel();
        String[] columnas = {"Numero", "Cliente", "Fecha", "Hora", "Total", "Credito"};
        facturas.addColumn(columnas[0]);
        facturas.addColumn(columnas[1]);
        facturas.addColumn(columnas[2]);
        facturas.addColumn(columnas[3]);
        facturas.addColumn(columnas[4]);
        facturas.addColumn(columnas[5]);
        try {
            conexion("facturas");
            while (tabla.next()) {
                if (tabla.getString("Fecha").equalsIgnoreCase(fecha)) {
                    String[] nombre = cliente.consultar(tabla.getString("Cedula"));
                    String[] fila = {
                        tabla.getString("NumeroFactura"),
                        nombre[1],
                        tabla.getString("Fecha"),
                        tabla.getString("Hora"),
                        tabla.getString("Total"),
                        Credito(tabla.getBoolean("CreditoFactura"))
                    };
                    facturas.addRow(fila);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        close();
        return facturas;
    }

    public String[] ReporteDia(String Fecha) {
        String[] datos = null;
        conexion("facturas where Fecha = '"+Fecha+"'");
        long total = 0;
        try {
            while (tabla.next()) {
                total += Long.parseLong(tabla.getString("Total"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se Registraron ventas");
        }
        close();
        
        conexion("egresos where Fecha BETWEEN '"+Fecha+" 00:00:00:000000' AND '"+Fecha+" 23:59:59:999999'");
        long egresos = 0;
        try {
            while (tabla.next()) {
                egresos += Long.parseLong(tabla.getString("Valor"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se Registraron pagos");
        }
        close();
        datos = new String[]{
            total + "",
            egresos + "",
            (total - egresos) + ""
        };
        
        return datos;
    }
}