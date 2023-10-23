package game.jframe.MessageClasses;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MyMessage implements Serializable{
    public String type;

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(this);
        out.flush();
        return byteArrayOutputStream.toByteArray();
    }

    // Статический метод для десериализации объекта из байтов
    public static MyMessage deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(byteArrayInputStream);
        return (MyMessage) in.readObject();
    }
}
