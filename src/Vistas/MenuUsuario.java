/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.FacturaController;
import Controladores.ImprimirFactura;
import Vistas.Clientes.ClientesView;
import Vistas.Clientes.CustomerView;
import Vistas.Facturacion.FacturaView;
import Vistas.Facturacion.FacturasView;
import Vistas.Facturacion.InvoiceView;
import Vistas.Facturacion.ReporteDias;
import Vistas.Productos.Productos;
import javax.swing.JOptionPane;

/**
 *
 * @author Mis-Dark
 */
public class MenuUsuario extends javax.swing.JFrame {

    /**
     * Creates new form MenuUsuario
     */
    
    public MenuUsuario() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Botones = new javax.swing.JPanel();
        btnFactura = new org.edisoncor.gui.button.ButtonAero();
        bntClientes = new org.edisoncor.gui.button.ButtonAero();
        btnProductos = new org.edisoncor.gui.button.ButtonAero();
        buttonAero1 = new org.edisoncor.gui.button.ButtonAero();
        btnFacturas = new org.edisoncor.gui.button.ButtonAero();
        btnCerrarSesion = new org.edisoncor.gui.button.ButtonAero();
        btnSalir = new org.edisoncor.gui.button.ButtonAero();
        Desktop = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SURTIALISS");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Botones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Botones.setMaximumSize(new java.awt.Dimension(32767, 30));
        Botones.setLayout(new java.awt.GridLayout());

        btnFactura.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btnFactura.setForeground(new java.awt.Color(0, 0, 0));
        btnFactura.setText("Nueva Factura (F2)");
        btnFactura.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturaActionPerformed(evt);
            }
        });
        Botones.add(btnFactura);

        bntClientes.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        bntClientes.setForeground(new java.awt.Color(0, 0, 0));
        bntClientes.setText("Clientes (F3)");
        bntClientes.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        bntClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntClientesActionPerformed(evt);
            }
        });
        Botones.add(bntClientes);

        btnProductos.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnProductos.setForeground(new java.awt.Color(0, 0, 0));
        btnProductos.setText("Productos (F4)");
        btnProductos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        Botones.add(btnProductos);

        buttonAero1.setForeground(new java.awt.Color(0, 0, 0));
        buttonAero1.setText("Pedidos (F5)");
        buttonAero1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        buttonAero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAero1ActionPerformed(evt);
            }
        });
        Botones.add(buttonAero1);

        btnFacturas.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnFacturas.setForeground(new java.awt.Color(0, 0, 0));
        btnFacturas.setText("Facturas (F6)");
        btnFacturas.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturasActionPerformed(evt);
            }
        });
        Botones.add(btnFacturas);

        btnCerrarSesion.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnCerrarSesion.setForeground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setText("Cerar Sesion");
        btnCerrarSesion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        Botones.add(btnCerrarSesion);

        btnSalir.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        btnSalir.setForeground(new java.awt.Color(0, 0, 0));
        btnSalir.setText("Salir");
        btnSalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        Botones.add(btnSalir);

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 716, Short.MAX_VALUE)
        );

        jMenu1.setText("Facturacion");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem1.setText("NuevaFactura");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem2.setText("Clientes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItem3.setText("Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem4.setText("Pagar Pedido");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem5.setText("Facturas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenuItem6.setText("Ventas Diarias");
        jMenu3.add(jMenuItem6);

        jMenuItem8.setText("Ventas Producto");
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reporte");

        jMenuItem9.setText("Reporte por dias");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Ayuda");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Botones, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
            .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Botones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Desktop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.Cerrar();
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void bntClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntClientesActionPerformed
        this.Desktop.removeAll();
        CustomerView clientes = new CustomerView();
        clientes.setVisible(true);
        this.Desktop.add(clientes);

    }//GEN-LAST:event_bntClientesActionPerformed

    private void btnFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturaActionPerformed
        FacturaView fac = new FacturaView();
        fac.setVisible(true);
        //InvoiceView invoiceView = new InvoiceView();
        //this.Desktop.add(invoiceView);
        //invoiceView.show();
        //this.Desktop.selectFrame(true);
    }//GEN-LAST:event_btnFacturaActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
        Productos pro = new Productos();
        pro.setVisible(true);
        this.Desktop.removeAll();
        this.Desktop.add(pro);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //InvoiceView invoiceView = new InvoiceView();
        //this.Desktop.add(invoiceView);
        //invoiceView.show();
        FacturaView fac = new FacturaView();
        fac.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.Desktop.removeAll();
        
            CustomerView clientes = new CustomerView();
            clientes.setVisible(true);
            this.Desktop.add(clientes);
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Productos pro = new Productos();
        pro.setVisible(true);
        this.Desktop.removeAll();
        this.Desktop.add(pro);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void buttonAero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAero1ActionPerformed
        PagoPedidos pedido = new PagoPedidos(this, true);
        pedido.setVisible(true);
    }//GEN-LAST:event_buttonAero1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        PagoPedidos pedido = new PagoPedidos(this, true);
        pedido.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void btnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturasActionPerformed
        FacturasView pro = new FacturasView();
        pro.setVisible(true);
        this.Desktop.removeAll();
        this.Desktop.add(pro);
    }//GEN-LAST:event_btnFacturasActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FacturasView pro = new FacturasView();
        pro.setVisible(true);
        this.Desktop.removeAll();
        this.Desktop.add(pro);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ReporteDias reporte = new ReporteDias(this,true);      
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.Cerrar();
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.Cerrar();
    }//GEN-LAST:event_formWindowClosed

    private void Cerrar(){
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(rootPane, "Estas Seguro?", "Mensaje de Confirmacion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            FacturaController cierre = new FacturaController();
            
            String[] datos = cierre.ReporteDia(cierre.Fecha());
            ImprimirFactura imp =  new ImprimirFactura();
            imp.ImprimirReporte(datos, cierre.Fecha());
            this.setVisible(false);
            this.dispose();
        } else {
            this.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Botones;
    private javax.swing.JPanel Desktop;
    private org.edisoncor.gui.button.ButtonAero bntClientes;
    private org.edisoncor.gui.button.ButtonAero btnCerrarSesion;
    private org.edisoncor.gui.button.ButtonAero btnFactura;
    private org.edisoncor.gui.button.ButtonAero btnFacturas;
    private org.edisoncor.gui.button.ButtonAero btnProductos;
    private org.edisoncor.gui.button.ButtonAero btnSalir;
    private org.edisoncor.gui.button.ButtonAero buttonAero1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
