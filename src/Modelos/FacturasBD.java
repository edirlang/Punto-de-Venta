/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.GuardarDetalleFactura;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class FacturasBD extends Conexion {

    public FacturasBD() {
        super();
    }

    protected int NumeroUltimaFactura() {
        try {
            this.conexion("facturas");
            tabla.last();
            int numero = tabla.getInt("NumeroFactura");
            close();
            return numero;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public void nuevoFactura(String[] datos) {
        try {
            this.conexion("facturas");
            tabla.moveToInsertRow();
            tabla.updateString("NumeroFactura", datos[0]);
            tabla.updateString("Cedula", datos[1]);
            tabla.updateString("Fecha", datos[2]);
            tabla.updateString("Hora", datos[3]);
            tabla.updateString("Total", datos[4]);
            tabla.updateBoolean("CreditoFactura", this.Credito(datos[5]));
            tabla.insertRow();
            close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se registro el pedido " + ex);
            close();
        }
    }

    public void NuevoDetalle(DefaultTableModel detalles, String datos) {
        GuardarDetalleFactura nuevo = new GuardarDetalleFactura(detalles, datos);
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
            conexion("productos");
            for (int i = 0; i < facturas.getRowCount(); i++) {
                tabla.first();
                while (tabla.next()) {
                    if (facturas.getValueAt(i, 0).toString().equalsIgnoreCase(tabla.getString("Codigo"))) {
                        facturas.setValueAt(tabla.getString("Nombre"), i, 1);
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
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "Error no se logro conectarse a  la tabla");
        }
        return facturas;
    }

    public String[] ReporteDia(String Fecha) {
        String[] datos = null;
        conexion("facturas");
        long total = 0;
        try {
            while (tabla.next()) {
                if (tabla.getString("Fecha").equalsIgnoreCase(Fecha)) {
                    total += Long.parseLong(tabla.getString("Total"));
                }
            }
            close();
        } catch (SQLException ex) {
            close();
            JOptionPane.showMessageDialog(null, "No se Registraron ventas");
        }

        conexion("egresos");
        long egresos = 0;
        try {
            while (tabla.next()) {
                if (tabla.getString("Fecha").equalsIgnoreCase(Fecha)) {
                    egresos += Long.parseLong(tabla.getString("Valor"));
                }
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