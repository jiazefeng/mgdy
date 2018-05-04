package com.mgdy.vesta.presistent.messageComment.repositoryHbnImpl;

import com.mgdy.vesta.domain.messageComment.model.MessageCommentEntity;
import com.mgdy.vesta.domain.messageComment.repository.MessageCommentRepository;
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
 * Created by 90544 on 2017/7/11.
 */
@Repository
public class MessageCommentRepositoryImpl extends HibernateDaoImpl implements MessageCommentRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addMessageComment(MessageCommentEntity messageCommentEntity) {
        Session session = getCurrentSession();
        session.save(messageCommentEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<MessageCommentEntity> getMessageCommentList() {
        List<Object> params = new ArrayList<Object>();
        String hql = " from MessageCommentEntity mce where 1=1 ";
        hql += " order by mce.createOn desc";
        List<MessageCommentEntity> newsEntityList = (List<MessageCommentEntity>) getHibernateTemplate().find(hql, params.toArray());
        return newsEntityList;
    }

    @Override
    public List<MessageCommentEntity> getMessageCommentInfoList(WebPage webPage, Map map) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from MessageCommentEntity where 1=1 ";
        hql += " order by createOn";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<MessageCommentEntity> messageCommentEntityList = (List<MessageCommentEntity>) getHibernateTemplate().find(hql, params);
        return messageCommentEntityList;
    }

    @Override
    public MessageCommentEntity getMessageCommentInfoById(String mcId) {
        String hql = " from MessageCommentEntity where mcId=?";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0, mcId);
        return (MessageCommentEntity) query.uniqueResult();
    }

    @Override
    public void updateInfo(MessageCommentEntity messageCommentEntity) {
        Session session = getCurrentSession();
        session.update(messageCommentEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteInfoById(MessageCommentEntity messageCommentEntity) {
        Session session = getCurrentSession();
        session.delete(messageCommentEntity);
        session.flush();
        session.close();
    }
}
