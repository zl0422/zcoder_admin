package com.zcoder.admin.gen.service;

import com.google.common.collect.Lists;
import com.zcoder.admin.gen.domain.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by lin on 2016-06-08.
 */
@Component
@Transactional
public class GenCodeService {

    @Autowired
    private EntityManager em;

    public List<Column> getColumnsByTableName(String tableName){
        String sql = "SHOW FULL FIELDS FROM "+ tableName;
        Query query =  em.createNativeQuery(sql);
        List<Column> result = Lists.newArrayList();
        List objecArraytList = query.getResultList();
        for(int i=0;i<objecArraytList.size();i++) {
            Object[] obj = (Object[]) objecArraytList.get(i);
            Column column = new Column();
            column.setField(String.valueOf(obj[0]));
            column.setJdbcType(String.valueOf(obj[1]));
            column.setIsNull(String.valueOf(obj[3]));
            column.setIsKey(String.valueOf(obj[4]));
            column.setDefaultVal(String.valueOf(obj[5]));
            column.setComment(String.valueOf(obj[8]));
            result.add(column);
        }
        return result;

    }




}
