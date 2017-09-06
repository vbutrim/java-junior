package com.clientio;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

import static java.lang.System.lineSeparator;

/**
 * Created by Java_16 on 06.09.2017.
 */
public class ClientMirrow {
    public static void main(String[] args) throws IOException {
        while(true) {
            try (
                    Socket socket = new Socket("127.0.0.1", 6667);
                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream outputStream1 = new DataOutputStream(outputStream);
            ) {
                outputStream1.writeUTF("logint 20");
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
