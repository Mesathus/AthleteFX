/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athletefx;

/**
 *
 * @author Mesa
 */
public class Athlete {
    // athlete fields
    private String _name;
    private double _salary;
    
    // constructor methods
    public Athlete(){
        _name = "";
        _salary = 0;
    }
    public Athlete(String n, double s){
        _name = n;
        _salary = s;
    }
    
    // getters
    public String Name(){
        return this._name;
    }
    public double Salary(){
        return this._salary;
    }
    
    //setters
    public boolean SetName(String n){
        try{
            this._name = n;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    public boolean SetSalary(double s){
        try{
            this._salary = s;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }    
    
    @Override
    public String toString(){
        return String.format("%s makes $%.2f per year", this._name, this._salary);
    }
}
