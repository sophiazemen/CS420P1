import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatInterface chat = (ChatInterface) registry.lookup("ChatService");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter a message (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) break;

                chat.sendMessage(message);
                System.out.println("Server Response: " + chat.receiveMessage());
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
