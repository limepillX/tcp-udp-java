import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPGuiServer {

    private static Socket clientSocket; //сокет для общения
    private static ServerSocket server; // серверсокет
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Server started!");
                clientSocket = server.accept();

                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                    String xy = in.readLine();
                    System.out.println(xy);

                    int x = Integer.parseInt(xy.split(":")[0]);
                    int y = Integer.parseInt(xy.split(":")[1]);

                    if (x >= 0 && y >= 0) {
                        out.write("Point in I quater");
                    } else if (x <= 0 && y >= 0) {
                        out.write("Point in II quater");
                    } else if (x <= 0) {
                        out.write("Point in III quater");
                    } else {
                        out.write("Point in IV quater");
                    }
                    out.flush();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server closed!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
