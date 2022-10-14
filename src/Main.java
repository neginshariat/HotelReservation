import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    private final static HotelResource hotelResource= HotelResource.getHotelResource();
    public final static AdminResource adminResource= AdminResource.getAdminResource();
    //public static HashMap<IRoom,Date> roomDateHashMap= new HashMap<>();
     static Scanner scanner=new Scanner(System.in);
    public static void Main() {

        System.out.println( "1. Find and reserve a room\n" + "2. See my reservations\n" + "3. Create an Account\n" + "4. Admin menu\n" + "5. Exit\n");
        String input = scanner.next();
        try {
            switch (input.charAt(0)){
                case '1':
                    findRoom();
                    Main();
                    break;
                case '2':
                    printReservation();
                    Main();
                    break;
                case '3':
                    createAccount();
                    Main();
                    break;
                case '4':
                    adminMenu();
                    break;
                case '5':
                    exit();
                    break;
                default:
                    System.out.println("please enter the numbers between 1 to 5");
                    Main();
                    break;
            }

        } catch (Exception e){
            System.out.println("please enter the number 1 to 5.");
            Main();

        }

    }
    public static void findRoom() {
        System.out.println("Please enter your check in date yyyy-MM-dd(2020-02-01):");
        String checkInInput = scanner.next();
        Date checkIn = convertToDate(checkInInput);
        System.out.println("Please enter your check out Date yyyy-MM-dd(2020-02-01):");
        String checkOutInput = scanner.next();
        Date checkOut = convertToDate(checkOutInput);
        Collection<IRoom> availableRoom = adminResource.getAllRooms();

        if (availableRoom.isEmpty()) {
            System.out.println("Hotel has no available rooms at the moment, admin will Enter the free rooms as soon as they are available. ");
            Main();
        } else {
            Collection<IRoom> rooms = adminResource.getAllRooms();
            for (IRoom roomList : rooms) {
                System.out.println("available room: " + roomList);
            }
            System.out.println("Which room number do you like to reserve? ");
            String roomString = scanner.next();

            if (!hotelResource.isRoomNumberCorrect(roomString)){
                System.out.println("please enter the room number in right format as you see in the list .");
                Main();
            }
            IRoom room= hotelResource.getRoom(roomString);
            //System.out.println("room taken: " + room);
            //System.out.println("room number: "+ room.getRoomNumber());
            if (hotelResource.isConflict(room.getRoomNumber(),checkIn)){
                System.out.println("Chosen Room is booked for Chosen Date, here is alternative Date:");
                alternativeRoom(checkIn,checkOut,room);
            }else {

                System.out.println("Please enter your email: eg. name@domain.com");
                String email = scanner.next();

                hotelResource.bookRoom(email, room, checkIn, checkOut);
                hotelResource.getCustomerReservations();
                adminResource.addReservation(email, room, checkIn, checkOut);
                System.out.println("the room from: " + checkInInput + " to: " + checkOutInput);
                Main();

            }

            }
    }




    public static void alternativeRoom(Date checkIn,Date checkOut,IRoom room) {
        Calendar newInDate = Calendar.getInstance();
        Calendar newOutDate = Calendar.getInstance();
        newInDate.setTime(checkIn);
        newInDate.add(Calendar.DATE, 7);

        newOutDate.setTime(checkOut);
        newOutDate.add(Calendar.DATE, 7);
        System.out.println("from "+newInDate.getTime()+ " to "+newOutDate.getTime());

        System.out.println("if you want to reserve room for this alternative date press 'y' if not press 'n'.");
        String input= scanner.next();
        if (input.charAt(0)=='y'){
            System.out.println("Please enter your email: eg. name@domain.com:");
            String email= scanner.next();

            hotelResource.getCustomer(email);

            if (hotelResource.getCustomer(email)== null){
                System.out.println("please at first create your account after that you are able to book a room.");
                Main();
            }else {
                //IRoom room =addExtraRoom();
                hotelResource.bookRoom(email,room,checkIn,checkOut);
                //hotelResource.addRoom(room);
                hotelResource.getCustomerReservations();
                adminResource.addReservation(email,room,newInDate.getTime(),newOutDate.getTime());
                System.out.println("the room from: "+ newInDate.getTime() +" to: "+ newOutDate.getTime());
                Main();
            }
        }else {
            System.out.println("thanks for using reservation app");
            exit();
        }

    }
/*    public static boolean isBooked(IRoom room, Date checkIn) {
        if (roomDateHashMap.containsValue(checkIn)) {
            for (Map.Entry<IRoom, Date> entry : roomDateHashMap.entrySet()) {
                if (Objects.equals(entry.getValue(), checkIn) && entry.getKey().equals(room)) {
                    System.out.println("result" + room);
                    return true;
                }
            }
        }
        System.out.println("resultend" + room);
        return false;
    }*/

    public static Room addExtraRoom(){
        Random random= new Random();
        Integer roomInt= random.nextInt();
        String test;
        if (roomInt<0){
           Integer roomIntResult = -roomInt;
             test= Integer.toString(roomIntResult);
        }else {
             test= Integer.toString(roomInt);
        }
        String roomNumber= test.substring(0,4);
        Double roomPrice= 100.0+random.nextDouble();
        RoomType roomType= RoomType.DOUBLE;
        Room room= new Room(roomNumber,roomPrice,roomType);
        hotelResource.addRoom(room);
        System.out.println("That's the recommended room: "+room);
        return room;
    }
    public static Room addRoom(){
        System.out.println("please enter room number:");
        String roomNumber= scanner.next();
        System.out.println("please enter room price:");
        Double roomPrice= scanner.nextDouble();
        System.out.println("please enter S for single room and D for double room:");
        RoomType roomType= Admin.roomTypeConvert(scanner.next());
        Room room= new Room(roomNumber,roomPrice,roomType);
        hotelResource.addRoom(room);
        return room;
    }
    public static Date convertToDate(String input) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat dataFormatter= new SimpleDateFormat("MMMM dd, YYYY");
        try{
            final Date convertDate= formatter.parse(input);
            System.out.println(convertDate);
            return convertDate;
        }catch (ParseException e){
            System.out.println("please enter the date in right format. e.g 2020-02-01");
            Main();
        }

        return null;
    }

    public static void printReservation(){
        adminResource.displayAllReservations();
    }
    public static void createAccount(){
        System.out.println("Please enter your first name:");
        String name= scanner.next();
        System.out.println("Please enter your last name:");
        String lastName= scanner.next();
        System.out.println("Please enter your email: eg. name@domain.com");
        String email= scanner.next();
            hotelResource.createCustomer(name, lastName,email );
            System.out.println("account is created.");
    }
    public static void adminMenu() {
        Admin.Admin();
    }
    public static void exit(){
        System.out.println("Exit");
    }

/* developed by
    Negin Shariat */
}
