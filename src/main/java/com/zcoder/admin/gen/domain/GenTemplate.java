package com.zcoder.admin.gen.domain;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 模板对象
 * Created by lin on 2016-06-13.
 */
@XmlRootElement(name = "template")
@Data
public class GenTemplate {

    private String name;    // 名称
    private String filePath;        // 生成文件路径
    private String fileName;        // 文件名
    private String content;        // 内容


}
