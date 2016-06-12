package com.zcoder.admin.gen.web;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.zcoder.admin.core.common.BaseController;
import com.zcoder.admin.gen.domain.Column;
import com.zcoder.admin.gen.domain.Table;
import com.zcoder.admin.gen.service.GenCodeService;
import com.zcoder.admin.gen.service.MysqlTableService;
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

	@RequestMapping("/findTableDetail")
	public String findTableDetail(Model model, String step_number, String tableName, HttpServletRequest request) {
		String view = "";
		switch (step_number) {
		case STEP_1:
			view = "gen/genCodeStep1";
			stepOne(model);
			break;
		case STEP_2:
			view = "gen/genCodeStep2";
			stepTwo(model,tableName);
			break;
		case STEP_3:
			view = "gen/genCodeStep3";
			break;
		case STEP_4:
			view = "gen/genCodeStep4";
			break;
		default:
			view = "gen/genCodeForm";
			break;
		}
		return view;

	}
	
	/**
	 * 第一步
	 * @param model
	 */
	private void stepOne(Model model){
		List<String> tables = tableService.findAllTables();
		model.addAttribute("tables", tables);
		if (log.isDebugEnabled()){
			log.debug("step one...");
			log.debug("table size:{}",tables.size());
		}
	}


	/**
	 * 第二步
	 * @param model
	 */
	private void stepTwo(Model model,String tableName){
		if (log.isDebugEnabled()){
			log.debug("step two...");
			log.debug("the selected table name : {} ",tableName);
		}
		Table table = new Table(tableName);
		model.addAttribute("table", table);
	}

	@RequestMapping("/findColumn")
	public @ResponseBody Map<Object, Object> findColumn(HttpServletRequest request, String tableName) {

		System.out.println("table:" + tableName);
		List<Column> columns = Lists.newArrayList();

		if (!Strings.isNullOrEmpty(tableName)) {
			columns = codeService.getColumnsByTableName(tableName);
		}
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("data", columns);
		info.put("recordsTotal", columns.size());
		info.put("recordsFiltered", columns);
		info.put("draw", request.getParameter("draw"));
		return info;
	}





}
