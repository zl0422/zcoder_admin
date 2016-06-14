package com.zcoder.admin.sys.service;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysDictDao;
import com.zcoder.admin.sys.domain.SysDict;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lin on 2016-06-14.
 */
@Component
@Transactional
@Slf4j
public class SysDictService extends CrudService<SysDictDao,SysDict> {
}
