package IntermediateLevel;
import java.io.*;
import java.util.*;
class Room{
	int roomNumber;
	String type;
	double price;
	boolean isBooked;

public Room(int roomNumber,String type,double price) {
	this.roomNumber=roomNumber;
	this.type=type;
	this.price=price;
	this.isBooked=false;
	
}
public String toString() {
	return "Room -"+roomNumber+" ["+type+"]Rs."+price+"-"+(isBooked?"Booked":"Available");
	
}
}
class Hotel{
	List<Room> rooms= new ArrayList<>();
	final String FILE_NAME="booking.txt";
	
	public Hotel() {
		rooms.add(new Room(101,"Single",1500));
		rooms.add(new Room(102,"Double",2500));
		rooms.add(new Room(103,"Suite",5000));
		rooms.add(new Room(104,"Single",1500));
		rooms.add(new Room(105,"Single",1500));
		rooms.add(new Room(106,"Double",2500));
		loadBookingsFromFile();
		
	}
	public void showAvailableRooms() {
		for(Room room:rooms) {
			if(!room.isBooked) {
				System.out.println(room);
			}
		}
	}
	public void bookRoom(int roomNumber) {
		for(Room room:rooms) {
			if(room.roomNumber==roomNumber && !room.isBooked) {
				room.isBooked=true;
				saveBookingsToFile();
				System.out.println("Room -"+roomNumber+" booked successfully!");
				return;
				
				
			}
			
		}
		System.out.println("Room not available or doesn't exist");
	}
	public void cancelBooking(int roomNumber) {
		for(Room room:rooms) {
			if(room.roomNumber==roomNumber && room.isBooked) {
				room.isBooked=false;
				saveBookingsToFile();
				System.out.println("Booking for room "+roomNumber+" Canceled");
				return;
				
			}
			}
		System.out.println("No booking found for this room.");
			
	}
	private void saveBookingsToFile() {
		try(BufferedWriter bw=new BufferedWriter(new FileWriter(FILE_NAME))){
			for(Room room:rooms) {
				bw.write(room.roomNumber+","+room.isBooked);
				bw.newLine();
				
			}
		}catch(IOException e) {
			System.out.println("Error saving bookings:"+e.getMessage());
		}
	}

	private void loadBookingsFromFile() {
		File file= new File(FILE_NAME);
		if(!file.exists())return;
		try(BufferedReader br=new BufferedReader(new FileReader(FILE_NAME))){
			String line;
			while((line=br.readLine())!=null) {
				String[] data=line.split(",");
				int roomNumber=Integer.parseInt(data[0]);
				boolean isBooked=Boolean.parseBoolean(data[1]);
				for(Room room:rooms) {
					if(room.roomNumber==roomNumber) {
						room.isBooked=isBooked;
					}
				}
			}
		}catch(IOException e) {
			System.out.println("Error loading Bookings:"+e.getMessage());
		}
		
		
	}
}
public class HotelReservationSystem {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		Hotel hotel= new Hotel();
		int choice;
		do {
			System.out.println("\n============Hotel Reservation System=============");
			System.out.println("1.Show Available Rooms");
			System.out.println("2.Book Room");
			System.out.println("3.Cancel Booking");
			System.out.println("4.Exit");
			System.out.println("Enter Your choice:");
			choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				hotel.showAvailableRooms();
				break;
			case 2:
				System.out.println("Enter room number to book:");
				int bookRoomNo=sc.nextInt();
				hotel.bookRoom(bookRoomNo);
				break;
			case 3:
				System.out.println("Enter room number to Cancel:");
				int cancelRoomNo=sc.nextInt();
				hotel.cancelBooking(cancelRoomNo);
				break;
			case 4:
				System.out.println("Thank you for using the System!");
				break;
			default:
				System.out.println("Invalid choice.Try again.");
				
			}
		}while(choice !=4); 
		 
		sc.close();
	}
			
	}


