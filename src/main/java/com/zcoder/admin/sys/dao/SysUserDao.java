package com.zcoder.admin.sys.dao;

import com.zcoder.admin.sys.domain.SysUser;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * note:方法实现由JPA实现，只需要方法命名符合JPA规范
 * Created by lin on 2016-05-19.
 */
public interface SysUserDao extends JpaRepository<SysUser, Long> {

    SysUser findByLoginNameAndLoginPwd(String loginName,String loginPwd);

    SysUser findByLoginName(String loginName);
}
