package game.jframe.Decoders;

import java.util.List;

import game.jframe.MessageClasses.PlayerMoveData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class PlayerMoveDataDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        PlayerMoveData playerMoveData = new PlayerMoveData(
            in.readInt(),
            in.readInt(),
            in.readInt(),
            in.readInt(),
            in.readInt(),
            in.readInt(),
            in.readInt(),
            in.readBoolean(),
            in.readLong()
        );

        out.add(playerMoveData);
    }
    
}
