package com.clientio;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

import static java.lang.System.lineSeparator;

public class Client {
    public static void main(String[] args) throws IOException {
        while (true) {
            try (
                    Socket socket = new Socket("127.0.0.1", 6667);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream outputStream1 = new DataOutputStream(outputStream);
            ) {
                outputStream1.writeUTF("logint 10");
                outputStream1.writeUTF("flush");

                outputStream1.flush();
                outputStream1.close();
            } catch (ConnectException e) {
                System.out.print("Connection is lost" + lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
