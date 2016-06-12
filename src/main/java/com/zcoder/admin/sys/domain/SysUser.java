package com.zcoder.admin.sys.domain;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lin on 2016-05-19.
 */

@Entity
@Data
public class SysUser implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4919726275436032372L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginName;

    @Column(nullable = false)
    private String loginPwd;

    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private int createBy;

    @OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    @NotFound(action= NotFoundAction.IGNORE)
    private SysUserInfo userInfo;

    private int status;
    
   
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private List<SysRole> sysRoles;
    
    
    @Transient
    public Set<String> getRolesName() {
        List<SysRole> roles = getSysRoles();
        Set<String> set = new HashSet<String>();
        for (SysRole role : roles) {
            set.add(role.getName());
        }
        return set;
    }

}
