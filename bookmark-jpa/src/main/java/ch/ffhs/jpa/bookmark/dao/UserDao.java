package ch.ffhs.jpa.bookmark.dao;

import ch.ffhs.jpa.bookmark.domain.User;
import ch.ffhs.jpa.bookmark.util.Hash;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
@Named("userDao")
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User save(User user) {
        entityManager.merge(user);
        return user;
    }

    public User find(String email, String password) {
        String sql = "SELECT u FROM user AS u WHERE email = :email AND password = :password";

        TypedQuery<User> query = entityManager.createQuery(sql, User.class);
        query.setParameter("email", email);
        query.setParameter("password", Hash.sha1(password));

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<User> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        CriteriaQuery<User> qr = query.select(query.from(User.class));
        return entityManager.createQuery(qr).getResultList();
    }

    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }

    public void deleteById(int id) {
        delete(getById(id));
    }
}
