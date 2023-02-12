import java.io.*;
import java.net.Socket;

public class TCPClient {

    private static Socket clientSocket; //сокет для общения
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4004);
                // нам нужен ридер читающий с консоли, иначе как
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Matrix order?:");
                int size = Integer.parseInt(reader.readLine());

                out.write(size + "\n");

                for (int i = 0; i < size; i++){

                    for (int j = 0; j < size; j++){
                        System.out.println("["+i+"]["+j+"]: ");
                        out.write(reader.readLine() + "\n");
                    }

                }

                out.flush();
                String divres = in.readLine();
                System.out.println("Main/second diag: " + Float.parseFloat(divres));

            }
            finally {
                System.out.println("Client closed...");
                clientSocket.close();
                in.close();
                out.close();
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}