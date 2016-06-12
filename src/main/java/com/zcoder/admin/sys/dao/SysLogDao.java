package com.zcoder.admin.sys.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zcoder.admin.sys.domain.SysLog;

/**
 * Created by lin on 2016-05-20.
 */
public interface SysLogDao extends JpaRepository<SysLog, Long> {

	@Query("select a from SysLog a where a.createDate >= ?1 and a.createDate <= ?2 ") 
    Page<SysLog> findByCreateDateBetween(Date beginDate, Date endDate,Pageable pageable);
}
