package com.zcoder.admin.sys.service;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysRoleDao;
import com.zcoder.admin.sys.domain.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@Slf4j
public class SysRoleService extends CrudService<SysRoleDao,SysRole> {

}
