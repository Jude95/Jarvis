package com.jude.jarvis_core.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Jude on 2017/7/25.
 */

public class ParametrizedTypeUtil {

    public static Class getTypeArgumentClass(Class clazz, int index) {
        Class pClass = null;
        do {
            Type genType = clazz.getGenericSuperclass();
            if (!(genType instanceof ParameterizedType)) {
                break;
            }
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (params == null || params.length < index + 1) {
                break;
            }
            if (params[index] != null && params[index] instanceof Class) {
                pClass = (Class) params[index];
            }
        } while (false);

        return pClass;
    }
}
