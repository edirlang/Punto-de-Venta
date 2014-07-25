/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author Caja1
 */
public class Hora extends Thread{
    JTextField hora;
    int dia;
    
    public Hora(JTextField hora, int dia){
        this.hora=hora;
        this.dia = dia;
    }
    
    @Override
    public void run(){
       while(0==0){
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               
           }
           this.HoraActual();
       }
   }
    
    private void HoraActual(){
        Date fechaActual = new Date();

        SimpleDateFormat formato2 = new SimpleDateFormat("h:mm a");

        Calendar cal1 = Calendar.getInstance();

        hora.setText(formato2.format(fechaActual));
        hora.setEditable(false);

        dia = cal1.get(Calendar.DAY_OF_WEEK);
    }
}
