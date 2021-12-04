package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;
import model.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {
    private static final int PORT = 23456;
    private static final String ADDRESS = "127.0.0.1";



    public static void main(String[] args) {
        System.out.println("Client started!");
        String query = getUserInput(args);
        String response = doRequest(query);
        System.out.println("Received: " + response);

    }

    private static String getUserInput(String[] argv) {
       //getting command line arguments
        //argv = (new Scanner(System.in)).nextLine().split(" ");//getting from std input for test purpose
        Args args = new Args();
        JCommander.newBuilder().addObject(args)
                .build()
                .parse(argv);

        Request request = new Request(args.type, args.key, args.value);
        Gson gson = new Gson();

       return gson.toJson(request);
    }

    private static String doRequest(String query) {
        String response  = "ERROR";

        try(Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        ) {

            outputStream.writeUTF(query);
            System.out.println("Sent: " + query);
            response = inputStream.readUTF();

            } catch (UnknownHostException unknownHostException) {
            unknownHostException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return response;

    }


}
