package com.zcoder.admin.sys.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.sys.dao.SysLogDao;
import com.zcoder.admin.sys.domain.SysLog;

/**
 * Created by lin on 2016-05-20.
 */
@Component
@Transactional
@Slf4j
public class SysLogService extends CrudService<SysLogDao,SysLog> {

	@Cacheable(value = EHCACHE_NAME_SYS)
    public Page<SysLog> findByCreateDateBetween(Pageable pageable,SysLog param) {
        return dao.findByCreateDateBetween(param.getBeginDate(),param.getEndDate(),pageable);
    }
}
