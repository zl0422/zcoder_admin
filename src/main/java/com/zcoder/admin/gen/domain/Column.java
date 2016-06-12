package com.zcoder.admin.gen.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 列
 * 
 * @author lin
 *
 */
@Entity
@Data
public class Column implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8106047230785828976L;


	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/**
	 * 字段名
	 */
	@Transient
	private String field;

	/**
	 * 数据库字段类型
	 */
	@Transient
	private String jdbcType;

	/**
	 * 对应字段类型
	 */
	@Transient
	private String javaType;

	/**
	 * 是否NULL
	 */
	@Transient
	private String isNull;

	/**
	 * 是否key
	 */
	@Transient
	private String isKey;

	/**
	 * 默认值
	 */
	@Transient
	private String defaultVal;

	/**
	 * 注解
	 */
	@Transient
	private String comment;

	/**
	 * Java字段名
	 */
	@Transient
	private String javaField;
	
	
	
	//-------------------ext-----------------------
	
	/**
	 * 是否是查询条件
	 */
	@Transient
	private boolean isQuery;
	
	/**
	 * 是否是查询列表字段
	 */
	@Transient
	private boolean isList;


	/**
	 * 查询方式
	 */
	private String queryMethod;


	/**
	 * 表单类型
	 */
	private String formType;
}
