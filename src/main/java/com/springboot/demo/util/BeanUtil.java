package com.springboot.demo.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class BeanUtil {

    /**
     * 判断对象是否为null
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 判断对象是否不为null
     *
     * @param obj
     * @return
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    /**
     * 判断对象内容是否为 空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return !isNotEmpty(obj);
    }

    /**
     * 判断对象内容是否为 非空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().length() > 0;
        }
        if (obj instanceof StringBuffer) {
            return ((StringBuffer) obj).toString().trim().length() > 0;
        }
        if (obj instanceof List) {
            return ((List) obj).size() > 0;
        }
        if (obj instanceof Vector) {
            return ((Vector) obj).size() > 0;
        }
        if (obj instanceof HashMap) {
            return ((HashMap) obj).size() > 0;
        }
        if (obj instanceof Iterator) {
            return ((Iterator) obj).hasNext();
        }
        if (obj instanceof JSONObject) {
            return !(((JSONObject) obj).isNullObject() || ((JSONObject) obj).isEmpty());
        }
        return true;

    }



}
