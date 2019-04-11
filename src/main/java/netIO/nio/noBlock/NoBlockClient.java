package netIO.nio.noBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;

/**
 * Created by lasia on 2019/4/10.
 */
public class NoBlockClient {
    public static void main(String args[]) throws IOException {
        //1,获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost",123));
        //切换成非阻塞模式
        socketChannel.configureBlocking(false);
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
        //5,关闭流
        fileChannel.close();
        socketChannel.close();
    }
}
