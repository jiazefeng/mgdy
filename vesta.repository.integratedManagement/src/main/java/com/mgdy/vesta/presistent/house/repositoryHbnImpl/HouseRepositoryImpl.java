package com.mgdy.vesta.presistent.house.repositoryHbnImpl;

import com.mgdy.vesta.domain.house.model.HouseEntity;
import com.mgdy.vesta.domain.house.model.HouseImageEntity;
import com.mgdy.vesta.domain.house.repository.HouseRepository;
import com.mgdy.vesta.hibernate.HibernateDaoImpl;
import com.mgdy.vesta.taglib.page.WebPage;
import com.mgdy.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/10.
 */
@Repository
public class HouseRepositoryImpl extends HibernateDaoImpl implements HouseRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<HouseEntity> getHouseInfoList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from HouseEntity he where 1=1 ";
        hql += " order by he.modifyOn desc";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<HouseEntity> houseEntityList = (List<HouseEntity>) getHibernateTemplate().find(hql, params.toArray());
        return houseEntityList;
    }

    @Override
    public boolean addHouse(HouseEntity houseEntity) {
        Session session = getCurrentSession();
        session.save(houseEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public void addHouseImage(HouseImageEntity houseImageEntity) {
        Session session = getCurrentSession();
        session.save(houseImageEntity);
        session.flush();
        session.close();
    }

    @Override
    public HouseEntity getHouseInfoById(String houseId) {
        String hql = " from HouseEntity ne where houseId=:houseId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("houseId", houseId);
        return (HouseEntity) query.uniqueResult();
    }

    @Override
    public List<HouseImageEntity> getHouseImageList() {
        String hql = " from HouseImageEntity hie where 1=1 ";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public boolean updateHouse(HouseEntity houseEntity) {
        Session session = getCurrentSession();
        session.update(houseEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<HouseImageEntity> getHouseImageInfoById(String houseId) {
        return null;
    }

    @Override
    public void deleteHouseImageInfoById(String houseId) {
        String sql = "delete FROM HouseImageEntity where houseId='" + houseId + "'";
        Query query = getCurrentSession().createQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void deleteHouseInfo(HouseEntity houseEntity) {
        Session session = getCurrentSession();
        session.delete(houseEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<HouseEntity> getHouseInfoList(String housType) {
        String hql = " from HouseEntity where 1=1 ";
        if (!StringUtil.isEmpty(housType)) {
            hql += " houseType ='" + housType + "' ";
        }
        hql += " order by modifyOn desc ";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }
}
