package netIO.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by lasia on 2019/4/10.
 */
public class BlockServer {
    public static void main(String args[]) throws IOException {
        //1,获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2,得到文件通道，将客户端传递过来的图片写到本地项目下
        FileChannel outChannel = FileChannel.open(Paths.get("leslie.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //3,绑定链接
        serverSocketChannel.bind(new InetSocketAddress(123));
        //4,获得客户端的链接（阻塞）
        SocketChannel client = serverSocketChannel.accept();
        //5,创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //6,将客户端传递过来的图片保存在本地中
        while(client.read(byteBuffer)!=-1) {
            //切换读模式
            byteBuffer.flip();

            outChannel.write(byteBuffer);

            //读完切换回写模式，能让管道继续读取文件的数据
            byteBuffer.clear();
        }
    }
}
