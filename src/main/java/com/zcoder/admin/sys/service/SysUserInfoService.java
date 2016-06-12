package com.zcoder.admin.sys.service;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysUserInfoDao;
import com.zcoder.admin.sys.domain.SysUserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lin on 2016-05-24.
 */
@Component
@Transactional
@Slf4j
public class SysUserInfoService extends CrudService<SysUserInfoDao,SysUserInfo> {

}
