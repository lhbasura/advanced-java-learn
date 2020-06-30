package com.lhbasura.io.demo.asyncnonblock.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author asura
 * @date 2020/6/23 10:34
 * @description
 */
public class ReadHandler implements CompletionHandler<Integer, ByteBuffer> {

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip(); // 移动 limit位置
        System.out.println("receive client data length：" + attachment.limit() + " byte");
        // 读取client发送的数据
        System.out.println("from client : "+new String(attachment.array(),0,attachment.limit()));

        // 向client写入数据
        //  doWrite(clientChannel);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
    }

}
