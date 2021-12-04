package server;

import com.google.gson.Gson;
import model.Request;
import model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    private static final int PORT = 23456;
    public static final String ADDRESS = "127.0.0.1";

    private static volatile boolean isStopped = false;

    public static void main(String[] args) {
        System.out.println("Server started!");
        run();
    }

    private static void run() {
        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PORT, 50, InetAddress.getByName(ADDRESS))) {

            while (!isStopped) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                executorService.submit(clientHandler);
                Thread.sleep(50);
            }

        } catch (IOException | InterruptedException ioException) {
            ioException.printStackTrace();
        }

    }

    public static void stopServer() {
        isStopped = true;
    }
}





