package com.mgdy.vesta.presistent.news.repositoryHbnImpl;

import com.mgdy.vesta.domain.news.model.NewsEntity;
import com.mgdy.vesta.domain.news.repository.NewsRepository;
import com.mgdy.vesta.hibernate.HibernateDaoImpl;
import com.mgdy.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/7/4.
 */
@Repository
public class NewsRepositoryImpl extends HibernateDaoImpl implements NewsRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<NewsEntity> getNewsList(Map map, WebPage webPage, String staffId) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from NewsEntity ne where 1=1 ";
        hql += " order by ne.modifyDate desc";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<NewsEntity> newsEntityList = (List<NewsEntity>) getHibernateTemplate().find(hql, params.toArray());
        return newsEntityList;
    }

    @Override
    public void addNews(NewsEntity newsEntity) {
        Session session = getCurrentSession();
        session.save(newsEntity);
        session.flush();
        session.close();
    }

    @Override
    public NewsEntity getNewsInfoById(String newsId) {
        String hql = " from NewsEntity ne where newsId=:newsId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("newsId", newsId);
        return (NewsEntity) query.uniqueResult();
    }

    @Override
    public void updateNews(NewsEntity newsEntity) {
        Session session = getCurrentSession();
        session.update(newsEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteNews(NewsEntity newsEntity) {
        Session session = getCurrentSession();
        session.delete(newsEntity);
        session.flush();
        session.close();
    }

    @Override
    public int getNewsCount(String slideShow) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM md_news mn ");
        sql.append(" WHERE 1=1 AND mn.SLIDE_SHOW = '"+slideShow+"'");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public List<NewsEntity> getNewsList() {
        List<Object> params = new ArrayList<Object>();
        String hql = " from NewsEntity ne where 1=1 ";
        hql += " order by ne.modifyDate desc";
        List<NewsEntity> newsEntityList = (List<NewsEntity>) getHibernateTemplate().find(hql, params.toArray());
        return newsEntityList;
    }
}
