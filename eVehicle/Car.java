// Car class derived from Vehicle class
package eVehicle;

public class Car extends Vehicle
{
  	// Attributes
  	private int childSeat;
  
  	// Behaviors
  
  	// constructors
  	public Car() 	// default constructor
  	{ 
		super();
  	}
	 
  	public Car(String r, String ma, String mo, double d, boolean a) // parameterized constructor
  	{ 
     	super(r,ma,mo,d,a);
  	}
  
  	// set and get methods
  	public void setChildSeat(int c) { childSeat = c; }
  	public int  getChildSeat() { return childSeat; }
  	
   // method to calculate the charges for the car rented
  	public double calculateCharges() { return dailyRate + childSeat * 10.0; }

	// toString() method
  	public String toString() { return super.toString() + "\tChild Seat:" + childSeat; }
 }