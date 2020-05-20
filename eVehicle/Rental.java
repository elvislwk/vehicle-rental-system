// Rental class
package eVehicle;
import java.util.*;

public class Rental
{
  // attributes
   private int counter;
   private int rentalNo;
   private Customer customer = new Customer();
   private Calendar pickupDate;
   private Calendar returnDate;
   private Vehicle vehicle;
  
  // behaviors
  
  // constructors
   public Rental()	// default constructor
   {
    // attributes will be initialized to their default values
   }
   	 
   public Rental(Customer c, Calendar p, Calendar r, Vehicle v) // parameterized constructor
   { 
      customer = c;
      pickupDate = p;
      returnDate = r;
      vehicle = v;
   }
  
  // add and get methods
   public void     setCounter(int c) { counter = c; }
   public int      getCounter() { return counter; }  
   public void     setRentalNo(int r) { rentalNo = r; }
   public int      getRentalNo() { return rentalNo; }
   public void     setCustomer(Customer c) { customer = c; }
   public Customer getCustomer(){ return customer; }
   public void     setPickupDate(Calendar p) { pickupDate = p; }
   public Calendar getPickupDate() { return pickupDate; }
   public void     setReturnDate(Calendar r) { returnDate = r; }
   public Calendar getReturnDate() { return returnDate; }  
   public void     setVehicle(Vehicle v) { vehicle = v; }
   public Vehicle  getVehicle() { return vehicle; }

  // method to calculate the cost of rental
   public double calculateRental()
   {
      long timeInMillisec = (returnDate.getTimeInMillis() - pickupDate.getTimeInMillis());
      int days = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
      if (days == 0)
      days = 1; // rent of less than a day
      return days * vehicle.calculateCharges();
   }
   
   // toString() method
   public String toString() { return "Counter:" + counter + "\tRental No:" + rentalNo + "\tCustomer:" + customer + "\tPick Up Date:" + pickupDate + "\tReturn Date:" + returnDate + "\tVehicle:" + vehicle; }   

}