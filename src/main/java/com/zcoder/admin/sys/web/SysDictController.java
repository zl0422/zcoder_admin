package com.zcoder.admin.sys.web;

import com.google.common.base.Strings;
import com.zcoder.admin.core.common.BaseController;
import com.zcoder.admin.core.common.Constants;
import com.zcoder.admin.core.message.ResponseMsg;
import com.zcoder.admin.sys.domain.SysDict;
import com.zcoder.admin.sys.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lin on 2016-05-24.
 */
@Controller
@RequestMapping("/sysDict")
@Slf4j
public class SysDictController extends BaseController {


	@Autowired
	private SysDictService sysDictService;

	@ModelAttribute
	public SysDict get(@RequestParam(required = false) String id) {
		if (!Strings.isNullOrEmpty(id)) {
			return sysDictService.findOne(Long.parseLong(id));
		} else {
			return new SysDict();
		}
	}

	@RequestMapping("/find")
	public String init() {
		return "sys/sysDictList";
	}

	@RequestMapping("/findAll")
	public @ResponseBody Map<Object, Object> findList(HttpServletRequest request, SysDict dict) {
		Page<SysDict> result = sysDictService.findAll(getPageRequest(request));
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("data", result.getContent());
		info.put("recordsTotal", result.getTotalElements());
		info.put("recordsFiltered", result.getTotalElements());
		info.put("draw", request.getParameter("draw"));
		return info;
	}

	@RequestMapping(value = "form")
	public String form(SysDict dict, Model model) {
		model.addAttribute("sysDict", dict);
		return "sys/sysDictForm";
	}

	@RequestMapping(value = "save")
	public String save(SysDict dict, Model model, RedirectAttributes redirectAttributes) {

		if (!beanValidator(model, dict)) {
			return form(dict, model);
		}
		dict.setCreateDate(new Date());
		dict.setDelFlag(Constants.DEL_FLAG_VALID);
		sysDictService.save(dict);
		addMessage(redirectAttributes,MESSAGE_SUCCESS ,"保存字典'" + dict.getLabel() + "'成功");
		return "redirect:/sysDict/find";
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseMsg<String> delete(SysDict dict, RedirectAttributes redirectAttributes) {
		try {
			sysDictService.delete(dict);
			return new ResponseMsg<String>("删除成功");
		} catch (Exception e) {
			return new ResponseMsg<String>("删除失败");
		}
	}

}
