package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by lasia on 2018/3/28.
 */
public class Client {
    public static void main(String args[]) throws InterruptedException {
        //创建NioEventLoopGroup实例来处理事件，如接收连接，读写数据等
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8379).sync();
        future.channel().writeAndFlush(Unpooled.copiedBuffer("Leslie".getBytes()));
        future.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }
}
