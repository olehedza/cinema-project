package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.error.DataProcessingException;
import com.dev.cinema.model.Role;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class RoleDaoImpl implements RoleDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    @Override
    public Role add(Role role) {
        @Cleanup
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            LOGGER.info("Role with id " + role.getId() + " was added to DB");
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert Role entity", e);
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role.RoleName roleNameEnum = Role.RoleName.valueOf(roleName);
        try (Session session = sessionFactory.openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            criteriaQuery.select(root)
                    .where(criteriaBuilder.equal(root.get("roleName"), roleNameEnum));
            return session.createQuery(criteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to retrieve "
                    + "Role with role name: " + roleName, e);
        }
    }
}
