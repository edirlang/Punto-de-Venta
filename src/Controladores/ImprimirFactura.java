/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Login;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ImprimirFactura extends Thread{
    String total, efectivo, cambio, NumeroFactura, cajero, fecha, hora, cliente, ccCliente;
    DefaultTableModel Productos;
    
    public ImprimirFactura(DefaultTableModel m,String[] datos) {
        
        this.total = datos[5];
        this.efectivo = datos[6];
        this.cambio = datos[7];
        this.NumeroFactura = datos[0];
        this.cajero = Login.NombreUsuario;
        this.fecha = datos[1];
        this.hora = datos[2];
        this.cliente = datos[4];
        this.ccCliente = datos[3];
        this.Productos = m;
    }

    public ImprimirFactura() {
    }

    @Override
    public void run() {
        String cadena = headTicket();
        cadena += "Factura #: " + NumeroFactura + " " + "Cajero(a): " + cajero + "\n"
                + "Fecha: " + fecha + " " + "Hora: " + hora + "\n"
                + "Cliente: " + cliente + "  " + "C.C: " + ccCliente + "\n";

        cadena += "\n";
        cadena += "Descripcion |UD |P.Unit|Subtotal" + "\n";
        for (int i = 0; i < 32; i++) {
            cadena += "-";
        }
        
        String imp2 = "";
        String es1 = "", es2 = "", es3 = "", es4;

        for (int i = 0; i < Productos.getRowCount(); i++) {
            es1 = String.format("%1$-12s", Productos.getValueAt(i, 1).toString());

            es2 = "|"+String.format("%1$-3s", Productos.getValueAt(i, 2).toString());

            es3 = "|"+String.format("%1$-6s", Productos.getValueAt(i, 3).toString());

            es4 = "|$" + String.format("%1$-7s",Productos.getValueAt(i, 4).toString());
            imp2 += es1 + es2 + es3 + es4 + "\n"  ;
        }
        
        for (int i = 0; i < 32; i++) {
            imp2 += "-";
        }
        imp2 += "\n";
        String pie = "Total: $" + total + "\n";
        
        pie += "Efectivo : $" + efectivo + "\n";
        pie += "Cambio: $" + cambio + "\n";
        
        
        String factura = cadena+imp2+pie;
        
        factura += "\n";
        factura += "\n";
        
        sendDoc(factura);        
        
        this.stop();
        this.destroy();
    }
    
    private String headTicket(){
        String cadena =   "    Autoservicio Surtialiss\n"
                + "        Nit: 21061835-0\n"        
                + "       Telefono: 3114834122\n";
                        
        return cadena;
    }
    
    public void cut(PrintWriter ps){
        try{
            char[] ESC_CUT_PAPER = new char[]{0x1b, 'm'};
            ps.write(ESC_CUT_PAPER);
        }catch(Exception e){
            System.out.print(e);
        }
    }
    
    public void openCash(){
        try{
            FileWriter imp = new FileWriter("/dev/lp0 ");
            imp.write(27);
            imp.write(112);
            imp.write(0);
            imp.write(150);
            imp.write(150);
            imp.write(0);
            imp.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    public void ImprimirReporte(String[] Datos, String fecha) {
        String cadena =  headTicket();
        cadena +="Fecha: " + fecha +"\n";
        String imp2 = cadena;
        imp2 += "\n";
        String pie = "Total Vendido: $" + Datos[0] + "\n";
        pie += "Pago de pedidos : $" + Datos[1] + "\n";
        pie += "Efectivo en Caja: $" + Datos[2] + "\n";

        imp2 += pie;
        
        imp2 += "\n";
        imp2 += "\n";
        
        sendDoc(imp2);
    }
    
    private void sendDoc(String text){
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.BYTE_ARRAY.AUTOSENSE;
        DocPrintJob pj = service.createPrintJob();
        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
        
        byte[] bytes = text.getBytes();
        Doc doc = new SimpleDoc(bytes, flavor, null);
        try {
            pj.print(doc, attributeSet);
        } catch (PrintException e) {
            JOptionPane.showMessageDialog(null, "eror" + e);
        }
    }
}
