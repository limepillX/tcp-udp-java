import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

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

                Integer MainDigSum = 0;
                int SecondDigSum = 0;

                try {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                    Integer size = Integer.parseInt(in.readLine());
                    System.out.println(size);

                    for (int i = 0; i < size; i++){
                        for (int j = 0; j < size; j++){
                            String readed = in.readLine();
                            if (i == j){MainDigSum+= Integer.parseInt(readed);}
                            if (i == (size - 1)-j){SecondDigSum+= Integer.parseInt(readed);}
                        }
                    }

                    double res = (double)MainDigSum/(double)SecondDigSum;

                    out.write("" + res);
                    out.flush();

                }
                finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            }
            finally {
                System.out.println("Server closed!");
                server.close();
            }
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
}