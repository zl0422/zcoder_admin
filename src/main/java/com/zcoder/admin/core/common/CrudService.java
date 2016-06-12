package com.zcoder.admin.core.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * crud
 * Created by lin on 2016-05-19.
 */
@SuppressWarnings("rawtypes")
@Transactional(readOnly = true)
public abstract class CrudService<D extends JpaRepository, E> {
	
	//-------------ehcache.xml config ----------------
	public static final String EHCACHE_NAME_SYS = "sys";

	

    @Autowired
    protected D dao;

    @SuppressWarnings("unchecked")
	public List<E> findAll() {
        return dao.findAll();
    }
    @SuppressWarnings("unchecked")
    public Page<E> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }
    @SuppressWarnings("unchecked")
    public E findOne(Long id) {
        return (E) dao.findOne(id);
    }
    @SuppressWarnings("unchecked")
    public boolean exists(Long id) {
        return dao.exists(id);
    }
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public E save(E e) {
        return (E) dao.save(e);
    }

    public long count() {
        return dao.count();
    }
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public void delete(Long var1) {
        dao.delete(var1);
    }
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = false)
    public void delete(E var1) {
        dao.delete(var1);
    }

}
