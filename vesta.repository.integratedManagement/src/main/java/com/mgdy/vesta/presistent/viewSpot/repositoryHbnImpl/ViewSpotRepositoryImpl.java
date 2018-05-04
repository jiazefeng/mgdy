package com.mgdy.vesta.presistent.viewSpot.repositoryHbnImpl;

import com.mgdy.vesta.domain.viewSpot.model.ViewSpotEntity;
import com.mgdy.vesta.domain.viewSpot.repository.ViewSpotRepository;
import com.mgdy.vesta.hibernate.HibernateDaoImpl;
import com.mgdy.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/11.
 */
@Repository
public class ViewSpotRepositoryImpl extends HibernateDaoImpl implements ViewSpotRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ViewSpotEntity> getViewSpotInfoList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ViewSpotEntity ne where 1=1 ";
        hql += " order by ne.modifyOn desc";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ViewSpotEntity> viewSpotEntityList = (List<ViewSpotEntity>) getHibernateTemplate().find(hql, params.toArray());
        return viewSpotEntityList;
    }

    @Override
    public void addViewSpot(ViewSpotEntity viewSpotEntity) {
        Session session = getCurrentSession();
        session.save(viewSpotEntity);
        session.flush();
        session.close();
    }

    @Override
    public ViewSpotEntity getViewSpotInfoById(String viewSpotId) {
        String hql = " from ViewSpotEntity vse where vse.viewSpotId=:viewSpotId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("viewSpotId", viewSpotId);
        return (ViewSpotEntity) query.uniqueResult();
    }

    @Override
    public void updateViewSpot(ViewSpotEntity viewSpotEntity) {
        Session session = getCurrentSession();
        session.update(viewSpotEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteViewSpotInfo(ViewSpotEntity viewSpotEntity) {
        Session session = getCurrentSession();
        session.delete(viewSpotEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<ViewSpotEntity> getViewSpotInfoList() {
        String hql = " from ViewSpotEntity vse";
        hql += " order by modifyOn desc";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }
}
