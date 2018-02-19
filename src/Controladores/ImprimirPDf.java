/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import Vistas.Login;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caja1
 */
public class ImprimirPDf extends Thread{

    String total, efectivo, cambio, num, cajero, fecha, hora, cli, cc, ganancia;
    DefaultTableModel m;
    boolean fianza;

    public ImprimirPDf(DefaultTableModel m,String[] datos) {
        this.total = datos[5];
        this.efectivo = datos[6];
        this.cambio = datos[7];
        this.num = datos[0];
        this.cajero = Login.NombreUsuario;
        this.fecha = datos[1];
        this.hora = datos[2];
        this.cli = datos[3];
        this.cc = datos[4];
        this.m = m;
    }
    public void run(){
        try {
            FileOutputStream archivo = new FileOutputStream("C:\\SurtiAliss\\Factura\\factura-" + num + ".pdf");
            Rectangle pageSize = new Rectangle(227f, 841f); //ancho y alto
            Document factura = new Document(pageSize, 10f, 10f, 0f, 0f);
            PdfWriter.getInstance(factura, archivo);
            factura.open();

            Font fuente = new Font(Font.FontFamily.COURIER);

            //cabecera del factura
            String cabecera;
            cabecera = "Autoservicio SurtiAlis" + "\n";
            cabecera += "Cll 27 # 2B-36" + "\n";
            cabecera += "Tel: 3114834122" + "\n";
            cabecera += "Nit: 210650835" + "\n";

            Paragraph p3 = new Paragraph(cabecera, fuente);

            cabecera = "N°: " + num + "      " + "Cajero(a): " + cajero + "\n";
            cabecera += "Fecha: " + fecha + "      " + "Hora: " + hora + "\n";
            cabecera += "Cliente: " + cli + "      " + "C.C.: " + cc + "\n";

            Paragraph p = new Paragraph(cabecera, fuente);
            fuente.setSize(7);
            p3.setAlignment(Element.ALIGN_CENTER);

            // Este codigo genera una tabla de 12 columnas
            PdfPTable table = new PdfPTable(12);

            PdfPCell cel2 = new PdfPCell(new Paragraph("Descripcion", fuente));
            PdfPCell cel3 = new PdfPCell(new Paragraph("Cant.", fuente));
            PdfPCell cel4 = new PdfPCell(new Paragraph("Vlr. und.", fuente));
            PdfPCell cel5 = new PdfPCell(new Paragraph("Sub Total", fuente));
            cel2.setColspan(6);
            cel4.setColspan(2);
            cel5.setColspan(3);

            table.addCell(cel2);
            table.addCell(cel3);
            table.addCell(cel4);
            table.addCell(cel5);

            for (int i = 0; i < this.m.getRowCount(); i++) {
                for (int j = 1; j < this.m.getColumnCount(); j++) {
                    PdfPCell celda = new PdfPCell(new Paragraph(this.m.getValueAt(i, j).toString(), fuente));
                    celda.setBorder(0);
                    if (j == 1) {
                        celda.setColspan(6);
                    } else if (j == 2) {
                        celda.setColspan(1);
                    } else if (j == 3) {
                        celda.setColspan(2);
                    } else {
                        celda.setColspan(3);
                    }
                    table.addCell(celda);
                }
            }
            factura.add(p3);
            factura.add(p);
            factura.add(new Paragraph("\n"));
            factura.add(table);
            factura.add(new Paragraph("\n"));

            String pie;
            pie = "Total: $ " + total + "\n";
            pie += "Efectivo: $ " + efectivo + "\n";
            pie += "Cambio: $ " + cambio;

            Paragraph p2 = new Paragraph(pie, fuente);
            p2.setAlignment(Element.ALIGN_LEFT);

            factura.add(p2);
            factura.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arhivo no encontrado " + ex);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Arhivo no creado " + ex);
        }
        this.stop();
        this.destroy();
    }
}
