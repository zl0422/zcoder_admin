package com.zcoder.admin.gen.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 列
 * 
 * @author lin
 *
 */
@Data
public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8106047230785828976L;


	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 字段长度
	 */
	private String length;

	/**
	 * 数据库字段类型
	 */
	private String jdbcType;

	/**
	 * 对应字段类型
	 */
	private String javaType;

	/**
	 * 是否NULL
	 */
	private String isNull;

	/**
	 * 是否key
	 */
	private String isKey;

	/**
	 * 默认值
	 */
	private String defaultVal;

	/**
	 * 注解
	 */
	private String comment;

	/**
	 * Java字段名
	 */
	private String javaField;
	
	
	
	//-------------------ext-----------------------
	
	/**
	 * 是否是查询条件
	 */
	private boolean isQuery;
	
	/**
	 * 是否是查询列表字段
	 */
	private boolean isList;

	/**
	 * 查询方式
	 */
	private String queryMethod;


	/**
	 * 表单类型
	 */
	private String formType;

	/**
	 * 表弟校验规则
	 */
	private Validator validator;

}
