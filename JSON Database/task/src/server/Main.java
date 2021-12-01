package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    private static final int PORT = 23456;
    public static final String ADDRESS = "127.0.0.1";
    static Scanner scanner = new Scanner(System.in);
    static List<String> dataBase = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Server started!");
        //initDB();
        //run();
        String request = getClientInput();
        System.out.println("Received: " + request);
        System.out.println("Sent: A record # 12 was sent!");

    }

    private static void run() {
        while (true) {
            String input = getClientInput();
            if (input.equals("exit")) {
                break;
            }
            Query query = new Query(input);
            String result = execute(query);
            System.out.println(result);

        }
    }

    private static String getClientInput() {

        String request = "ERROR";
        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {
            Socket clientSocket = serverSocket.accept();
            try (
                    DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
            ) {
                request = input.readUTF();
                output.writeUTF("A record # 12 was sent!");


            } catch(IOException e){
            e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  request;

    }

    private static void initDB() {
        for (int i = 0; i < 100; i++) {
            dataBase.add("");
        }
    }

    private static String getUserInput() {
        return scanner.nextLine();
    }

    private static String execute(Query query) {
        String result = "ERROR";
        if (query.getId() > 0 && query.getId() < 101) {
            switch (query.getCommand()) {
                case "get":
                    result = dataBase.get(query.getId() - 1);
                    if (result.isEmpty()) {
                        result = "ERROR";
                    }
                    break;
                case "set":
                    dataBase.set(query.getId() - 1, query.getData());
                    result = "OK";
                    break;
                case "delete":
                    dataBase.set(query.getId() - 1, "");
                    result = "OK";
                    break;

            }
        }
        return result;
    }
}
