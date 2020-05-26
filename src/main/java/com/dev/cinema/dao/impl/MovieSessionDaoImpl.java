package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    private static final Logger LOGGER = Logger.getLogger(MovieSessionDaoImpl.class);

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> criteriaQuery = criteriaBuilder
                    .createQuery(MovieSession.class);
            Root<MovieSession> sessionRoot = criteriaQuery.from(MovieSession.class);
            Predicate idPredicate = criteriaBuilder
                    .equal(sessionRoot.get("movie"), movieId);
            Predicate datePredicate = criteriaBuilder
                    .greaterThan(sessionRoot.get("showTime"), date.atStartOfDay());
            criteriaQuery.where(idPredicate, datePredicate);
            return session.createQuery(criteriaQuery).list();
        } catch (Exception e) {
            throw new DataProcessingException(String
                    .format("Failed to retrieve movie sessions by movie id:%s", movieId), e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            LOGGER.info(String
                    .format("Add Movie Session entity #%d to DB", movieSession.getId()));
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie session to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
