import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

public class ChatServer implements ChatInterface {
    private final Queue<String> messages = new LinkedList<>();

    public ChatServer() {}

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        messages.add(message);
        System.out.println("Received: " + message);
    }

    @Override
    public synchronized String receiveMessage() throws RemoteException {
        return messages.poll();
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            ChatInterface stub = (ChatInterface) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", stub);

            System.out.println("Chat Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
