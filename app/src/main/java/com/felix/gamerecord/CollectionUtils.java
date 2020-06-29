package com.felix.gamerecord;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by guofanghua on 17/4/11.
 */
public class CollectionUtils {
    private CollectionUtils() {
        //do nothing
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Map collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * <p>Checks if an array of Objects is empty or {@code null}.</p>
     *
     * @param array the array to test
     * @return {@code true} if the array is empty or {@code null}
     * @since 2.1
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 将list转换程String(格式：xx,xx,xx)
     *
     * @param list
     * @return 字符串
     */
    public static String list2Str(List<String> list) {
        if (null == list || list.isEmpty()) {
            return "";
        }

        StringBuilder vBuild = new StringBuilder();
        for (String str : list) {
            vBuild.append(str);
            vBuild.append(",");
        }

        if (vBuild.length() > 0) {
            vBuild.deleteCharAt(vBuild.length() - 1);
        }
        return vBuild.toString();
    }


}
