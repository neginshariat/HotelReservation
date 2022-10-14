package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class HotelResource {
    CustomerService customerService= CustomerService.getCustomerService();
    ReservationService reservationService= ReservationService.getReservationService();
    private final static HotelResource hotelResource= new HotelResource();
    private HotelResource(){}
    public static HotelResource getHotelResource(){return hotelResource;}
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void createCustomer(String firstName, String lastName,String email){
        customerService.addCustomer(firstName,lastName,email);
    }
    public IRoom getRoom( String room){
       return reservationService.getIRoom(room);
    }
/*    public IRoom getRoom(){
        return reservationService.getIRoom();
    }*/
    public void addRoom(IRoom iRoom){
         reservationService.addRoom(iRoom);
    }
    public Reservation bookRoom(String email, IRoom room, Date checkIn, Date checkOut){
       return reservationService.reserveRoom(getCustomer(email),room,checkIn,checkOut);
    }
    public Collection<Reservation> getCustomerReservations(){
        return reservationService.getAllReservations();
    }

    public boolean isConflict(String iRoom, Date checkIn){
       return reservationService.isConflict(iRoom,checkIn);

    }
    public boolean isRoomNumberCorrect(String roomNumber){
        return reservationService.isRoomNumberCorrect(roomNumber);

    }

}
