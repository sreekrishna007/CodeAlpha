import java.util.*;

class Room{
  int roomNumber;
  String category;
  boolean isAvailable;
  double price;

  public Room(int roomNumber,String category,double price){
    this.roomNumber=roomNumber;
    this.category=category;
    this.price=price;
    this.isAvailable=true;
  }
}

class Reservation{
  int bookingId;
  String customerName;
  Room room;
  boolean isPaid;

  public Reservation(int bookingId,String customerName,Room room){
    this.bookingId=bookingId;
    this.customerName=customerName;
    this.room=room;
    this.isPaid=false;
  }

  public void processPayment(){
    this.isPaid=true;
    System.out.println("Payment successful for"+customerName+".Booking confirmed.");
  }

  public void displayBookingDetails(){
    System.out.println("\nBooking Details.");
    System.out.println("Booking ID:"+bookingId);
    System.out.println("Customer Name:"+customerName);
    System.out.println("Room Number:"+room.roomNumber);
    System.out.println("Category:"+room.category);
    System.out.println("Price:$"+room.price);
    System.out.println("Payment Status:"+(isPaid?"Paid":"Pending"));
  }
}

public class HotelReservationSystem{
  private static final List<Room>rooms=new ArrayList<>();
  private static final List<Reservation>reservations=new ArrayList<>();
  private static int bookingCounter=1001;
  
  public static void main(String[]args){
   initializeRooms();
   Scanner scanner=new Scanner(System.in);

   while(true){
     System.out.println("\nHotel Reservation System");
     System.out.println("1.View Available Rooms");
     System.out.println("2.Make a Reservation");
     System.out.println("3.View Booking Details");
     System.out.println("4. Exit");
     System.out.print("Choose an option:");
     int choice=scanner.nextInt();
     scanner.nextLine();

     switch(choice){
       case 1:
         displayAvailableRooms();
         break;
       case 2:
         makeReservation(scanner);
         break;
       case 3:
         viewBookingDetails(scanner);
         break;
       case 4:
         System.out.println("Exiting...Thank you for using our hotel reservation system.");
         scanner.close();
         return;
       default:
         System.out.println("Invalid choice.Please try again");
     }
    }
  } 
  
  private static void initializeRooms(){
    rooms.add(new Room(101,"Single",100.0));
    rooms.add(new Room(102,"Single",100.0));
    rooms.add(new Room(201,"Double",150.0));
    rooms.add(new Room(202,"Double",150.0));
    rooms.add(new Room(301,"Suite",250.0));
  }

  private static void displayAvailableRooms(){
    System.out.println("\nAvailable Rooms:");
    for(Room room:rooms){
      if(room.isAvailable){
        System.out.println("Room"+room.roomNumber+"-"+room.category+"-$"+room.price);
      }
    }
  }
  
  private static void makeReservation(Scanner scanner){
    System.out.print("\nEnter your name:");
    String customerName=scanner.nextLine();
    System.out.print("Enter room category(Single/Double/Suite):");
    String category=scanner.nextLine();

    Room selectedRoom=null;
    for(Room room:rooms){
      if(room.isAvailable && room.category.equalsIgnoreCase(category)){
        selectedRoom=room;
        break;
      }
    }

    if(selectedRoom==null){
      System.out.println("No available rooms in the selected category.");
      return;
    }

    selectedRoom.isAvailable=false;
    Reservation reservation=new Reservation(bookingCounter++,customerName,selectedRoom);
    reservations.add(reservation);
    System.out.println("Room"+selectedRoom.roomNumber+"reserved for"+customerName);

    System.out.print("Proceed to payment?(yes/no):");
    if(scanner.nextLine().equalsIgnoreCase("yes")){
            reservation.processPayment();
    }
  }

  private static void viewBookingDetails(Scanner scanner){
     System.out.print("\nEnter your booking ID:");
     int bookingId=scanner.nextInt();

     for(Reservation res:reservations){
       if(res.bookingId==bookingId){
          res.displayBookingDetails();
          return;
        }
      }
      System.out.println("Booking not found.");
  }
}



