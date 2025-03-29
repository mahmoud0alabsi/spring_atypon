package com.example.universitygradingsystemV1.socket;

import com.example.universitygradingsystemV1.controller.AuthController;

import java.io.*;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, false)
        ) {
            writer.println("Welcome to the Student Grading System!");
            writer.flush();

            AuthController authController = new AuthController(reader, writer);
            authController.showAuthMenu();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

