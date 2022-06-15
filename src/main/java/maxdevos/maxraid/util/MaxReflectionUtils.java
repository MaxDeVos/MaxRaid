package maxdevos.maxraid.util;

import java.lang.reflect.Field;

public class MaxReflectionUtils {

    public static Field findByType(Class<?> owner, Class<?> fieldType) throws Exception {
        Field[] fields = owner.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == fieldType) return field;
        }
        return null;
    }

}
