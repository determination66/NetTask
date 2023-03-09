package com.example.test;
import java.io.IOException;
import java.net.*;

public class TestUdp {
    private static final int TIMEOUT_MS = 1000;

    public static void main(String[] args) {
        String ipAddress = "127.0.0.1"; // IP地址
        int startPort = 1; // 起始端口
        int endPort = 65535; // 终止端口

        for (int port = startPort; port <= endPort; port++) {
            Thread thread = new Thread(new PortScannerTask(ipAddress, port, TIMEOUT_MS));
            thread.start();
        }


    }

    private static class PortScannerTask implements Runnable {
        private final String ipAddress;
        private final int port;
        private final int timeout;

        public PortScannerTask(String ipAddress, int port, int timeout) {
            this.ipAddress = ipAddress;
            this.port = port;
            this.timeout = timeout;
        }

        public void run() {
            try {
                // 扫描UDP端口
                DatagramSocket udpSocket = new DatagramSocket();
                udpSocket.setSoTimeout(timeout);
                InetAddress udpAddress = InetAddress.getByName(ipAddress);
                DatagramPacket udpPacket = new DatagramPacket(new byte[1], 1, udpAddress, port);
                udpSocket.send(udpPacket);
                udpSocket.receive(udpPacket);
                System.out.println("UDP port " + port + " is open");

            } catch (SocketTimeoutException e) {
                // 端口未开放，不需要处理
            } catch (IOException e) {
                //System.out.println("UDP port " + port + " is closed or an error occurred");
            }

            try {
                // 扫描TCP端口
                Socket tcpSocket = new Socket();
                tcpSocket.connect(new InetSocketAddress(ipAddress, port), timeout);
                System.out.println("TCP port " + port + " is open");
                tcpSocket.close();

            } catch (SocketTimeoutException e) {
                // 端口未开放，不需要处理
            } catch (IOException e) {
                //System.out.println("TCP port " + port + " is closed or an error occurred");
            }
        }
    }
}
