package com.mgdy.vesta.presistent.video.repositoryHbnImpl;

import com.mgdy.vesta.domain.video.model.VideoEntity;
import com.mgdy.vesta.domain.video.repository.VideoRepository;
import com.mgdy.vesta.hibernate.HibernateDaoImpl;
import com.mgdy.vesta.taglib.page.WebPage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiazefeng on 2018/6/1.
 */
@Repository
public class VideoRepositoryImpl extends HibernateDaoImpl implements VideoRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void add(VideoEntity videoEntity) {
        Session session = getCurrentSession();
        session.save(videoEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<VideoEntity> getVideos() {
        List<Object> params = new ArrayList<Object>();
        String hql = " from VideoEntity ve where 1=1 and status=1";
        hql += " order by ve.createDate desc";
        List<VideoEntity> videoEntityList = (List<VideoEntity>) getHibernateTemplate().find(hql, params.toArray());
        return videoEntityList;
    }

    @Override
    public List<VideoEntity> getVideosByUserId(String userId) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from VideoEntity ve where 1=1 and status=1 and userId = '" + userId + "'";
        hql += " order by ve.createDate desc";
        List<VideoEntity> videoEntityList = (List<VideoEntity>) getHibernateTemplate().find(hql, params.toArray());
        return videoEntityList;
    }

    @Override
    public List<VideoEntity> getVideosByParam(WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from VideoEntity ve where 1=1 ";
        hql += " order by ve.createDate desc";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<VideoEntity> videoEntityList = (List<VideoEntity>) getHibernateTemplate().find(hql, params.toArray());
        return videoEntityList;
    }

    @Override
    public VideoEntity getVideosByVideoId(String videoId) {
        String hql = " from VideoEntity ve where 1=1 and vId = '" + videoId + "'";
        VideoEntity videoEntity = (VideoEntity) getCurrentSession().createQuery(hql).uniqueResult();
        return videoEntity;
    }

    @Override
    public void update(VideoEntity videoEntity) {
        Session session = getCurrentSession();
        session.update(videoEntity);
        session.flush();
        session.close();
    }
}
