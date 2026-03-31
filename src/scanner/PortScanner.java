package scanner;

import banner.BannerGrabber;
import service.ServiceDetector;
import util.ResultWriter;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PortScanner {

    public void scanPorts(String host, int startPort, int endPort, int threads, String outputFile) {

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int port = startPort; port <= endPort; port++) {

            int currentPort = port;

            executor.submit(() -> {

                try {

                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(host, currentPort), 2000);

                    String service = ServiceDetector.detectService(currentPort);

                    String banner = BannerGrabber.grabBanner(socket, currentPort);

                    String result = "Port " + currentPort + " OPEN → " + service;

                    System.out.println(result);

                    ResultWriter.write(outputFile, result);
                    ResultWriter.write(outputFile, "Banner: " + banner);

                    socket.close();

                } catch (Exception ignored) {}

            });
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}

        System.out.println("\nScan Completed");
    }
}