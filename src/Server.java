import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Contacts App");
        System.out.println("Server started");
        System.out.println("Waiting for a client");
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("A new client is connected: " + socket);

                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                System.out.println("Assigning new thread for this client");
                ClientHandler clientHandler = new ClientHandler(socket, inputStream, outputStream);
                clientHandler.start();
            }
            catch (IOException i) {
                socket.close();
                System.out.println(i);
            }
        }
    }
}
