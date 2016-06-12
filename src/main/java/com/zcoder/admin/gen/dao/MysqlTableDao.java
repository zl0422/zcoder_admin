package com.zcoder.admin.gen.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcoder.admin.gen.domain.MySqlTable;

public interface MysqlTableDao extends JpaRepository<MySqlTable, Long> {
	
}
