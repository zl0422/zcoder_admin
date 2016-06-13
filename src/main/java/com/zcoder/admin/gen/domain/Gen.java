package com.zcoder.admin.gen.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by lin on 2016-06-13.
 */
@Data
public class Gen {

    private String className;
    private String tableName;
    @Value("${packageName}")
    private String packageName;
    private String moduleName;
    private String subModuleName;
    @Value("${author}")
    private String author;
    @Value("${version}")
    private String version;
    private String funcName;


}
