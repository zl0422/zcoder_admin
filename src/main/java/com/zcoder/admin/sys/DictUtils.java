package com.zcoder.admin.sys;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zcoder.admin.core.utils.CacheUtils;
import com.zcoder.admin.sys.dao.SysDictDao;
import com.zcoder.admin.sys.domain.SysDict;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 数据字典工具类
 * Created by lin on 2016-06-15.
 */
public class DictUtils {

    @Autowired
    private static SysDictDao dictDao;

    public static final String CACHE_DICT_MAP = "dictMap";

    /**
     * 根据数据字典类型，查询字典数据
     * @param type 数据字典类型
     * @return
     */
    public static List<SysDict> getDictList(String type){
        @SuppressWarnings("unchecked")
        Map<String, List<SysDict>> dictMap = (Map<String, List<SysDict>>)CacheUtils.get(CACHE_DICT_MAP);
        if (dictMap==null){
            dictMap = Maps.newHashMap();
            for (SysDict dict : dictDao.findAll()){
                List<SysDict> dictList = dictMap.get(dict.getDictType());
                if (dictList != null){
                    dictList.add(dict);
                }else{
                    dictMap.put(dict.getDictType(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DICT_MAP, dictMap);
        }
        List<SysDict> dictList = dictMap.get(type);
        if (dictList == null){
            dictList = Lists.newArrayList();
        }
        return dictList;

    }

}
