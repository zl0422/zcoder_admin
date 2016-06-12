package com.zcoder.admin.gen.domain;

/**
 * 字段显示类型
 * Created by lin on 2016-06-08.
 */
public enum FieldType {

    INPUT("单行文本", "input"), TEXTAREA("多行文本", "textarea"), SELECT("下拉列表", "select"),

    RADIOBOX("单选", "radiobox"), CHECKBOX("复选框", "checkbox"),

    DATESELECT("日期", "dateSelect");

    private String label;

    private String value;


    FieldType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
