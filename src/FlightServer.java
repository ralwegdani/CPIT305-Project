
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class FlightServer {

    public static void main(String[] args) {

        DB.getInstance();
        BookingManager manager = new BookingManager();

        try (ServerSocket serverSocket = new ServerSocket(5500)) {
            System.out.println("Airline Server is Online (Port 5500)...");

            while (true) {

                // accept() BLOCKS here until a client connects.
                // Once a client connects, it returns a Socket
                // representing that specific connection.
                Socket socket = serverSocket.accept();
                System.out.println("New connection from: " + socket.getInetAddress());

                // Create a handler for this client and run it in its own thread.
                // This immediately frees the main thread to go back
                // and wait for the next client connection.
                new Thread(new ClientHandler(socket, manager)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}