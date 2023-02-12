import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

import static java.lang.Math.*;


public class UDPserver {
    public static void main(String[] args) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] sendData = new byte[1024];
        while(true)
        {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            String[] coords = sentence.trim().split(":");

            System.out.println("RECEIVED: " + sentence.trim());
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            float x = Float.parseFloat(coords[0]);
            float y = Float.parseFloat(coords[1]);
            float z = Float.parseFloat(coords[2]);

            double w = 6 + (pow(1.2, x - sin(y)) / y + (tan(x * x) / (y + pow(x, 7) / z))) * pow((1 + pow((1 / tan(z / 100)), 7)), sqrt(abs(y) + 3));

            String answer = String.valueOf(w);

            sendData = answer.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);

            System.out.println(w);
        }
    }


}
