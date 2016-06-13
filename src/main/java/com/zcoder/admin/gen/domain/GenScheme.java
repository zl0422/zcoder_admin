package com.zcoder.admin.gen.domain;

import org.springframework.beans.factory.annotation.Value;

/**
 * 生成方案，意在
 * 支持所有模板生成，也可进行某个模板的生成
 * Created by lin on 2016-06-13.
 */
public enum  GenScheme {

    JSP("jsp",GenSchemeType.jsp),WEB("web",GenSchemeType.web),SERVICE("service",GenSchemeType.service),
    DAO("dao",GenSchemeType.dao),DOMAIN("domain",GenSchemeType.domain);

    GenScheme(String label,String value){
        this.label = label;
        this.value = value;
    }

    private String label;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static class GenSchemeType{

        @Value("${jsp}")
        private static  String jsp;
        @Value("${web}")
        private static String web;
        @Value("${service}")
        private static String service;
        @Value("${dao}")
        private static String dao;
        @Value("${domain}")
        private static String domain;

    }
}
