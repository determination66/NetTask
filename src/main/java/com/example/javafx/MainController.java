package com.example.javafx;

import com.example.entity.ScanTask;
import com.example.entity.TaskIpInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button startscan;

    @FXML
    private TextField ipadress0;

    @FXML
    private TextField endport0;

    @FXML
    private TextField startport0;

    @FXML
    private TextArea contentUdp1;
    @FXML
    private TextArea contentUdp2;
    @FXML
    private TextArea contentUdp3;

    @FXML
    private TextField ipadress1;

    @FXML
    private TextField endport1;

    @FXML
    private TextField startport1;

    @FXML
    private TextField ipadress2;

    @FXML
    private TextField endport2;

    @FXML
    private TextField startport2;

    @FXML
    private TextArea contentTcp1;

    @FXML
    private TextArea contentTcp2;

    @FXML
    private TextArea contentTcp3;




    //点击按钮触发的事件，调用函数获取信息，并且开始调用线程测试端口函数
    @FXML
    void Click(ActionEvent event) throws InterruptedException {
        // 示例：获取ipadress中的文字，并在content中显示
        //String ip = ipadress0.getText();
        //content.setText("开始端口:"+startport0.getText()+"\n结束端口为："+endport0.getText());
        //getIpAdresses();
        String ip0 = ipadress0.getText();
        String star0 = startport0.getText();
        String end0 = endport0.getText();
        TaskIpInfo taskIpInfo0 = new TaskIpInfo(ip0, Integer.parseInt(star0), Integer.parseInt(end0));

        //ip地址受否符合
        if (!taskIpInfo0.IpAdressIsLegal(ip0)) {
            System.out.println("ip0不符合");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("请输入正确的ip地址格式");
            alert.show();
            return;
        }
        ;
        //port不符合
        if (!taskIpInfo0.PortIsLegal(star0) || !taskIpInfo0.PortIsLegal(end0)) {
            System.out.println("port不符合");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("请输入正确的port格式");
            alert.show();
            return;
        }

        //清除内容
        contentTcp1.setText("");
        contentUdp1.setText("");
        contentTcp2.setText("");
        contentUdp2.setText("");
        contentTcp3.setText("");
        contentUdp3.setText("");

//        contentTcp1.setMaxSize(65535,20);
//        contentUdp1.setMaxSize(65535,20);
//        contentTcp2.setMaxSize(65535,20);
//        contentUdp2.setMaxSize(65535,20);
//        contentTcp3.setMaxSize(65535,20);
//        contentUdp3.setMaxSize(65535,20);




        for (int port = Integer.parseInt(star0); port <= Integer.parseInt(end0); port++) {
            Thread thread = new Thread(new ScanTask(ip0,port,contentTcp1,contentUdp1),"thread1");
            thread.start();
        }
        //System.out.println("完成!!");

    }


    @FXML
    void Clear(ActionEvent event) {
        contentUdp1.setText("");
        contentTcp1.setText("");

        contentTcp2.setText("");
        contentUdp2.setText("");

        contentTcp3.setText("");
        contentUdp3.setText("");
        //System.out.println("删除");
    }




}










