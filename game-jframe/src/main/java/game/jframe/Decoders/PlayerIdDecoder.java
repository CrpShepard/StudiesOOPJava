package game.jframe.Decoders;

import java.util.List;

import game.jframe.MessageClasses.PlayerId;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PlayerIdDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        PlayerId playerId = new PlayerId();
        playerId.id = in.readInt();
        out.add(playerId);
    }
    
}
