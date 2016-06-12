package com.zcoder.admin.core.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Strings;
import com.zcoder.admin.core.beanvalidator.BeanValidators;

/**
 * base controller all controllers should be sub-class Created by lin on
 * 2016-05-18.
 */
public class BaseController {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final int DEFAULT_PAGE_NUM = 1;

	private final int DEFAULT_PAGE_SIZE = 20;

	private final String DEFAULT_SORT_FIELD = "id";
	
	
	protected final static String ACTION_SHOW = "show";
	
	protected final static String ACTION_EDIT = "edit";
	

	
	//-----------------------message----------------------------
	
	protected final String MESSAGE_INFO = "INFO";
	protected final String MESSAGE_ERROR = "danger";
	protected final String MESSAGE_SUCCESS = "success";

	/**
	 * 获取参数
	 *
	 * @param req
	 * @return
	 */
	protected PageRequest getPageRequest(HttpServletRequest req) {
		int pageNum = DEFAULT_PAGE_NUM;
		int pagesize = DEFAULT_PAGE_SIZE;
		String sortField = req.getParameter("columns[0][data]");
		String sortorder = req.getParameter("order[0][dir]");

		if (!Strings.isNullOrEmpty(req.getParameter("start"))) {
			pageNum = Integer.parseInt(req.getParameter("start"));
		}
		if (!Strings.isNullOrEmpty(req.getParameter("length"))) {
			pagesize = Integer.parseInt(req.getParameter("length"));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("request uri: {}", req.getRequestURI());
			logger.debug("order column: {}", req.getParameter("columns[0][data]"));
			logger.debug("order dir: {}", req.getParameter("order[0][dir]"));
			logger.debug("start: {}", req.getParameter("start"));
			logger.debug("length: {}", req.getParameter("length"));
		}

		if (Strings.isNullOrEmpty(sortField)) {
			return new PageRequest(pageNum / pagesize, pagesize, Sort.Direction.DESC, DEFAULT_SORT_FIELD);
		} else {
			if ("DESC".equalsIgnoreCase(sortorder)) {
				return new PageRequest(pageNum / pagesize, pagesize, Sort.Direction.DESC, sortField);
			} else {
				return new PageRequest(pageNum / pagesize, pagesize, Sort.Direction.ASC, sortField);
			}

		}

	}
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model,MESSAGE_ERROR, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes,MESSAGE_ERROR, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	

	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model,String type, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("type", type);
		model.addAttribute("content", sb.toString());
	}
	
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes,String type,String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("type", type);
		redirectAttributes.addFlashAttribute("content", sb.toString());
	}
	
	
	
}
