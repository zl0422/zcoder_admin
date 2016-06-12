package com.zcoder.admin.sys.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcoder.admin.core.common.BaseController;

/**
 * 主操作界面
 * @author lin
 *
 */
@Controller
@Slf4j
public class DashboardController extends BaseController {
	
	@RequestMapping("/dashboard")
	public String dashboard(){
		return "dashboard";
	}

}
