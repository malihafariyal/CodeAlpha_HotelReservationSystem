import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean available;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.available = true;
    }
}

class Reservation {
    String customerName;
    int roomNumber;

    Reservation(String customerName, int roomNumber) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Standard"));
        rooms.add(new Room(201, "Deluxe"));
        rooms.add(new Room(202, "Deluxe"));
        rooms.add(new Room(301, "Suite"));

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewRooms();
                    break;

                case 2:
                    System.out.print("Enter Customer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Room Number: ");
                    int roomNo = sc.nextInt();

                    bookRoom(name, roomNo);
                    break;

                case 3:
                    System.out.print("Enter Room Number to Cancel: ");
                    int cancelRoom = sc.nextInt();

                    cancelReservation(cancelRoom);
                    break;

                case 4:
                    viewReservations();
                    break;

                case 5:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    static void viewRooms() {
        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {
            System.out.println("Room " + room.roomNumber +
                    " | " + room.category +
                    " | " + (room.available ? "Available" : "Booked"));
        }
    }

    static void bookRoom(String name, int roomNo) {

        for (Room room : rooms) {
            if (room.roomNumber == roomNo && room.available) {

                room.available = false;
                reservations.add(new Reservation(name, roomNo));

                System.out.println("Payment Successful!");
                System.out.println("Room Booked Successfully!");
                return;
            }
        }

        System.out.println("Room Not Available!");
    }

    static void cancelReservation(int roomNo) {

        for (Room room : rooms) {
            if (room.roomNumber == roomNo) {
                room.available = true;
            }
        }

        reservations.removeIf(r -> r.roomNumber == roomNo);

        System.out.println("Reservation Cancelled!");
    }

    static void viewReservations() {

        System.out.println("\nReservation Details:");

        for (Reservation r : reservations) {
            System.out.println("Customer: " + r.customerName +
                    " | Room: " + r.roomNumber);
        }
    }
}
