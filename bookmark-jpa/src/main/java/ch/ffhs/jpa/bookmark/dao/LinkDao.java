package ch.ffhs.jpa.bookmark.dao;

import ch.ffhs.jpa.bookmark.domain.Link;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
@Named("linkDao")
public class LinkDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Link save(Link link) {
        entityManager.merge(link);
        return link;
    }

    public List<Link> getAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Link> query = cb.createQuery(Link.class);
        CriteriaQuery<Link> qr = query.select(query.from(Link.class));
        return entityManager.createQuery(qr).getResultList();
    }

    public Link getById(int id) {
        return entityManager.find(Link.class, id);
    }

    public void delete(Link link) {
        entityManager.remove(link);
    }

    public void deleteById(int id) {
        delete(getById(id));
    }
}
