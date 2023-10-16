package game.jframe;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class IntArrayEncoder extends MessageToByteEncoder<int[]> {

    @Override
    protected void encode(ChannelHandlerContext ctx, int[] msg, ByteBuf out) {
        out.writeInt(msg.length);
        for (int value : msg) {
            out.writeInt(value);
        }
    }
}

