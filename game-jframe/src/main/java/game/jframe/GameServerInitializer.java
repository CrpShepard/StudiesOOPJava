package game.jframe;

import game.jframe.Decoders.IntegerDecoder;
import game.jframe.Decoders.PlayerIdDecoder;
import game.jframe.Decoders.PlayerMoveDataDecoder;
import game.jframe.Encoders.AllPlayerMoveDataEncoder;
import game.jframe.Encoders.IntArrayEncoder;
import game.jframe.Encoders.PlayerIdEncoder;
import game.jframe.Encoders.PlayerMoveDataEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class GameServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        ChannelPipeline pipeline = arg0.pipeline();

        pipeline.addLast(new PlayerIdEncoder());
        pipeline.addLast(new AllPlayerMoveDataEncoder());
        pipeline.addLast(new PlayerMoveDataEncoder());
        pipeline.addLast(new PlayerMoveDataDecoder());

        pipeline.addLast("handler", new GameServerHandler());
    }

}
