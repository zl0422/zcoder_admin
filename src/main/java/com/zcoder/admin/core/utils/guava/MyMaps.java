package com.zcoder.admin.core.utils.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.apache.commons.collections.MapUtils;

import java.util.Map;

/**
 * map tools
 * Created by lin on 2016-04-22.
 */
public class MyMaps extends MapUtils {

    /**
     * map convert to string
     *
     * @param source
     * @param separtor string separator default "="
     * @return
     */
    public static String toString(Map<String, String> source, String separtor) {
        String delimiter = MyStrings.isNullOrEmpty(separtor) ? "=" : separtor;
        return Joiner.on(",").withKeyValueSeparator(delimiter).join(source);
    }

    /**
     * Returns the key by the value of map
     *
     * @param map
     * @param val
     */
    public static <T> T getKeyByValForMap(Map<T, T> map, String val) {
        BiMap<T, T> tmp = HashBiMap.create();//bimap双向map
        tmp.putAll(map);
        return tmp.inverse().get(val);
    }

}
