package com.xulihuazj.pms.impl;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * PlainNioServer.java 1.0.0 2018/08/03  17:14
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 * 1. 2018/08/03  17:14 created by xulihua
 */
public class PlainNioServer {

    public void serve(int port) throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket ss = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        ss.bind(address);
        Selector selector = Selector.open();
        serverChannel.register(selector,SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("222".getBytes());
        for (;;) {
            selector.select();
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try{
                    if(key.isAcceptable()){
                        ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                        SocketChannel client = channel.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE | SelectionKey.OP_READ,msg.duplicate());
                    }

                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel)key.channel();
                        ByteBuffer buffer = (ByteBuffer)key.attachment();
                        while (buffer.hasRemaining()){
                            if(client.write(buffer) == 0){
                                break;
                            }
                        }
                    }
                }catch (IOException ex){
                    key.cancel();
                    try{
                        key.channel().close();
                    }catch (IOException cex){

                    }
                }

            }
        }


    }
}
