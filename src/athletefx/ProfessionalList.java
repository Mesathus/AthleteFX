/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athletefx;
import java.util.ArrayList;

/**
 *
 * @author Mesa
 */
public class ProfessionalList {
    // list fields
    private ArrayList<Professional> profList;
    
    // <editor-fold defaultstate="collapsed" desc="professional internal class to be managed by the parent List">
    private class Professional{
        // professional fields
        private String _name;
        private double _salary;
        private String _job;        
        // constructor method
        public Professional(String n, String j, double athSal) throws NumberFormatException{
            this._name = n;
            this._job = j;
            switch(j.toLowerCase()){
                case "lawyer": this._salary = athSal * .1; break;
                case "trainer": this._salary = athSal * .07; break;
                case "personal assistant": this._salary = athSal * .03; break;
                case "agent": this._salary = athSal * .05; break;
                default: throw new NumberFormatException("One of the specified jobs wasn't found.");
            }
        }
        // getters
        public String Name(){
            return this._name;
        }
        public String Job(){
            return this._job;
        }
        public double Salary(){
            return this._salary;
        }     
        
        @Override
        public String toString(){
            return String.format("%s the %s makes $%.2f per year.", this._name, this._job, this._salary);
        }
    }
    // </editor-fold>
    
    //constructor method
    public ProfessionalList(){
        profList = new ArrayList();
    }
    
    // list management methods
    public boolean Insert(String n, String j, double athSal){
        try{
            Professional p = new Professional(n, j, athSal);
            this.profList.add(p);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public boolean Remove(int i){
        try{
            this.profList.remove(i);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    
    public void Clear(){
        this.profList.clear();
    }
    
    // item array getter
    public ArrayList<Professional> Items(){
        return this.profList;
    }
}
