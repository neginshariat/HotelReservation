package service;

import model.*;
import java.util.*;

public class ReservationService {
    private final static ReservationService reservationService= new ReservationService();
    private ReservationService(){}
    public static ReservationService getReservationService(){return reservationService;}
    HashMap<String,Collection<Reservation>> customerReservationHashMap = new HashMap<>();
    HashMap<String,IRoom> iRoomHashMap = new HashMap<>();
    HashMap<String,Date> conflictMap = new HashMap<>();

    public void addRoom(IRoom iRoom){
            iRoomHashMap.put(iRoom.getRoomNumber(), iRoom);
    }
     void noAvailableRoom(){
        System.out.println("there is no available room on this date.");
    }
    public IRoom getIRoom(String room){
       return iRoomHashMap.get(room);
    }

    public Reservation reserveRoom(Customer customer, IRoom room, Date checkInDate, Date checkoutDate){
        Collection<Reservation> reservationList= getAllReservations();
        Reservation reservation= new Reservation(customer,room,checkInDate,checkoutDate);
        reservationList.add(reservation);;
        return reservation ;
    }
    public boolean isConflict(String iRoom,Date checkIn){
        //System.out.println("room "+ iRoom + "date : "+ checkIn);

        if (conflictMap.isEmpty()){

            return false;
        } else{
            for (Map.Entry<String,Date> entry: conflictMap.entrySet()){
               // System.out.println("Entry get key : "+ entry.getKey()+" Entry get value: "+ entry.getValue());
                if (entry.getKey().equals(iRoom) && entry.getValue().equals(checkIn)){
                   // System.out.println("here3 "+entry.getKey()+" +va+ "+entry.getValue() );
                    return true;
                }
            }

        }

     return false;
    }
    public void addReservation(Customer customer,IRoom iRoom,Date checkIn,Date checkOut){
        Collection<Reservation> reservationList= getAllReservations();
        Reservation reservation= new Reservation(customer,iRoom,checkIn,checkOut);
        reservationList.add(reservation);
        conflictMap.put(iRoom.getRoomNumber(),checkIn);
       // System.out.println("conflict map: "+ conflictMap);
        customerReservationHashMap.put(customer.getEmail(),reservationList);
    }
    public Collection<Reservation> getAllReservations(){
        Collection<Reservation> allReservation= new ArrayList<>();
        for (Collection<Reservation> reservation: customerReservationHashMap.values()){
            for (Reservation reservationValue: reservation){
               allReservation.add(reservationValue);
            }
        }
        return allReservation;
    }
    public Collection<IRoom> getAllRooms(){
        return iRoomHashMap.values();
    }
    public boolean isRoomNumberCorrect(String roomNumber){
        if (iRoomHashMap.containsKey(roomNumber)){
            return true;
        }
        return false;
    }
    public void printAllTheReservation(){
        for (Reservation reservation: getAllReservations()){
            System.out.println(reservation);
        }
    }
}
