package com.zcoder.admin.gen.domain;

import com.google.common.collect.Lists;
import com.zcoder.admin.core.utils.CamelCaseUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public  class Table implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4737050971995397319L;
	
	/**
	 * 对应实体类名称
	 */
	private String className;

	/**
	 * 包含字段
	 */
	private List<Column> columns = Lists.newArrayList();

	/**
	 * 注释
	 */
	private String comments;

	/**
	 * 表名
	 */
	private String name;


	public Table(String tableName){
		this.name = tableName;
		this.className = CamelCaseUtils.toCapitalizeCamelCase(tableName);
	}
	
}
