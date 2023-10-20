package game.jframe.Encoders;

import game.jframe.MessageClasses.PlayerId;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PlayerIdEncoder extends MessageToByteEncoder<PlayerId>{

    @Override
    protected void encode(ChannelHandlerContext ctx, PlayerId message, ByteBuf out) throws Exception {
        out.writeInt(message.id);
    }
    
}
