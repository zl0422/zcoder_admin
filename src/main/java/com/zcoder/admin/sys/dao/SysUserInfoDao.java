package com.zcoder.admin.sys.dao;

import com.zcoder.admin.sys.domain.SysUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lin on 2016-05-24.
 */
public interface SysUserInfoDao extends JpaRepository<SysUserInfo,Long> {

}
