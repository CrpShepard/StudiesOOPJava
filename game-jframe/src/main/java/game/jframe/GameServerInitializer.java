package game.jframe;

import game.jframe.Decoders.MyMessageDecoder;
import game.jframe.Encoders.MyMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class GameServerInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        pipeline.addLast(new MyMessageEncoder());
        pipeline.addLast(new MyMessageDecoder());

        pipeline.addLast("handler", new GameServerHandler());
    }

}
