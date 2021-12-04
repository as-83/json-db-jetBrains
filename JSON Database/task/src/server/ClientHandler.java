package server;

import com.google.gson.Gson;
import model.Request;
import model.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());
        ) {
            String requestStr = input.readUTF();
            Gson gson = new Gson();
            Request request = gson.fromJson(requestStr, Request.class);

            Response response = execute(request);
            String json = gson.toJson(response);
            output.writeUTF(json);
            //System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Response execute(Request request) {
        Response response = new Response();
        DB db = DB.getInstance();
        String key = "";
        if ( request.getKey() != null) {
            key = request.getKey();
        }
        switch (request.getType()) {
            case "get":
                if (db.containsKey(key)) {
                    response.setResponse("OK");
                    response.setValue(db.get(key));
                } else {
                    response.setResponse("ERROR");
                    response.setReason("No such key");
                }
                break;
            case "set":
                db.save(request.getKey(), request.getValue());
                response.setResponse("OK");
                break;
            case "delete":
                if (db.containsKey(key)) {
                    response.setResponse("OK");
                    db.remove(key);
                } else {
                    response.setReason("No such key");
                }
                break;
            case "exit":
                Main.stopServer();
                response.setResponse("OK");
                break;
            default:
                break;

        }

        return response;
    }
}
