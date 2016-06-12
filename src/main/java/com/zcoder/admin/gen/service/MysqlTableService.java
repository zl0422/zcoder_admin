package com.zcoder.admin.gen.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zcoder.admin.core.common.CrudService;
import com.zcoder.admin.gen.dao.MysqlTableDao;
import com.zcoder.admin.gen.domain.MySqlTable;

@Component
@Transactional
public class MysqlTableService extends CrudService<MysqlTableDao,MySqlTable>{

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<String> findAllTables(){
		String sql = "show tables";
		Query query =  em.createNativeQuery(sql);
		return (List<String>)query.getResultList();   
	}
	
}
