import eVehicle.Customer;
import eVehicle.Rental;
import eVehicle.Vehicle;
import eVehicle.Car;
import eVehicle.Truck;
import java.text.*; // used for Decimal Format
import java.util.*; // used for Scanner and ArrayList

public class vehicleRentalSystem
{
   public static void main(String[] args)
   {
      // declare arraylists
      ArrayList customerList  = new ArrayList();   // list of customers
      ArrayList vehicleList  = new ArrayList();    // list of vehicles
      ArrayList availVehicleList = new ArrayList();// list of available vehicles
      ArrayList rentalList = new ArrayList();      // list of rented vehicles
      ArrayList rentalHistory = new ArrayList();   // list of history records of rented vehicles
      
      initCustomerList(customerList);
      initVehicleList(vehicleList);
      
      for (int i=0; i<vehicleList.size(); i++)  //initialise the availVehicleList using a for loop
      {
         Vehicle v = (Vehicle) vehicleList.get(i);
         if (v.getAvailable() == true)
         {
            availVehicleList.add(v);
         }
      }
      
      Scanner input = new Scanner(System.in);
      
      int option;
      do
      {
         displayMenu(); // display the menu
         System.out.print("Enter your option : ");
         option = input.nextInt();
         switch (option)
         {
            case 1: System.out.println("\nOption 1. Register Customer");
               registerCustomer(customerList);
               break;
            case 2: System.out.println("\nOption 2. List All Customers");
               listCustomers(customerList);
               break;
            case 3: System.out.println("\nOption 3. List All Vehicles");
               listVehicles(vehicleList);
               break;
            case 4: System.out.println("\nOption 4. List All Available Vehicles");
               listAvailableVehicles(vehicleList);
               break;
            case 5: System.out.println("\nOption 5. Rent A Vehicle");
               rentVehicle(customerList, vehicleList, availVehicleList, rentalList, rentalHistory);
               break;
            case 6: System.out.println("\nOption 6. List rental details for all cars or trucks");
               listRentalDetails(rentalList);
               break;
            case 7: System.out.println("\nOption 7. Return A Vehicle");
               returnVehicle(rentalList, customerList, availVehicleList);
               break;
            case 8: System.out.println("\nOption 8. List car or truck with the highest number of rental transactions");
               listMostRentedVehicle(rentalHistory, vehicleList);
               break;
            case 9: System.out.println("\nOption 9. List rental details of a customer over a period");
               listRentalHistoryDetailsOfCustomer(rentalList, customerList, vehicleList);
               break;
            case 0: System.out.println("Exiting programme...");
               break;
            default:System.out.println("Error: Invalid Input"); // default output
         }
      }while (option != 0);
   }
   
   // initialize customer arraylist with customers
   public static void initCustomerList(ArrayList customerList)
   {
      Customer c;
      Calendar dob;
      
      dob = new GregorianCalendar(1990, 2, 22);
      c = new Customer("S9099999A", "Adrian", dob, "91112222");
      customerList.add(c);
      
      dob = new GregorianCalendar(1989, 7, 11);
      c = new Customer("S8558911B", "Benjamin", dob, "96668888");
      customerList.add(c);
      
      dob = new GregorianCalendar(1979, 11, 13);
      c = new Customer("S7670326C", "Christina", dob, "93335555");
      customerList.add(c);
   }
   
   // initialize vehicle arraylist with vehicles
   public static void initVehicleList(ArrayList vehicleList)
   {
      Vehicle v;
      
      v = new Car("SJV1883R", "Honda", "Civic", 60.00, true);
      vehicleList.add(v);
      
      v = new Car("SJZ2987A", "Toyota", "Altis", 60.00, true);
      vehicleList.add(v);
      
      v = new Car("SKA4370H", "Honda", "Accord", 80.00, true);
      vehicleList.add(v);
      
      v = new Car("SKD8024M", "Toyota", "Camry", 80.00, true);
      vehicleList.add(v);
      
      v = new Car("SKH5922D", "BMW", "320i", 90.00, true);
      vehicleList.add(v);
      
      v = new Car("SKM5139C", "BMW", "520i", 100.00, true);
      vehicleList.add(v);
      
      v = new Car("SKM5139C", "Mercedes", "S500", 300.00, true);
      vehicleList.add(v);
      
      v = new Truck("GB3221K", "Tata", "Magic", 200.00, true);
      vehicleList.add(v);
      
      v = new Truck("YB8283M", "Isuzu", "NPR", 250.00, true);
      vehicleList.add(v);
      
      v = new Truck("YK5133H", "Isuzu", "NQR", 300.00, true);
      vehicleList.add(v);
   }
   
