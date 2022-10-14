import api.AdminResource;
import model.RoomType;

import java.util.Scanner;

public class Admin {

    static AdminResource adminResource= AdminResource.getAdminResource();
    private final static Scanner scanner= new Scanner(System.in);
    public static void Admin() {
        System.out.println( "1. See all Customers\n" + "2. See all Rooms\n" + "3. See all Reservations\n" + "4. Add a Room\n" + "5. Back to the Main Menu\n");
        String input= scanner.next();
        switch (input.charAt(0)){
            case '1':
                allCustomers();
                Admin();
                break;
            case '2':
                allRooms();
                Admin();
                break;
            case '3':
                allReservations();
                Admin();
                break;
            case '4':
                Main.addRoom();
                Admin();
                break;
            case '5':
                Main.Main();
                break;
        }
    }
    public static void allCustomers(){
        System.out.println(adminResource.getAllCustomer());;
    }
    public static void allRooms(){
        System.out.println(adminResource.getAllRooms());
    }
    public static void allReservations(){
        adminResource.displayAllReservations();
    }

    public static RoomType roomTypeConvert(String room){
        if (room.charAt(0)== 'S'){
            return RoomType.SINGLE;

        } else if (room.charAt(0)== 'D') {
            return RoomType.DOUBLE;
        }else {
            throw new RuntimeException("input has to be S or D");
        }
    }
}
