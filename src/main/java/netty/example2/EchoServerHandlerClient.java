package netty.example2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 *     · channelActive函数，在建立了与服务端的连接后该函数被调用。
 *
 *　　　· channelRead0函数，当接收到服务端发送来的消息后被调用。
 *
 *　　　· exceptionCaught函数，当处理发生异常时被调用。
 * Created by lasia on 2018/3/28.
 */
public class EchoServerHandlerClient extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("Client received: " + ByteBufUtil.hexDump(byteBuf.readBytes(byteBuf.readableBytes())));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
      //  ctx.writeAndFlush(Unpooled.copiedBuffer("Leslie".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("Leslie".getBytes()));
    }
}
