package com.example.entity;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.*;

public class ScanTask extends TaskIpInfo implements Runnable {
    // 扫描任务类，实现Runnable接口   包括TCP和UDP
        private int currentPort=0;

        @FXML
        private TextArea contentTcp;

        @FXML
        private TextArea contentUdp;


        public ScanTask(String ipaddress, int currentPort, TextArea contentTcp,TextArea contentUdp) {
            super.setIpAddress(ipaddress);
            this.currentPort=currentPort;
            this.contentTcp=contentTcp;
            this.contentUdp=contentUdp;
           // content.setText("");
        }

        @Override
        public void run() {

            try {
                // 扫描UDP端口
                DatagramSocket udpSocket = new DatagramSocket();
                udpSocket.setSoTimeout(200);
                InetAddress udpAddress = InetAddress.getByName(super.getIpAddress());
                DatagramPacket udpPacket = new DatagramPacket(new byte[1], 1, udpAddress, currentPort);
                udpSocket.send(udpPacket);
                udpSocket.receive(udpPacket);

                String newInfo="UDP:"+currentPort +"已打开\n";
//
//                if (content.getText().length() + newInfo.length() > TextArea.getMaxLength()) {
//                    content.replaceText(newInfo);
//                    content.positionCaret(content.getLength());
//                } else {
//                    content.appendText(newInfo);
//                }
                Platform.runLater(() -> contentUdp.appendText(newInfo));

                //contentUdp.appendText("UDP:"+currentPort +"已打开\n");


                System.out.print("UDP port " + currentPort + " is open");

            } catch (SocketTimeoutException e) {
                // 端口未开放，不需要处理
            } catch (IOException e) {
                //System.out.println("UDP port " + port + " is closed or an error occurred");
            }

            try {
                // 扫描TCP端口
                Socket tcpSocket = new Socket();
                tcpSocket.connect(new InetSocketAddress(super.getIpAddress(), currentPort), 200);
                String newInfo="TCP:"+currentPort +"已打开\n";
                //contentTcp.appendText("TCP:"+currentPort +"已打开\n");
                Platform.runLater(() -> contentTcp.appendText(newInfo));
                System.out.println("TCP port " + currentPort + " is open");



                tcpSocket.close();

            } catch (SocketTimeoutException e) {
                // 端口未开放，不需要处理
            } catch (IOException e) {
                //System.out.println("TCP port " + port + " is closed or an error occurred");
            }
        }
}

// 遍历所有要扫描的端口，进行扫描
//for (int port = super.getStartPort(); port <= super.getEndPort(); port++) {
//
//            for (int i=super.getStartPort()+threadNo;i<=super.getEndPort();i=i+totalThreadNum){
//                int port =i;
//                try {
//                    if (protocol.equals("tcp")) {
//                        // 创建Socket对象，尝试连接端口
//                        Socket socket = new Socket();
//                        socket.connect(new java.net.InetSocketAddress(super.getIpAddress(), port), 100);
//                        socket.close();
//                        // 连接成功，输出结果
//                        System.out.println(String.format("TCP Port %d on %s is open", port, super.getIpAddress()));
//                    } else if (protocol.equals("udp")) {
//                        // 创建DatagramSocket对象，发送UDP数据包
//                        DatagramSocket datagramSocket = new DatagramSocket();
//                        datagramSocket.setSoTimeout(200);
//                        byte[] sendData = new byte[1024];
//                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(super.getIpAddress()), port);
//                        datagramSocket.send(sendPacket);
//
//                        // 接收响应数据包
//                        byte[] receiveData = new byte[1024];
//                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                        datagramSocket.receive(receivePacket);
//                        datagramSocket.close();
//                        // 响应数据包不为空，说明端口开放，输出结果
//                        if (receivePacket.getLength() > 0) {
//                            System.out.println(String.format("UDP Port %d on %s is open", port, super.getIpAddress()));
//                        }
//                    }
//                } catch (IOException e) {
//                    // 连接失败或者超时，不输出结果
//                }
//            }