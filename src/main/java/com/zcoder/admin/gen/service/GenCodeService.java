package com.zcoder.admin.gen.service;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.zcoder.admin.gen.domain.Column;
import com.zcoder.admin.gen.domain.Gen;
import com.zcoder.admin.gen.domain.GenTemplate;
import com.zcoder.admin.gen.utils.GenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * Created by lin on 2016-06-08.
 */
@Component
@Transactional
@Slf4j
public class GenCodeService {

    @Autowired
    private EntityManager em;

    public List<Column> getColumnsByTableName(String tableName) {
        String sql = "SHOW FULL FIELDS FROM " + tableName;
        Query query = em.createNativeQuery(sql);
        List<Column> result = Lists.newArrayList();
        List objecArraytList = query.getResultList();
        for (int i = 0; i < objecArraytList.size(); i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            Column column = new Column();
            column.setField(String.valueOf(obj[0]));
            column.setJdbcType(String.valueOf(obj[1]));
            column.setIsNull(String.valueOf(obj[3]));
            column.setIsKey(String.valueOf(obj[4]));
            column.setDefaultVal(String.valueOf(obj[5]));
            column.setComment(String.valueOf(obj[8]));
            result.add(column);
        }
        return result;

    }


    /**
     * 生成代码
     *
     * @param request
     */
    public void genCode(HttpServletRequest request) throws Exception {

        String tableName = request.getParameter("tableName");
        String className = request.getParameter("className");
        String funcName = request.getParameter("funcName");
        String moduleName = request.getParameter("moduleName");
        String subModuleName = request.getParameter("subModuleName");

        log.info("=======================gen code============================");
        log.info("tableName :" + tableName + "className :" + className
                + "moduleName :" + moduleName + "subModuleName :" + subModuleName);

        Preconditions.checkArgument(tableName != null, "tableName must be not null.");
        Preconditions.checkArgument(className != null, "className must be not null.");
        Preconditions.checkArgument(moduleName != null, "moduleName must be not null.");

        List<Column> columns = getColumns(request, tableName);

        String scheme = request.getParameter("scheme");
        List<GenTemplate> templates = GenUtils.getTemplateList(scheme);
        Map<String, Object> model = buildModel(tableName, className, funcName, moduleName, subModuleName, columns);
        if (templates != null && !templates.isEmpty()) {
            for (GenTemplate template : templates) {
                GenUtils.generate(template, model, true);
            }
        }

    }

    /**
     * 封装请求参数
     * @param request
     * @param tableName
     * @return
     */
    private List<Column> getColumns(HttpServletRequest request, String tableName) {
        Map param = request.getParameterMap();
        List<Column> columns = getColumnsByTableName(tableName);
        for (Column column : columns) {
            column.setJavaType(GenUtils.toJavaType(column.getJdbcType()));
            if (param.keySet().contains("isQuery_" + column.getField())) {
                column.setQuery(Boolean.TRUE);
            }
            if (param.keySet().contains("isList_" + column.getField())) {
                column.setList(Boolean.TRUE);
            }
            if (param.keySet().contains("queryMethod_" + column.getField())) {
                column.setQueryMethod(String.valueOf(param.get("queryMethod_" + column.getField())));
            }
            if (param.keySet().contains("formType_" + column.getField())) {
                column.setFormType(String.valueOf(param.get("formType_" + column.getField())));
            }
        }
        return columns;
    }

    /**
     * 构建模型
     * @param tableName
     * @param className
     * @param funcName
     * @param moduleName
     * @param subModuleName
     * @param columns
     * @return
     */
    private Map<String, Object> buildModel(String tableName, String className, String funcName, String moduleName, String subModuleName, List<Column> columns) {
        Gen gen = new Gen();
        gen.setTableName(tableName);
        gen.setClassName(className);
        gen.setFuncName(funcName);
        gen.setModuleName(moduleName);
        gen.setSubModuleName(subModuleName);
        return GenUtils.getDataModel(gen,columns);
    }


}
