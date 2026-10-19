package utb.fai;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        int port = 12345;
        int max_threads = 10;    

        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[0]);
                max_threads = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Chybny format parametru, pouziji vychozi hodnoty.");
            }
        }
        ExecutorService pool = Executors.newFixedThreadPool(max_threads);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientThread(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}
