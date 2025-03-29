package com.example.universitygradingsystemV1.socket;

import java.io.*;
import java.net.Socket;

public class GradingSystemClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println(reader.readLine()); // Welcome message from the server

            while (true) {
                StringBuilder fullMessage = new StringBuilder();
                Thread.sleep(750);
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (line == null) break;
                    fullMessage.append(line).append("\n");
                }
                System.out.print(fullMessage);

                if (fullMessage.toString().contains("Goodbye")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                String userResponse = userInput.readLine();
                writer.println(userResponse);
                writer.flush();
            }

            System.out.println("System terminated!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


