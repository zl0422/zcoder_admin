package com.zcoder.admin.gen.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by lin on 2016-06-13.
 */
@Data
@ConfigurationProperties(prefix = "gen",locations = "classpath:templates/gen/config.properties")
public class Gen {

    @NotNull(message = "className can not be null")
    private String className;
    @NotNull(message = "tableName can not be null")
    private String tableName;
    @NotNull(message = "packageName can not be null")
    private String packageName;
    @NotNull(message = "moduleName can not be null")
    private String moduleName;
    private String subModuleName;
    private String author;
    private String version;
    private String funcName;
    @NotNull(message = "scheme can not be null")
    private String scheme;
    private String isReplace;
    private String jsp;
    private String web;
    private String service;
    private String dao;
    private String domain;

}
