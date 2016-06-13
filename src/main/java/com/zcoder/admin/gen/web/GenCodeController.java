package com.zcoder.admin.gen.web;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zcoder.admin.core.common.BaseController;
import com.zcoder.admin.gen.domain.Column;
import com.zcoder.admin.gen.service.GenCodeService;
import com.zcoder.admin.gen.service.MysqlTableService;
import com.zcoder.admin.gen.utils.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/genCode")
public class GenCodeController extends BaseController {

    private final static Logger log = LoggerFactory.getLogger(GenCodeController.class);

    private static final String STEP_1 = "1";
    private static final String STEP_2 = "2";
    private static final String STEP_3 = "3";
    private static final String STEP_4 = "4";

    @Autowired
    private MysqlTableService tableService;

    @Autowired
    private GenCodeService codeService;

    @RequestMapping("/find")
    public String init(Model model) {
        List<String> tables = tableService.findAllTables();
        model.addAttribute("tables", tables);
        return "gen/genCodeForm";
    }

    @RequestMapping("/findColumn")
    public
    @ResponseBody
    Map<Object, Object> findColumn(HttpServletRequest request, String tableName) {

        System.out.println("table:" + tableName);
        List<Column> columns = Lists.newArrayList();

        if (!Strings.isNullOrEmpty(tableName)) {
            columns = codeService.getColumnsByTableName(tableName);
        }
        setJavaType(columns);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", columns);
        info.put("recordsTotal", columns.size());
        info.put("recordsFiltered", columns);
        info.put("draw", request.getParameter("draw"));
        return info;
    }

    private List<Column> setJavaType(List<Column> columns) {
        for (Column column : columns) {
            column.setJavaType(GenUtils.toJavaType(column.getJdbcType()));
        }
        return columns;
    }


    @RequestMapping("/save")
    public String save(HttpServletRequest request) {

        try {
            codeService.genCode(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}