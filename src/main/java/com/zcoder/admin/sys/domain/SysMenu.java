package com.zcoder.admin.sys.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class SysMenu  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2978243265059384184L;
	@Column
	private SysUser createBy;
	@Column
	private Date createDate;
	
	@Column
	private String delFlag;
	
	@Column
	private String href;
	@Column
	private String icon;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String isShow;

	@Column
	private String name;

	@Column
	private Long parentId;

	@Column
	private String permission;

	@Column
	private String remarks;

	@Column
	private int sort;

	@Column
	private String target;

	@Column
	private SysUser updateBy;

	@Column
	private Date updateDate;



	@Override
	public String toString() {
		return "SysMenu [createBy=" + createBy + ", createDate=" + createDate + ", delFlag=" + delFlag + ", href="
				+ href + ", icon=" + icon + ", id=" + id + ", isShow=" + isShow + ", name=" + name + ", parentId="
				+ parentId + ", permission=" + permission + ", remarks=" + remarks + ", sort=" + sort + ", target="
				+ target + ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}

	@Data
	public class MenuTree{

		private Long id;
		private Long pId;
		private String name;
		
		
	}
	

}
