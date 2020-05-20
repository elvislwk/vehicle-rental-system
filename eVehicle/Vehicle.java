// Vehicle class
package eVehicle;

public abstract class Vehicle
{
  // attributes
  protected String regNo;
  protected String make; 
  protected String model; 
  protected double dailyRate;
  protected boolean available;
  
  // behaviors
  
  // constructors
  public Vehicle()	// default constructor
  {
  	 // attributes will be initialized to their default values
  }

  public Vehicle(String r, String ma, String mo, double d, boolean a)	// parameterized constructor
  {
	 regNo = r;
    make = ma;
    model = mo;
    dailyRate = d;
    available = a;
  }
  
  // set and get methods
  public void    setRegNo(String r) { regNo = r; }
  public String  getRegNo() { return regNo; }  
  public void    setMake(String ma) { make = ma; }
  public String  getMake() { return make; }
  public void    setModel(String mo) { model = mo; }
  public String  getModel(){ return model; }
  public void    setDailyRate(double d) { dailyRate = d; }
  public double  getDailyRate() { return dailyRate; }
  public void    setAvailable(boolean a) { available = a; }
  public boolean getAvailable(){ return available; }
  
  // abstract method that links to the Car and Truck subclasses
  public abstract double calculateCharges();
  
  // toString() method
  public String toString() { return "Reg No:" + regNo + "\tMake:" + make + "\tModel:" + model + "\tDaily Rate:" + dailyRate + "\tAvailable:" + available; }

}