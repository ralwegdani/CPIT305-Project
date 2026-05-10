

public class BookingThread implements Runnable {
    private BookingManager bookingManager;
    private String flightId;
    private User   passenger;   // FIX: was String passengerName — bookSeat now needs a User
    private String seatType;
    private String seatNumber;

    public BookingThread(BookingManager bookingManager, String flightId,
                         User passenger, String seatType, String seatNumber) {
        this.bookingManager = bookingManager;
        this.flightId       = flightId;
        this.passenger      = passenger;
        this.seatType       = seatType;
        this.seatNumber     = seatNumber;
    }

    @Override
    public void run() {
        System.out.println("[ATTEMPT] " + passenger.getF_name() + " attempting to book flight " + flightId);
        // FIX: updated to match new bookSeat signature
        String result = bookingManager.bookSeat(flightId, passenger, seatType, seatNumber);
        System.out.println("[RESPONSE] " + result);
    }
}
