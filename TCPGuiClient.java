import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPGuiClient extends Frame implements ActionListener {
    private static BufferedReader in; // поток чтения из сокета
    private static BufferedWriter out; // поток записи в сокет
    private static Socket clientSocket; //сокет для общения

    TextField address, port, xcord, ycord, output;

    Label clientlable, sendingl, resl;
    Button connectbtn, setbtn;

    public TCPGuiClient() {


        clientlable = new Label("Client (ip, port)");
        sendingl = new Label("Sending data(x, y)");
        resl = new Label("Result");
        address = new TextField(10);
        port = new TextField(10);
        xcord = new TextField(10);
        ycord = new TextField(10);
        output = new TextField(10);

        connectbtn = new Button("Connect");
        setbtn = new Button("Send");

        setLayout( new GridBagLayout());
        GridBagLayout g = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(g);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(clientlable, gbc);

        //
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(connectbtn, gbc);

        gbc.gridx = 1;
        add(address, gbc);

        gbc.gridx = 2;
        add(port, gbc);

        //
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(sendingl, gbc);

        //
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(setbtn, gbc);

        gbc.gridx = 1;
        add(xcord, gbc);

        gbc.gridx = 2;
        add(ycord, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        add(resl, gbc);

        gbc.ipady = 20;
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(output, gbc);

        connectbtn.addActionListener(this);
        setbtn.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == connectbtn) {
            try {
                clientSocket = new Socket(address.getText(), Integer.parseInt(port.getText()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Connected successfully");


        }
        if (ae.getSource() == setbtn) {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println(xcord.getText() + ":" + ycord.getText());
                out.write(xcord.getText() + ":" + ycord.getText()+"\n");
                out.flush();
                output.setText(in.readLine());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        TCPGuiClient app = new TCPGuiClient();
        app.setSize(500, 200);
        app.setVisible(true);
        app.setLocation(300,300);
        app.address.setText("localhost");
        app.port.setText("4004");
    }

}
