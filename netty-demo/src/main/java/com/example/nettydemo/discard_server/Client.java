package com.example.nettydemo.discard_server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author sunyajun
 * @date 2022/8/8 上午12:13
 */
@Slf4j
public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
            printWriter.flush();
            log.info("发送数据 success");
            socket.shutdownOutput();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
