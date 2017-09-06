package com.serverio;

import com.acme.edu.Logger;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.css.StyleableObjectProperty;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6667);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(() -> {
                try (
                        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
                ) {
                    while (true) {
                        HandlerProtocol handler = new HandlerProtocol();
                        handler.handle(inputStream.readUTF());
//                      outputStream.writeUTF(">>> " + inputStream.readUTF());
                        //System.out.println(">>> " + inputStream.readUTF());
//                        Logger.log(inputStream.readUTF());
                    }
                } catch (EOFException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
