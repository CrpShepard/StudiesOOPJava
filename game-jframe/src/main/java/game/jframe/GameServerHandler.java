package game.jframe;

import java.util.concurrent.ThreadLocalRandom;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GameServerHandler extends SimpleChannelInboundHandler<Integer>{

    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has joined!\n");
        }
        channels.add(ctx.channel());
        System.out.println("channel added!" + ctx.channel().toString());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has left!\n");
        }
        channels.remove(ctx.channel());
        System.out.println("channel left!" + ctx.channel().toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Integer message) throws Exception {
        
            System.out.println(ctx.channel().toString() + " messaged: " + message.toString());
        
        // for (Channel channel : channels) {
        //     if (channel != ctx.channel()) {
        //         channel.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + message + "\n"); 
        //     }
        // }

            int x = ThreadLocalRandom.current().nextInt(0, 1024);
            int y = ThreadLocalRandom.current().nextInt(0, 768);
            int[] coords = new int[3];
            coords[0] = message; coords[1] = x; coords[2] = y;

            ctx.channel().writeAndFlush(coords);
        
        
    }
    
}
