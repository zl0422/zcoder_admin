package com.zcoder.admin.sys.domain;

import lombok.Data;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Date;

/**
 * 数据字典 Created by lin on 2016-05-24.
 */
@Entity
@Data
public class SysDict implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5413925531455108211L;

	@Column
	private SysUser createBy;

	@Column
	private Date CreateDate;

	@Column
	private String delFlag;
	@Column
	private String description;
	@Column
	private String dictType;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column
	private String label;

	@Column
	private String remarks;
	@Column
	private int sort;

	@Column
	private SysUser updateBy;

	@Column
	private Date updateDate;

	@Column
	private String value;

}