   // display the menu
   public static void displayMenu()
   {
      System.out.println("MENU");
      System.out.println("=====");
      System.out.println("1. Register customer");
      System.out.println("2. List all customers");
      System.out.println("3. List all vehicles");
      System.out.println("4. List all available vehicles");
      System.out.println("5. Rent a vehicle");
      System.out.println("6. List all rental details for all cars or trucks");
      System.out.println("7. Return a vehicle");
      System.out.println("8. List car or truck with the highest number of rental transactions");
      System.out.println("9. List rental details of a customer over a period");
      System.out.println("0. Exit");
   }
   
   //register new customers
   public static void registerCustomer(ArrayList customerList)
   {
      Scanner input = new Scanner(System.in);
      String nric;
      boolean error = false; // initialise error as false
      Customer c = null;
      do
      {
         error = false;
         System.out.print("Enter NRIC Number: ");
         nric = input.nextLine().toUpperCase(); // convert all alphabets to upper case
         for (int i=0; i<customerList.size(); i++)
         {
            c = (Customer) customerList.get(i);
            if (c.getIcNo().equalsIgnoreCase(nric)) // checking if it is an existing user by comparing NRIC
               error = true;
         }
         if (nric.length() != 9)
            System.out.println("Error: Invalid Input");
         if (error == true)
            System.out.println("Error: Existing Customer"); // display error if identical NRIC is found
      }while (nric.length() != 9 || error == true);
      System.out.print("Enter Name: ");
      String name = input.nextLine();
      int day, mth, yr;
      do
      {
         System.out.print("Enter Date of Birth (DD  MM  YYYY): ");
         day = input.nextInt();
         mth = input.nextInt();
         yr = input.nextInt();
         if ((day > 31 || day < 1) || (mth > 12 || mth < 1) || yr <= 0) // checking if date entered is valid
            System.out.println("Error: Invalid Date Of Birth");
      }while ((day > 31 || day < 1) || (mth > 12 || mth < 1) || yr <= 0);
      String tel;
      do
      {
         error = false;
         System.out.print("Enter Tel No: "); // prompt user to enter Tel no.
         tel = input.next();
         for (int i=0; i<customerList.size(); i++)
         {
            c = (Customer) customerList.get(i);
            if (c.getTel().equalsIgnoreCase(tel)) // check if there is same number in the system
               error = true;
         }
         if (tel.length() != 8)
            System.out.println("Error: Invalid Input");
         if (error == true)
            System.out.println("Error: Existing Customer"); // display that there is identical number if found
      }while (tel.length() != 8 || error == true);
      Calendar dob = new GregorianCalendar(yr,mth-1,day);
      c = new Customer(nric.toUpperCase(), name, dob, tel);
      customerList.add(c); // add the new customer object into the arraylist
      System.out.println(name + " is registered successfully.");
   }
   
   // show details of exisitng customers
   public static void listCustomers(ArrayList customerList)
   {
      System.out.printf("%s\t%s\t%-10s\t%s\t%s\n", "S/No", "IC Number", "Name", "Date of Birth", "Tel"); // headings
      for (int i=0; i<customerList.size(); i++)
      {
         Customer c = (Customer) customerList.get(i);
         String dob = dateConverter(c.getDob()); // converting Calendar object to String
         
         System.out.printf("%d\t%s\t%-10s\t%s\t%s\n", (i+1), c.getIcNo(), c.getName(), dob, c.getTel()); // displaying customer information
      }
   
   }
   
