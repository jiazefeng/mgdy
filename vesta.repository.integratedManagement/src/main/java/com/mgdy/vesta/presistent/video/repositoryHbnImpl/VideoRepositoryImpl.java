package com.mgdy.vesta.presistent.video.repositoryHbnImpl;

import com.mgdy.vesta.domain.video.model.VideoEntity;
import com.mgdy.vesta.domain.video.repository.VideoRepository;
import com.mgdy.vesta.hibernate.HibernateDaoImpl;
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
        String hql = " from VideoEntity ve where 1=1 ";
        hql += " order by ve.createDate desc";
        List<VideoEntity> videoEntityList = (List<VideoEntity>) getHibernateTemplate().find(hql, params.toArray());
        return videoEntityList;
    }
}
