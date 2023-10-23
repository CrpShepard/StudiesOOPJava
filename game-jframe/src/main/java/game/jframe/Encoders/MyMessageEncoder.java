package game.jframe.Encoders;

import game.jframe.MessageClasses.MyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MyMessage>{

    @Override
    protected void encode(ChannelHandlerContext ctx, MyMessage message, ByteBuf out) throws Exception {
        byte[] serializedData = message.serialize(); // Сериализация объекта
        out.writeInt(serializedData.length); // Записываем длину массива
        out.writeBytes(serializedData); // Записываем массив байтов

        System.out.print("MyMessage Encoder");

    }
    
}
