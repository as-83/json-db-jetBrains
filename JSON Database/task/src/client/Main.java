package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    private static final int PORT = 23456;
    private static final String ADDRESS = "127.0.0.1";


    public static void main(String[] args) {
        System.out.println("Client started!");
        String query = getUserInput();
        String response = doRequest(query);
        System.out.println("Received: " + response);

    }

    private static String getUserInput() {
       return "Give me a record #12";
    }

    private static String doRequest(String query) {
        String response  = "ERROR";

        try(Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        ) {

            outputStream.writeUTF(query);
            System.out.println("Sent: Give me a record #12");

            response = inputStream.readUTF();

            } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return response;

    }


}
