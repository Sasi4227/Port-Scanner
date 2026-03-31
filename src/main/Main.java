package main;

import scanner.PortScanner;

public class Main {

    public static void main(String[] args) {

        String target = null;
        int startPort = 1;
        int endPort = 1024;
        int threads = 50;
        String outputFile = null;

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {

                case "-t":
                    target = args[++i];
                    break;

                case "-p":
                    String[] ports = args[++i].split("-");
                    startPort = Integer.parseInt(ports[0]);
                    endPort = Integer.parseInt(ports[1]);
                    break;

                case "-th":
                    threads = Integer.parseInt(args[++i]);
                    break;

                case "-o":
                    outputFile = args[++i];
                    break;
            }
        }

        if (target == null) {

            System.out.println("Usage:");
            System.out.println("java main.Main -t <target> -p <start-end> -th <threads> -o <file>");

            return;
        }

        System.out.println("\nTarget: " + target);
        System.out.println("Ports: " + startPort + "-" + endPort);
        System.out.println("Threads: " + threads);

        PortScanner scanner = new PortScanner();

        scanner.scanPorts(target, startPort, endPort, threads, outputFile);
    }
}