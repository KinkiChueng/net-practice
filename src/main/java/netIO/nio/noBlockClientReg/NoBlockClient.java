package netIO.nio.noBlockClientReg;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by lasia on 2019/4/10.
 */
public class NoBlockClient {
    public static void main(String args[]) throws IOException {
        //1,获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",123));
        //切换成非阻塞模式
        socketChannel.configureBlocking(false);
        //获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器中，获取服务器端返回的数据
        socketChannel.register(selector, SelectionKey.OP_READ);
        //2,发送照片
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/lasia/Documents/PLAY/leslieLogo.jpg"));
        //3,创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4,读取本地文件
        while(fileChannel.read(byteBuffer)!=-1) {
            //在读之前都要切换成读模式
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            //读完切换成写模式，能让管道继续读取文件的数据
            byteBuffer.clear();
        }

        //5,轮训地获取选择器上已"就绪"的事件
        while (selector.select() > 0) {
            //6，获取当前选择器所有注册的"选择键" 已就绪的监听事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //7,获取已就绪的事件
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //8,读事件就绪
                if (selectionKey.isReadable()) {
                    //获取相应通道
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
                    //9,知道服务端要返回响应的数据给客户端，客户端在这里接收
                    int readBytes = channel.read(responseBuffer);

                    if (readBytes > 0) {
                        //切换读模式
                        responseBuffer.flip();
                        System.out.println(new String(responseBuffer.array(),0,readBytes));
                    }
                }
            }
            iterator.remove();
        }
        //5,关闭流
        fileChannel.close();
        socketChannel.close();
    }
}
