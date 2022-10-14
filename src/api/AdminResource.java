package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.Date;

public class AdminResource {
    private static final AdminResource adminResource= new AdminResource();
    CustomerService customerService= CustomerService.getCustomerService();
    ReservationService reservationService= ReservationService.getReservationService();
    private AdminResource(){}
    public static AdminResource getAdminResource(){
        return adminResource;
    }
    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void addReservation(String email, IRoom iRoom, Date checkIn,Date checkOut){
        reservationService.addReservation(getCustomer(email),iRoom, checkIn,checkOut);

    }
   /* public IRoom availableRooms(IRoom iRoom){return reservationService.availableRooms(iRoom);}*/
 /*   public boolean checkEmail(String email){
        return customerService.checkEmail(email);
    }*/
    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }
    public Collection<Customer> getAllCustomer(){
        return customerService.getCustomers();
    }
    public void displayAllReservations(){
        reservationService.printAllTheReservation();
    }
}
