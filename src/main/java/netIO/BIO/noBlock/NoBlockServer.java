package netIO.BIO.noBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 * Created by lasia on 2019/4/10.
 */
public class NoBlockServer {
    public static void main(String args[]) throws IOException {
        //1,获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2,切换成非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3,绑定链接
        serverSocketChannel.bind(new InetSocketAddress(123));
        //4,获取选择器
        Selector selector = Selector.open();
        //将通道注册到选择器上，指定接收"监听通道"事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //5，轮训的获取选择器上已经"就绪"的事件---》只要select()>0，就说明已就绪
        while (selector.select()>0) {
            //6,获取当前选择器所有注册的"选择键"（已就绪的监听事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //7,获取"已就绪"事件，不同事件做不同工作
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                //接收事件就绪
                if (selectionKey.isAcceptable()) {
                    //8,获取客户端链接
                    SocketChannel client = serverSocketChannel.accept();
                    //切换成非阻塞状态
                    client.configureBlocking(false);
                    //注册到选择器上---》拿到客户端的链接为了读取通道数据（监听读就绪事件）
                    client.register(selector,SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {  //读事件就绪
                    //9,获取当前选择器读就绪状态的通道
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    //读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //得到文件通道，将客户端传递过来的图片写到本地项目下（写模式，没则创建）
                    FileChannel outChannel = FileChannel.open(Paths.get("logo.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                    while (client.read(buffer)>0) {
                        //在读之前切换成读模式
                        buffer.flip();

                        outChannel.write(buffer);

                        //读完切换成写模式
                        buffer.clear();
                    }
                }
                //10,取消选择键（把已经处理过的事件，取消）
                iterator.remove();
            }
        }
    }
}
