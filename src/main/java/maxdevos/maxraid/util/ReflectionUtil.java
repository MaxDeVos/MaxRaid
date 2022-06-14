package maxdevos.maxraid.util;

import jline.internal.Nullable;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.lang.reflect.Field;

public final class ReflectionUtil {

    private ReflectionUtil() {}

    @Nullable
    public static Object getPrivateField(String fieldName, @NonNull Class<?> clazz, Object object) {
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