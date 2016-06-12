package com.zcoder.admin.sys.service;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysUserDao;
import com.zcoder.admin.sys.domain.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lin on 2016-05-19.
 */
@Component
@Transactional
@Slf4j
public class SysUserService extends CrudService<SysUserDao,SysUser>{

    /**
     * login
     * @param loginName
     * @param loginPwd
     * @return
     */
    public SysUser login(String loginName, String loginPwd) {
        return dao.findByLoginNameAndLoginPwd(loginName,loginPwd);
    }


}
