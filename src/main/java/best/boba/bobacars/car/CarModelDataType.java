package best.boba.bobacars.car;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CarModelDataType implements PersistentDataType<byte[], CarModel> {
    @Override
    public @NotNull Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public @NotNull Class<CarModel> getComplexType() {
        return CarModel.class;
    }

    @Override
    public byte @NotNull [] toPrimitive(@NotNull CarModel complex, @NotNull PersistentDataAdapterContext context) {
        return SerializationUtils.serialize(complex);
    }

    @Override
    public @NotNull CarModel fromPrimitive(byte @NotNull [] primitive, @NotNull PersistentDataAdapterContext context) {
        //return (CarModel) SerializationUtils.deserialize(primitive);
        // ^ I don't know why this throws a org.apache.commons.lang.SerializationException: java.lang.ClassNotFoundException

        ByteArrayInputStream byteStream = new ByteArrayInputStream(primitive);
        ObjectInputStream objectStream = null;
        try {
            objectStream = new ObjectInputStream(byteStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return (CarModel) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
