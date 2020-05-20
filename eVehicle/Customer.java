// Customer class
package eVehicle;
import java.util.*;

public class Customer
{
  // attributes
   private String icNo;
   private String name;
   private Calendar dob;
   private String tel;
   private ArrayList rentalList = new ArrayList();
  
  // behaviors
  
  // constructors
   public Customer()	// default constructor
   {
    // attributes will be initialized to their default values
   }
   	 
   public Customer(String i, String n, Calendar d, String t) // parameterized constructor
   { 
      icNo = i;
      name = n;
      dob = d;
      tel = t;
   }
  
  // add and get methods
   public void     setIcNo(String i) { icNo = i; }
   public String   getIcNo() { return icNo; }  
   public void     setName(String n) { name = n; }
   public String   getName() { return name; }
   public void     setDob(Calendar d) { dob = d; }
   public Calendar getDob(){ return dob; }
   public void     setTel(String t) { tel = t; }
   public String   getTel() { return tel; }
  
   public void addRental(Rental rent) { rentalList.add(rent); }
   public ArrayList getRentalList() { return rentalList; }

   // toString() method
   public String toString() { return "IC No:" + icNo + "\tName:" + name + "\tDOB:" + dob + "\tTel:" + tel + "\tRental List:" + rentalList; }   

}