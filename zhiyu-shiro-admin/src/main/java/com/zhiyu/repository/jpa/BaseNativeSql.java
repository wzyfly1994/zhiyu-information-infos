package com.zhiyu.repository.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.util.List;

/**
 * @author wengzhiyu
 * @date 2019/11/01
 */
public class BaseNativeSql {
    @PersistenceUnit
    private EntityManagerFactory emf;

    /**
     * 查询多个属性
     * 返回List<Object[]>数组形式的，数组中内容按照查询字段先后
     *
     * @param sql
     * @return
     * @author wengzhiyu
     * @author 2019年11月01日
     */
    public List<Object[]> sqlArrayList(String sql) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery(sql);
        List<Object[]> list = query.getResultList();
        em.close();
        return list;
    }

    /**
     * 查询多个属性
     * 返回List<Object>对象形式的List，Object为Class格式对象
     *
     * @param sql
     * @param obj
     * @return
     * @author wengzhiyu
     * @author 2019年11月01日
     */
    public List sqlObjectList(String sql, Object obj) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery(sql, obj.getClass());
        List list = query.getResultList();
        em.close();
        return list;
    }

    /**
     * 查询单个属性
     *
     * @param sql
     * @return
     * @author wengzhiyu
     * @author 2019年11月01日
     */
    public List sqlSingleList(String sql) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery(sql);
        List list = query.getResultList();
        em.close();
        return list;
    }

    /**
     * @param sqlstr
     * @return
     */
    public List sqlconditionList(String sqlstr, String condition, Class clazz) {
        EntityManager em = emf.createEntityManager();
        String sql = sqlstr +
                condition;
        Query query = em.createNativeQuery(sql, clazz);
        List list = query.getResultList();
        em.close();
        return list;
    }
}
