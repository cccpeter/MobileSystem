package com.mobile.mobile.manager;


import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

/**
 * @Author: cpeter
 * @Desc: 获取JPA原生SQL的执行结果
 * @Date: cretead in 2019/12/12 9:54
 * @Last Modified: by
 * @return value
 */
@Service
public class BaseFunctionService {

//    注入manager
    @Autowired
    @PersistenceContext
    private EntityManager entityManager;
/**
     * @Author: cpeter
     * @Desc: 获取执行sql查询的结果
     * @Date: cretead in 2019/12/26 19:25
     * @Last Modified: by
     * @return value
     */
    public List<Map<String, Object>> getSqlResult(String sql){
        List<Map<String, Object>> result;
        try {
            Query query = entityManager.createNativeQuery(sql);
//            Query query = entityManager.getEntityManagerFactory().createEntityManager().createNativeQuery(sql);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            result =query.getResultList();
        }catch (Exception e){
//            构建空对象,不传null
            result = new ArrayList<>();
        }
        return result;
    }
    /**
     * @Author: cpeter
     * @Desc: 获取数据库查询语句
     * @Date: cretead in 2019/12/26 19:06
     * @Last Modified: by
     * @return value
     */
    public boolean getUpdate(String sql){
        boolean result = true;
        try {
            Query query =  entityManager.createNativeQuery(sql);
//            Query query = entityManager.getEntityManagerFactory().createEntityManager().createNativeQuery(sql);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.executeUpdate();
        }catch (Exception e){
            result = false;
        }
        return  result;
    }

}
