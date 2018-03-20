package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author surtialiss
 */
public class MonthDate {
    private int id;
    private String txt;
    private int idDate;

    public MonthDate(int id, String text){
        this.id = id;
        this.txt = text;
        
    }
    
    public MonthDate(){
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }
    
    @Override
    public String toString(){
        return this.txt;
    }
}
