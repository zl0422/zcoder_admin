package com.zcoder.admin.gen.domain;

import com.google.common.collect.Maps;

import java.util.Map;


/**
 * 生成方案，意在
 * 支持所有模板生成，也可进行某个模板的生成
 * Created by lin on 2016-06-13.
 */
public class   GenScheme {

    private Gen gen;

    public GenScheme(Gen gen){
        this.gen = gen;
    }

    public Map<String,String> getScheme(){
        Map<String,String> schemes = Maps.newHashMap();
        schemes.put("jsp",gen.getJsp());
        schemes.put("web",gen.getWeb());
        schemes.put("service",gen.getService());
        schemes.put("dao",gen.getDao());
        schemes.put("domain",gen.getDomain());
        return schemes;
    }


}
