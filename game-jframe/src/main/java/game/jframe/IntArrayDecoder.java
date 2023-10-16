package game.jframe;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class IntArrayDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() < 4) {
            return;
        }

        int length = in.readInt();

        if (in.readableBytes() < length * 4) {
            in.resetReaderIndex();
            return;
        }

        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = in.readInt();
        }

        out.add(array);
    }
}
