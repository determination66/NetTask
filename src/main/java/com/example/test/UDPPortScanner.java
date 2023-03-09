//package com.example.test;
//
//
//import java.io.IOException;
//import java.net.*;
//
//import java.util.concurrent.*;
//
//public class UDPPortScanner {
//    private static final int NUM_THREADS = 100;
//    private static final int TIMEOUT_MS = 200;
//
//
//    public static void main(String[] args) {
//        String ipAddress = "127.0.0.1"; // IP地址
//        int startPort = 1; // 起始端口
//        int endPort = 65535; // 终止端口
//
//        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
//
//        for (int port = startPort; port <= endPort; port++) {
//           executor.submit(new PortScannerTask(ipAddress, port, TIMEOUT_MS,"tcp"));
//            executor.submit(new PortScannerTask(ipAddress, port, TIMEOUT_MS,"udp"));
//        }
//
//        executor.shutdown();
//
//        try {
//            executor.awaitTermination(1, TimeUnit.MINUTES);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static class PortScannerTask implements Runnable {
//        private final String ipAddress;
//        private final int port;
//        private final int timeout;
//        private final String protocol;
//
//        public PortScannerTask(String ipAddress, int port, int timeout,String protocol) {
//            this.ipAddress = ipAddress;
//            this.port = port;
//            this.timeout = timeout;
//            this.protocol=protocol;
//        }
//
//        public void run() {
//            if(this.protocol.equals("udp")){
//                try {
//                    // 扫描UDP端口
//                    DatagramSocket udpSocket = new DatagramSocket();
//                    udpSocket.setSoTimeout(timeout);
//                    InetAddress udpAddress = InetAddress.getByName(ipAddress);
//                    DatagramPacket udpPacket = new DatagramPacket(new byte[1], 1, udpAddress, port);
//                    udpSocket.send(udpPacket);
//                    udpSocket.receive(udpPacket);
//                    System.out.println("UDP port " + port + " is open");
//
//                } catch (SocketTimeoutException e) {
//                    // 端口未开放，不需要处理
//                    //System.out.println("UDP port " + port + " is closed or an error occurred");
//                } catch (IOException e) {
//                    //System.out.println("UDP port " + port + " is closed or an error occurred");
//                }
//
//            }
//           if(this.protocol.equals("tcp")){
//               try {
//                   // 扫描TCP端口
//                   Socket tcpSocket = new Socket();
//                   tcpSocket.connect(new InetSocketAddress(ipAddress, port), timeout);
//                   System.out.println("TCP port " + port + " is open");
//                   tcpSocket.close();
//
//               } catch (SocketTimeoutException e) {
//                   // 端口未开放，不需要处理
//               } catch (IOException e) {
//                   //System.out.println("TCP port " + port + " is closed or an error occurred");
//               }
//
//           }
//
//
//        }
//    }
//}
//
