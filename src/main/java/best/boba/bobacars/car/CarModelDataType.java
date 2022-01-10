package best.boba.bobacars.car;

import org.apache.commons.lang.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

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
        return (CarModel) SerializationUtils.deserialize(primitive);
    }
}
