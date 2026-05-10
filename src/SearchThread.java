
public class SearchThread implements Runnable {
    private BookingManager bookingManager;
    private String flightId;
    private String userName;

    public SearchThread(BookingManager bookingManager, String flightId, String userName) {
        this.bookingManager = bookingManager;
        this.flightId       = flightId;
        this.userName       = userName;
    }

    @Override
    public void run() {
        System.out.println("[SEARCH] " + userName + " is searching for flight " + flightId);
        String result = bookingManager.searchFlight(flightId);
        System.out.println("[RESULT] For " + userName + ": " + result);
    }
}