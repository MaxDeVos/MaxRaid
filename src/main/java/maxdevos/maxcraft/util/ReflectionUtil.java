package maxdevos.maxcraft.util;

import jdk.internal.jline.internal.Nullable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class ReflectionUtil {

    private ReflectionUtil() {}

    @Nullable
    public static Object getPrivateField(String fieldName, @NotNull Class<?> clazz, Object object) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}