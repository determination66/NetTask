package com.example.entity;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class TaskIpInfo {

    private String ipAddress;

    private int startPort;

    private  int endPort;

    public TaskIpInfo(){
        this.ipAddress="127.0.0.1";
        this.startPort=0;
        this.endPort=1000;
    }

    //初始化这个信息
    public TaskIpInfo(String ipAddress,int startPort,int endPort){
        this.ipAddress=ipAddress;
        this.startPort=startPort;
        this.endPort=endPort;
//        this.outputTextField=outputTextField;
    }


    //判断IpAdress是否合法
    public boolean IpAdressIsLegal(String str){
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (str.equals("localhost")) {
            //System.out.println("localhost情况");
            return true;
        }
        String[] parts = str.split("\\.");
        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
    return true;
    }

    //判断端口信息是否合法
    public boolean PortIsLegal(String str){
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            int port = Integer.parseInt(str);
            if (port < 0 || port > 65535) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setTaskIpInfo(String ipAddress,int startPort,int endPort){
        this.ipAddress = ipAddress;
        this.startPort = startPort;
        this.endPort = endPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getStartPort() {
        return startPort;
    }

    public void setStartPort(int startPort) {
        this.startPort = startPort;
    }

    public int getEndPort() {
        return endPort;
    }

    public void setEndPort(int endPort) {
        this.endPort = endPort;
    }

    @Override
    public String toString() {
        return "TaskIpInfo{" +
                "ipAddress='" + ipAddress + '\'' +
                ", startPort=" + startPort +
                ", endPort=" + endPort +
                '}';
    }








}
