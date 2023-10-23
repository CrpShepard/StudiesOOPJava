package game.jframe.Decoders;

import java.util.List;

import game.jframe.MessageClasses.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MyMessageDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.print("MyMessageDecoder Begin");

        int dataLength = in.readInt(); // Читаем длину массива
        byte[] serializedData = new byte[dataLength];
        in.readBytes(serializedData); // Читаем массив байтов

        MyMessage allPlayerMoveData = MyMessage.deserialize(serializedData); // Десериализация объекта
        out.add(allPlayerMoveData); // Добавляем в выходной список

        System.out.print("MyMessageDecoder End");
    }
}
