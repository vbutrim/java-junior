package com.serverio;

import com.acme.edu.Logger;

public class HandlerProtocol {
    //TODO: now parses only "logint 10"

    public void handle(String message) {
        String[] args = (message).split(" ");
        String command = args[0];
        switch (command) {
            case "logint":
                Logger.log(Integer.parseInt(args[1]));
                break;
            case "flush":
                Logger.flush();
                break;
            default:
                System.out.println("ERROR: only logint is supported!");
        }
    }
}
