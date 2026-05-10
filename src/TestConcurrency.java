
import java.util.List;

public class TestConcurrency {
    public static void main(String[] args) throws InterruptedException {
        BookingManager manager = new BookingManager();
        String flightId = "FL123";

        // FIX: BookingThread now needs User objects, not just name strings
        List<User> passengers = List.of(
            new User("Ali",    "A", "ali@mail.com",    "P001", 25),
            new User("Sara",   "B", "sara@mail.com",   "P002", 30),
            new User("Omar",   "C", "omar@mail.com",   "P003", 22),
            new User("Lina",   "D", "lina@mail.com",   "P004", 28),
            new User("Khalid", "E", "khalid@mail.com", "P005", 35),
            new User("Nora",   "F", "nora@mail.com",   "P006", 27),
            new User("Faisal", "G", "faisal@mail.com", "P007", 31),
            new User("Hana",   "H", "hana@mail.com",   "P008", 24),
            new User("Tariq",  "I", "tariq@mail.com",  "P009", 29),
            new User("Reem",   "J", "reem@mail.com",   "P010", 26)
        );

        Thread[] bookingThreads = new Thread[passengers.size()];
        for (int i = 0; i < passengers.size(); i++) {
            bookingThreads[i] = new Thread(
                new BookingThread(manager, flightId, passengers.get(i), "economy", "A" + (i + 1))
            );
        }

        Thread searchThread = new Thread(new SearchThread(manager, flightId, "Mona"));

        for (Thread t : bookingThreads) t.start();
        searchThread.start();

        for (Thread t : bookingThreads) t.join();
        searchThread.join();

        System.out.println("\n=== Final Result ===");
        System.out.println(manager.searchFlight(flightId));
    }
}