package com.xulihuazj.pms.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * PlainOioServer.java 1.0.0 2018/08/03  17:06
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/03  17:06 created by xulihua
 */
public class PlainOioServer {

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (; ; ) {
                final Socket clientSocket = socket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("11111\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            ;
                            clientSocket.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
