package lsl.wind.echocs;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author yd
 * @version 1.0
 * @date 2020/9/22 16:01
 * Sharable表示一个Channel- Handler可以被多个Channel安全地共享
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg){
        //转换成byte缓冲区
        ByteBuf in=(ByteBuf)msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        //将接收到的消息写给发送者，而不冲刷出站消息
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //将未决消息冲刷到远程节点，并且关闭该Channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace();//打印异常栈跟踪
        ctx.close();//关闭该Channel
    }
}
