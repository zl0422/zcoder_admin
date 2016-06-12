package com.zcoder.admin.sys.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色
 * 
 * @author lin
 *
 */
@Entity
@Data
public class SysRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 896439589559030316L;
	@Column
	private SysUser createBy;
	@Column
	private Date createDate;
	@Column
	private String delFlag;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@OneToMany(fetch = FetchType.EAGER)
	private List<SysMenu> permissionList;// 一个角色对应多个权限
	@Column
	private String remarks;
	@Column
	private SysUser updateBy;

	@Column
	private Date updateDate;
	@ManyToMany
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private List<SysUser> userList;// 一个角色对应多个用户

	@Transient
	public List<String> getPermissionsName() {
		List<String> list = new ArrayList<String>();
		List<SysMenu> perlist = getPermissionList();
		for (SysMenu per : perlist) {
			list.add(per.getName());
		}
		return list;
	}

}
