package utb.fai;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {

    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
       try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
                output.flush();
            }
            
        } catch (IOException e) {
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
