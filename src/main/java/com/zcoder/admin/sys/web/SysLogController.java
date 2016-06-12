package com.zcoder.admin.sys.web;

import com.zcoder.admin.core.common.BaseController;
import com.zcoder.admin.core.exception.GlobalException;
import com.zcoder.admin.sys.domain.SysLog;
import com.zcoder.admin.sys.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 2016-05-20.
 */
@Controller
@RequestMapping("/sysLog")
@Slf4j
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/find")
    public String findLog(Model model) {
        model.addAttribute("beginDate",new Date());
        model.addAttribute("endDate",new Date());
        return "sys/sysLogList";
    }


	@RequestMapping("/findAll")
    public  @ResponseBody Map<Object, Object> findList(HttpServletRequest request) throws GlobalException{
		SysLog sysLog = new SysLog();
		try {
			sysLog.setBeginDate(new SimpleDateFormat().parse(request.getParameter("beginDate")));
			sysLog.setEndDate(new SimpleDateFormat().parse(request.getParameter("endDate")));
		} catch (ParseException e) {
			
		}
        Page<SysLog> result = sysLogService.findByCreateDateBetween(getPageRequest(request),sysLog);
        Map<Object, Object> info = new HashMap<Object, Object>();
        info.put("data", result.getContent());
        info.put("recordsTotal", result.getTotalElements());
        info.put("recordsFiltered", result.getTotalElements());
        info.put("draw", request.getParameter("draw"));
        return info;
    }


}