   // show details of all vehicles
   public static void listVehicles(ArrayList vehicleList)
   {
      System.out.printf("%s\t%-8s\t%-8s\t%s\t%13s\t%s\n", "S/No", "Reg No", "Make", "Model", "Daily Rate($)", "Availability"); // headings
      for (int i=0; i<vehicleList.size(); i++)
      {
         Vehicle v = (Vehicle) vehicleList.get(i);
         if (v.getAvailable() == true)
            System.out.printf("%d\t%-8s\t%-8s\t%s\t%13.2f\tYes\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate()); // display vehicle details if it is available
         else
            System.out.printf("%d\t%-8s\t%-8s\t%3s\t%13.2f\tNo\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate()); // dispplay vehicle details if it is unavailable
      }
   }
   
   //show details of availanle vehicles
   public static void listAvailableVehicles(ArrayList vehicleList)
   {
      boolean noavailable = true;
      for (int i=0; i<vehicleList.size(); i++)
      {
         Vehicle v = (Vehicle) vehicleList.get(i);
         if (v.getAvailable() == true) // check if vehicle is available
            noavailable = false;
      }
      if (noavailable == false)
      {
         int x = 1;
         System.out.printf("%s\t%-8s\t%-8s\t%s\t%13s\n", "S/No", "Reg No", "Make", "Model", "Daily Rate($)"); // headings
         for (int i=0; i<vehicleList.size(); i++)
         {
            Vehicle v = (Vehicle) vehicleList.get(i);
            if (v.getAvailable() == true)
               System.out.printf("%d\t%-8s\t%-8s\t%s\t%13.2f\n", x++, v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate()); // display available vehicle details
         }
      }
      else
         System.out.println("No available vehicles."); // display when there is no available vehicles
   }
   
   // allow registered customers to rent a vehicle
   public static void rentVehicle(ArrayList customerList, ArrayList vehicleList, ArrayList availVehicleList, ArrayList rentalList, ArrayList rentalHistory)
   {
      Scanner input = new Scanner(System.in);
      listCustomers(customerList); // display customer details
      int customerno;
      int day, mth, yr;
      do
      {
         System.out.print("Enter S/No of customer : "); // prompt user to enter customer's S/No
         customerno = input.nextInt();
         if (customerno > customerList.size() || customerno <= 0)
            System.out.println("Error: Invalid Input"); // display error if user entered an invalid number
      }while (customerno > customerList.size() || customerno <= 0);
      Customer c = (Customer) customerList.get(customerno-1);
      System.out.println();
      
      listAvailableVehicles(vehicleList); // display details of available vehicles
      int vehicleno;
      do
      {
         System.out.print("Enter S/No of vehicle	: ");
         vehicleno = input.nextInt();
         if (vehicleno > vehicleList.size() || vehicleno <= 0)
            System.out.println("Error: Invalid Input");
      }while (vehicleno > vehicleList.size() || vehicleno <= 0);
      Vehicle v = (Vehicle) availVehicleList.get(vehicleno-1);
      
      if (v instanceof Car)
      {
         int childSeat;
         do
         {
            System.out.print("Enter Child Seat Required	: ");
            childSeat = input.nextInt();
            if (childSeat < 0)
               System.out.println("Error: Invalid Input");
         }while (childSeat < 0);
         Car car = (Car) v;
         car.setChildSeat(childSeat);
      }
      if (v instanceof Truck)
      {
         String driver;
         boolean error = true;
         do
         {
            System.out.print("Driver Required	(Y/N)	: ");
            driver = input.next();
            Truck t = (Truck) v;
            if (driver.equalsIgnoreCase("y"))
            {
               t.setDriver(true);
               error = false;
            }
            else if (driver.equalsIgnoreCase("n"))
            {
               t.setDriver(false);
               error = false;
            }
            if (error == true)
               System.out.println("Error: Invalid Input");
         }while (error == true);
      }
      Calendar pickUpDate;
      Calendar returnDate;
      int days;
      do
      {
         do
         {
            System.out.print("Enter Pick Up Date (DD  MM  YYYY)	: ");
            day = input.nextInt();
            mth = input.nextInt();
            yr = input.nextInt();
            pickUpDate = new GregorianCalendar(yr,mth-1,day);
            if ((day > 31 || day < 1) || (mth > 12 || mth < 1))
               System.out.println("Error: Invalid Pick Up Date");
         }while ((day > 31 || day < 1) || (mth > 12 || mth < 1));
         do
         {
            System.out.print("Enter Return Date (DD  MM  YYYY)	: ");
            day = input.nextInt();
            mth = input.nextInt();
            yr = input.nextInt();
            returnDate = new GregorianCalendar(yr,mth-1,day);
            if ((day > 31 || day < 1) || (mth > 12 || mth < 1))
               System.out.println("Error: Invalid Return Date");
         }while ((day > 31 || day < 1) || (mth > 12 || mth < 1));
         long timeInMillisec = (returnDate.getTimeInMillis() - pickUpDate.getTimeInMillis());
         days = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
         if (days < 0)
            System.out.println("Error: Invalid input of dates. Please re-enter both dates.");
      }while (days < 0);
      Rental r = new Rental(c, pickUpDate, returnDate, v);
      rentalList.add(r);
      c.addRental(r);
      r.setRentalNo(rentalList.size()+1);
      v.setAvailable(false);
      availVehicleList.remove(v);
      rentalHistory.add(r);
      System.out.printf("Total rental charges is $%.2f\n", r.calculateRental());
   }
   
   // show details of rented vehicles
   public static void listRentalDetails(ArrayList rentalList)
   {
      if (rentalList.size() == 0)
      {
         System.out.println("No Rentals.");
         return;
      }
      Scanner input = new Scanner(System.in);
      int opt;
      do
      {
         System.out.print("Enter 1 for Cars, 2 for Trucks : ");
         opt = input.nextInt();
         if (opt == 1)
         {
            boolean empty = true;
            for (int i=0; i<rentalList.size(); i++)
            {
               Rental r = (Rental) rentalList.get(i);
               Vehicle v = (Vehicle) r.getVehicle();
               if (v instanceof Car)
                  empty = false;
            }
            if (empty == true)
            {
               System.out.println("No Rentals.");
               return;
            }
            System.out.println("Rentals for Cars:");
            System.out.printf("%-9s %-8s %-6s %-9s %-10s %-10s %-10s\n", "Reg No", "Make", "Model", "Rental No", "Customer", "Pick Up", "Return");
            for (int i=0; i<rentalList.size(); i++)
            {
               Rental r = (Rental) rentalList.get(i);
               Vehicle v = (Vehicle) r.getVehicle();
               Customer c = (Customer) r.getCustomer();
               String pickupDate = dateConverter(r.getPickupDate());
               String returnDate = dateConverter(r.getReturnDate());
               if (v instanceof Car)
                  System.out.printf("%-9s %-8s %-6s %-9d %-10s %-10s %-10s\n", c.getIcNo(), v.getMake(), v.getModel(), r.getRentalNo(), c.getName(), pickupDate, returnDate);
            }
         }
         else if (opt == 2)
         {
            boolean empty = true;
            for (int i=0; i<rentalList.size(); i++)
            {
               Rental r = (Rental) rentalList.get(i);
               Vehicle v = (Vehicle) r.getVehicle();
               if (v instanceof Truck)
                  empty = false;
            }
            if (empty == true)
            {
               System.out.println("No Rentals.");
               return;
            }
            System.out.println("Rentals for Trucks:");
            System.out.printf("%-9s %-8s %-6s %-9s %-10s %-10s %-10s\n", "Reg No", "Make", "Model", "Rental No", "Customer", "Pick Up", "Return");
            for (int i=0; i<rentalList.size(); i++)
            {
               Rental r = (Rental) rentalList.get(i);
               Vehicle v = (Vehicle) r.getVehicle();
               Customer c = (Customer) r.getCustomer();
               String pickupDate = dateConverter(r.getPickupDate());
               String returnDate = dateConverter(r.getReturnDate());
               if (v instanceof Truck)
                  System.out.printf("%-9s %-8s %-6s %-9d %-10s %-10s %-10s\n", c.getIcNo(), v.getMake(), v.getModel(), r.getRentalNo(), c.getName(), pickupDate, returnDate);
            }
         }
         if (opt != 1 && opt != 2)
            System.out.println("Error: Invalid Input");
      }while (opt != 1 && opt != 2);
   }
   
   // allow registered customers to return their rented vehicles
   public static void returnVehicle(ArrayList rentalList, ArrayList customerList, ArrayList availVehicleList)
   {
      Scanner input = new Scanner(System.in);
      String icNo;
      boolean error = true;
      listCustomers(customerList);
      Customer c = null;
      do
      {
         System.out.print("Enter IC number of customer : ");
         icNo = input.next();
         for (int i=0; i<customerList.size(); i++)
         {
            c = (Customer) customerList.get(i);
            if (icNo.equalsIgnoreCase(c.getIcNo()))
            {
               error = false;
               break;
            }
         }
         if (c.getRentalList().size() == 0 && icNo.equalsIgnoreCase(c.getIcNo()))
         {
            System.out.println("No Rentals.");
            return;
         }
         if (error == true)
            System.out.println("Error: Invalid Input");
      }while (error == true);
      error = true;
      Rental r = null;
      int x = 1;
      System.out.printf("%s\t%-8s\t%-8s\t%s\t%13s\n", "S/No", "Reg No", "Make", "Model", "Daily Rate($)");
      for (int i=0; i<c.getRentalList().size(); i++)
      {
         r = (Rental) c.getRentalList().get(i);
         Vehicle v = (Vehicle) r.getVehicle();
         System.out.printf("%d\t%-8s\t%-8s\t%s\t%13.2f\n", x++, v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate());
      }
      do
      {
         System.out.print("Enter registration number of the vehicle : ");
         String regNo = input.next();
         for (int i=0; i<rentalList.size(); i++)
         {
            r = (Rental) rentalList.get(i);
            if (regNo.equalsIgnoreCase(r.getVehicle().getRegNo()))
            {
               error = false;
               break;
            }
         }
         if (error == true)
            System.out.println("Error: Invalid Input");
      }while (error == true);
      int day, mth, yr, days;
      Calendar returnDate;
      do
      {
         System.out.print("Enter Return Date (DD  MM  YYYY)	: ");
         day = input.nextInt();
         mth = input.nextInt();
         yr = input.nextInt();
         returnDate = new GregorianCalendar(yr,mth-1,day);
         long timeInMillisec = (returnDate.getTimeInMillis() - r.getPickupDate().getTimeInMillis());
         days = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
         if ((day > 31 || day < 1) || (mth > 12 || mth < 1) || yr <= 0 || days < 0)
            System.out.println("Error: Invalid Return Date");
      }while ((day > 31 || day < 1) || (mth > 12 || mth < 1) || yr <= 0 || days < 0);
      r.setReturnDate(returnDate);
      r.getVehicle().setAvailable(true);
      availVehicleList.add(r.getVehicle());
      rentalList.remove(r);
      System.out.printf("Total rental charges is $%.2f\n", r.calculateRental());
   }
   
   // display details of the most rented vehicles
   public static void listMostRentedVehicle(ArrayList rentalHistory, ArrayList vehicleList)
   {
      Scanner input = new Scanner(System.in);
      int count, opt;
      do
      {
         System.out.print("Enter 1 for Cars, 2 for Trucks : ");
         opt = input.nextInt();
         if (opt != 1 && opt != 2)
            System.out.println("Error: Invalid Input");
      }while (opt != 1 && opt != 2);
      int highest = 0;
      if (opt == 1)
      {
         System.out.printf("%s\t%-8s\t%-8s\t%s\t%13s\t%s\n", "S/No", "Reg No", "Make", "Model", "Daily Rate($)", "Availability");
         for (int i=0; i<vehicleList.size(); i++)
         {
            count = 0;
            Vehicle v = (Vehicle) vehicleList.get(i);
            if (v instanceof Car)
            {
               for (int x=0; x<rentalHistory.size(); x++)
               {
                  Rental r = (Rental) rentalHistory.get(x);
                  if (v.getRegNo().equals(r.getVehicle().getRegNo()))
                     count++;
               }
               if (count > highest)
                  highest = count;
            }
         }
         for (int i=0; i<vehicleList.size(); i++)
         {
            count = 0;
            Vehicle v = (Vehicle) vehicleList.get(i);
            if (v instanceof Car)
            {
               for (int x=0; x<rentalHistory.size(); x++)
               {
                  Rental r = (Rental) rentalHistory.get(x);
                  if (v.getRegNo().equals(r.getVehicle().getRegNo()))
                     count++;
               }
               if (highest == count)
               {
                  if (v.getAvailable() == true)
                     System.out.printf("%d\t%-8s\t%-8s\t%s\t%13.2f\tYes\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate());
                  else
                     System.out.printf("%d\t%-8s\t%-8s\t%3s\t%13.2f\tNo\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate());
               }
            }
         }
      }
      else if (opt == 2)
      {
         System.out.printf("%s\t%-8s\t%-8s\t%s\t%13s\t%s\n", "S/No", "Reg No", "Make", "Model", "Daily Rate($)", "Availability");
         for (int i=0; i<vehicleList.size(); i++)
         {
            count = 0;
            Vehicle v = (Vehicle) vehicleList.get(i);
            if (v instanceof Truck)
            {
               for (int x=0; x<rentalHistory.size(); x++)
               {
                  Rental r = (Rental) rentalHistory.get(x);
                  if (v.getRegNo().equals(r.getVehicle().getRegNo()))
                     count++;
               }
               if (count > highest)
                  highest = count;
            }
         }
         for (int i=0; i<vehicleList.size(); i++)
         {
            count = 0;
            Vehicle v = (Vehicle) vehicleList.get(i);
            if (v instanceof Truck)
            {
               for (int x=0; x<rentalHistory.size(); x++)
               {
                  Rental r = (Rental) rentalHistory.get(x);
                  if (v.getRegNo().equals(r.getVehicle().getRegNo()))
                     count++;
               }
               if (highest == count)
               {
                  if (v.getAvailable() == true)
                     System.out.printf("%d\t%-8s\t%-8s\t%s\t%13.2f\tYes\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate());
                  else
                     System.out.printf("%d\t%-8s\t%-8s\t%3s\t%13.2f\tNo\n", (i+1), v.getRegNo(), v.getMake(), v.getModel(), v.getDailyRate());
               }
            }
         }
      }
   }
   
   // display details of rental history
   public static void listRentalHistoryDetailsOfCustomer(ArrayList rentalList, ArrayList customerList, ArrayList vehicleList)
   {
      Scanner input = new Scanner(System.in);
      listCustomers(customerList);
      System.out.print("Enter IC number of customer : "); // prompt IC number of customer from user
      String nric = input.nextLine();
      int sday, smth, syr;
      int eday, emth, eyr;
      int days;
      Calendar startDate, endDate;
      do
      {
         do
         {
            System.out.print("Enter start date (DD  MM  YYYY)	: "); // prompt start date from user
            sday = input.nextInt();
            smth = input.nextInt();
            syr = input.nextInt();
            startDate = new GregorianCalendar(syr,smth-1,sday);
            if ((sday > 31 || sday < 1) || (smth > 12 || smth < 1))
               System.out.println("Error: Invalid Return Date");
         }while ((sday > 31 || sday < 1) || (smth > 12 || smth < 1));
         do
         {
            System.out.print("Enter end date (DD  MM  YYYY)	: "); // prompt end date from user
            eday = input.nextInt();
            emth = input.nextInt();
            eyr = input.nextInt();
            endDate = new GregorianCalendar(eyr,emth-1,eday);
            if ((eday > 31 || eday < 1) || (emth > 12 || emth < 1))
               System.out.println("Error: Invalid Return Date");
         }while ((eday > 31 || eday < 1) || (emth > 12 || emth < 1));
         long timeInMillisec = (endDate.getTimeInMillis() - startDate.getTimeInMillis());
         days = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
         if (days < 0)
            System.out.println("Error: Invalid input of dates. Please re-enter both dates."); // check if dates entered are valid
      }while (days < 0);
      System.out.printf("%-9s %-8s %-6s %-9s %-10s %-10s %-10s\n", "Reg No", "Make", "Model", "Rental No", "Customer", "Pick Up", "Return");
      int daysfromstart, daysfromend;
      for (int i=0; i<rentalList.size(); i++)
      {
         Rental r = (Rental) rentalList.get(i);
         Customer c = (Customer) r.getCustomer();
         Vehicle v = (Vehicle) r.getVehicle();
         Calendar startRentalDate = r.getPickupDate();
         long timeInMillisec = (startRentalDate.getTimeInMillis() - startDate.getTimeInMillis()); // calculate amount of days from start date to rental
         daysfromstart = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
         timeInMillisec = (endDate.getTimeInMillis() - startDate.getTimeInMillis()); // calculate amount of days from start date to rental
         daysfromend = (int) (timeInMillisec / (24 * 60 * 60 * 1000));
         if (daysfromstart >= 0 && daysfromend >= 0) // if rental date is inbetween the 2 dates
         {
            String startingDate = dateConverter(r.getPickupDate());
            String endingDate = dateConverter(r.getReturnDate());
               System.out.printf("%-9s %-8s %-6s %-9d %-10s %-10s %-10s\n", c.getIcNo(), v.getMake(), v.getModel(), r.getRentalNo(), c.getName(), startingDate, endingDate); // display information of rental between the dates
         }
      }
   }
   
   // convert Calendar objects to String used for outputs
   public static String dateConverter(Calendar date)
   {
      int yr  = date.get(Calendar.YEAR);
      int mth = date.get(Calendar.MONTH) + 1;
      int day = date.get(Calendar.DATE);
      DecimalFormat df = new DecimalFormat("00");
      String convertedDate = df.format(day) + "/" + df.format(mth) + "/" + yr;
      return convertedDate;
   }
}