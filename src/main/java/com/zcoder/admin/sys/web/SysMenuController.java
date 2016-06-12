package com.zcoder.admin.sys.web;

import com.google.common.base.Strings;
import com.zcoder.admin.core.common.BaseController;
import com.zcoder.admin.core.common.Constants;
import com.zcoder.admin.core.message.ResponseMsg;
import com.zcoder.admin.sys.domain.SysMenu;
import com.zcoder.admin.sys.domain.SysMenu.MenuTree;
import com.zcoder.admin.sys.service.SysMenuService;
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
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/sysMenu")
@Slf4j
public class SysMenuController extends BaseController {
	
	
	@Autowired
	private SysMenuService sysMenuService;

	@ModelAttribute
	public SysMenu get(@RequestParam(required = false) String id) {
		if (!Strings.isNullOrEmpty(id)) {
			return sysMenuService.findOne(Long.parseLong(id));
		} else {
			return new SysMenu();
		}
	}

	@RequestMapping("/find")
	public String init() {
		return "sys/sysMenuList";
	}

	@RequestMapping("/findAll")
	public @ResponseBody Map<Object, Object> findList(HttpServletRequest request, SysMenu menu) {
		Page<SysMenu> result = sysMenuService.findAll(getPageRequest(request));
		Map<Object, Object> info = new HashMap<Object, Object>();
		info.put("data", result.getContent());
		info.put("recordsTotal", result.getTotalElements());
		info.put("recordsFiltered", result.getTotalElements());
		info.put("draw", request.getParameter("draw"));
		return info;
	}
	
	
	@RequestMapping(value = "form")
	public String form(SysMenu sysMenu, Model model,HttpServletRequest request) {
		model.addAttribute("sysMenu", sysMenu);
		model.addAttribute("action", request.getParameter("action"));
		return "sys/sysMenuForm";
	}

	@RequestMapping(value = "save")
	public String save(SysMenu sysMenu, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {

		if (!beanValidator(model, sysMenu)) {
			return form(sysMenu, model,request);
		}
		sysMenu.setCreateDate(new Date());
		sysMenu.setDelFlag(Constants.DEL_FLAG_VALID);
		sysMenuService.save(sysMenu);
		addMessage(redirectAttributes,MESSAGE_SUCCESS ,"保存菜单'" + sysMenu.getName() + "'成功");
		return "redirect:/sysMenu/find";
	}
	
	
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResponseMsg<String> delete(SysMenu sysMenu, RedirectAttributes redirectAttributes) {
		try {
			sysMenuService.delete(sysMenu);
			return new ResponseMsg<String>("删除成功");
		} catch (Exception e) {
			return new ResponseMsg<String>("删除失败");
		}
	}
	
	@RequestMapping(value = "loadTree")
	@ResponseBody
	public List<MenuTree> loadTree(){
		return sysMenuService.loadMenuTree();
	}
	

}
