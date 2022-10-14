package model;

public class Room implements IRoom{
    final private String roomNumber;
    final private Double roomPrice;
    final private RoomType roomType;

    public Room (String roomNumber,Double roomPrice,RoomType roomType){
        this.roomNumber=roomNumber;
        this.roomPrice=roomPrice;
        this.roomType=roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomType=" + roomType +
                '}';
    }

}
