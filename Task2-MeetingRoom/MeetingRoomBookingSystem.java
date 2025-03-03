import java.util.*;

// Enum for room features
enum RoomFeature {
    PROJECTOR, VIDEO_CONFERENCING, WHITEBOARD, CONFERENCE_PHONE, AIR_CONDITIONING;
}

// Class representing a Meeting Room
class MeetingRoom {
    private String roomId;
    private String roomName;
    private int capacity;
    private EnumSet<RoomFeature> features;

    public MeetingRoom(String roomId, String roomName, int capacity, EnumSet<RoomFeature> features) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.features = features;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public EnumSet<RoomFeature> getFeatures() {
        return features;
    }

    @Override
    public String toString() {
        return roomName;
    }
}

// Class to manage meeting room scheduling
class RoomScheduler {
    private Map<String, MeetingRoom> meetingRooms;

    public RoomScheduler() {
        this.meetingRooms = new HashMap<>();
    }

    // Add a new meeting room
    public void addMeetingRoom(MeetingRoom room) {
        meetingRooms.put(room.getRoomId(), room);
        System.out.println("Room added: " + room.getRoomName() + ", ID: " + room.getRoomId());
    }

    // Book a room with required features
    public void bookRoom(String roomId, EnumSet<RoomFeature> requiredFeatures) {
        MeetingRoom room = meetingRooms.get(roomId);
        if (room != null) {
            if (room.getFeatures().containsAll(requiredFeatures)) {
                System.out.println("Room " + roomId + " booked successfully.");
            } else {
                System.out.println("Room " + roomId + " does not meet the required features.");
            }
        } else {
            System.out.println("Room ID " + roomId + " not found.");
        }
    }

    // List available rooms that meet required features
    public void listAvailableRooms(EnumSet<RoomFeature> requiredFeatures) {
        List<String> availableRooms = new ArrayList<>();
        for (MeetingRoom room : meetingRooms.values()) {
            if (room.getFeatures().containsAll(requiredFeatures)) {
                availableRooms.add(room.getRoomName());
            }
        }
        System.out.println("Available rooms with " + requiredFeatures + ": " + availableRooms);
    }
}

// Driver class to test functionality
public class MeetingRoomBookingSystem {
    public static void main(String[] args) {
        RoomScheduler scheduler = new RoomScheduler();

        // Adding meeting rooms
        scheduler.addMeetingRoom(new MeetingRoom("001", "Boardroom", 20,
                EnumSet.of(RoomFeature.PROJECTOR, RoomFeature.CONFERENCE_PHONE, RoomFeature.AIR_CONDITIONING)));
        scheduler.addMeetingRoom(new MeetingRoom("002", "Strategy Room", 10,
                EnumSet.of(RoomFeature.WHITEBOARD, RoomFeature.AIR_CONDITIONING)));

        // Booking a room
        scheduler.bookRoom("001", EnumSet.of(RoomFeature.PROJECTOR, RoomFeature.CONFERENCE_PHONE));
        
        // Listing available rooms
        scheduler.listAvailableRooms(EnumSet.of(RoomFeature.AIR_CONDITIONING));
    }
}
