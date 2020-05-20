// Truck class derived from Vehicle class
package eVehicle;

public class Truck extends Vehicle
{
  	// Attributes
   private boolean driver;
  
  	// Behaviors
  
  	// constructors
   public Truck() 	// default constructor
   { 
      super();
   }
	 
   public Truck(String r, String ma, String mo, double d, boolean a) // parameterized constructor
   { 
      super(r,ma,mo,d,a); 
   }
  
  	// set and get methods
   public void    setDriver(boolean dr) { driver = dr; }
   public boolean getDriver() { return driver; }
  	
   // method to calculate the charges for the truck rented
   public double calculateCharges()
   {
      if (driver == true)
         return dailyRate + 100;
      else
         return dailyRate;
   }

	// toString() method
   public String toString() { return super.toString() + "\tDriver:" + driver; }
}